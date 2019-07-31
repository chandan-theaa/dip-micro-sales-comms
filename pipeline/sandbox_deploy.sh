#!/bin/bash
set -e

SERVICE_NAME=`cat servicename`

export AWS_DEFAULT_PROFILE=default
ACCOUNT_ID=`aws sts get-caller-identity --query "Account" --output text`

PLATFORM=dip
ENV=sandbox

read -e -p "ENV ID [develop]: " ENV_ID
ENV_ID=${ENV_ID:-develop}

read -e -p "Version [latest]: " VERSION
VERSION=${VERSION:-latest}

REPO_REGION=eu-west-1
REPO=${ACCOUNT_ID}.dkr.ecr.${REPO_REGION}.amazonaws.com
CAPABILITIES=CAPABILITY_IAM


$(aws ecr get-login --region ${REPO_REGION} --no-include-email)
aws ecr describe-repositories --repository-names ${PLATFORM}-${SERVICE_NAME} || aws ecr create-repository --repository-name ${PLATFORM}-${SERVICE_NAME}

pushd ..
docker build --build-arg JAR_FILE=build/libs/`./gradlew -q printjar` -t ${REPO}/${PLATFORM}-${SERVICE_NAME}:${VERSION} .
docker push $REPO/${PLATFORM}-${SERVICE_NAME}:${VERSION}
popd

aws \
cloudformation \
deploy \
--role-arn arn:aws:iam::${ACCOUNT_ID}:role/CloudFormationRole \
--template-file ../cloudformation/cloudformation.yml \
--stack-name ${PLATFORM}-${SERVICE_NAME}-${ENV}-${ENV_ID} \
--capabilities ${CAPABILITIES} \
--parameter-overrides \
ServiceName=${SERVICE_NAME} \
Env=${ENV} \
EnvId=${ENV_ID} \
Version=${VERSION}

unset AWS_DEFAULT_PROFILE

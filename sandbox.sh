SERVICE=micro-sales-comms
PROJECT=dip
VERSION=local
ENV=sandbox
ENV_ID=develop
REPO=454457622811.dkr.ecr.eu-west-1.amazonaws.com
BUCKET=dip-develop-cloudformation
CAPABILITIES=CAPABILITY_IAM


$(aws ecr get-login --region eu-west-1 --no-include-email)
aws ecr describe-repositories --repository-names ${PROJECT}-${SERVICE} || aws ecr create-repository --repository-name ${PROJECT}-${SERVICE}

docker build --build-arg JAR_FILE=build/libs/`./gradlew -q printjar` -t ${REPO}/${PROJECT}-${SERVICE}:${VERSION} .
docker push $REPO/${PROJECT}-${SERVICE}:${VERSION}

aws cloudformation deploy \
--template-file cloudformation/cloudformation.yml \
--stack-name ${PROJECT}-${SERVICE}-${ENV}-${ENV_ID} \
--role-arn arn:aws:iam::454457622811:role/CloudFormationRole \
--parameter-overrides \
ServiceName=${SERVICE} \
Env=${ENV} \
EnvId=${ENV_ID} \
Version=${VERSION} \
--capabilities ${CAPABILITIES}

SERVICE_NAME=micro-sales-breakdown

printf "Hotfix Version: "
read VERSION


aws \
--profile aa-dev \
cloudformation \
deploy \
--template-file hotfix_pipeline.yml \
--stack-name dip-${SERVICE_NAME}-hotfix-pipeline \
--capabilities CAPABILITY_NAMED_IAM \
--parameter-overrides \
ServiceName=${SERVICE_NAME} \
GithubRepo=dip-${SERVICE_NAME} \
GithubToken=`aws ssm --profile aa-dev get-parameter --name GithubToken --with-decryption --query "Parameter.Value" --output text` \
CyclingSecret=`openssl rand -base64 32` \
Version=${VERSION}

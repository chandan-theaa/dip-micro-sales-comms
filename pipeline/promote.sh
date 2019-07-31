SERVICE_NAME=dip-micro-sales-comms



aws \
--profile aa-dev \
cloudformation \
deploy \
--template-file promote_pipeline.yml \
--stack-name dip-${SERVICE_NAME}-promote \
--capabilities CAPABILITY_NAMED_IAM \
--parameter-overrides \
ServiceName=${SERVICE_NAME} \
GithubRepo=dip-${SERVICE_NAME}

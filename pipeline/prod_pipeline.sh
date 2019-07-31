SERVICE_NAME=micro-sales-breakdown


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


aws \
--profile aa-pre \
cloudformation \
deploy \
--template-file prod_repository.yml \
--stack-name dip-${SERVICE_NAME}-repository \
--parameter-overrides \
ServiceName=${SERVICE_NAME}


aws \
--profile aa-pre \
cloudformation \
deploy \
--template-file prod_pipeline.yml \
--stack-name dip-${SERVICE_NAME}-pipeline \
--capabilities CAPABILITY_NAMED_IAM \
--parameter-overrides \
ServiceName=${SERVICE_NAME}

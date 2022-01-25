IMAGE_NAME ?= rest-sample-app

build:
	pack build  --volume $(PWD)/conf.tar.gz:/tmp/conf.tar.gz  --descriptor project.toml $(IMAGE_NAME)

run:
	docker run --rm --tty --publish 9080:9080 --name $(IMAGE_NAME) --volume $(PWD)/binding:/platform/bindings/open-liberty --env SERVICE_BINDING_ROOT=/platform/bindings $(IMAGE_NAME)

start:
	docker run --rm -d --publish 9080:9080 --name $(IMAGE_NAME) --volume $(PWD)/binding:/platform/bindings/open-liberty --env SERVICE_BINDING_ROOT=/platform/bindings $(IMAGE_NAME)

stop:
	docker stop $(IMAGE_NAME)

logs:
	docker logs -f $(IMAGE_NAME)

test:
	curl -s http://localhost:9080/system/properties | jq
compose := docker-compose.yml

install:
	./mvnw clean install -DskipTests=true

compose-build:
	docker-compose -f $(compose) build

test:
	./mvnw test

compose-up:
	docker-compose -f $(compose) up

compose-down:
	docker-compose -f $(compose) down

build: install compose-build

start: compose-down build compose-up

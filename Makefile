install:
	./mvnw clean install -DskipTests=true

compose-build:
	docker-compose -f docker-compose.yml build

test:
	./mvnw test

compose-up:
	docker-compose -f docker-compose.yml up

compose-down:
	docker-compose -f docker-compose.yml down

build: install compose-build

start: compose-down build compose-up

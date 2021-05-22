build:
	mvn clean install -DskipTests=true

test:
	mvn test

compose-up:
	docker-compose -f docker-compose.yml up

compose-down:
	docker-compose -f docker-compose.yml down

start: compose-down build compose-up

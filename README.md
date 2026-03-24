# Ride Sharing Backend Monorepo

Uber-like backend monorepo using Spring Boot microservices.

## Tech Stack
- Java 17
- Spring Boot 3.5.12
- Spring Web, Validation, Data JPA, Security (JWT)
- Spring Kafka
- Spring Data Redis
- WebSocket/STOMP
- MySQL
- Docker + Docker Compose
- Lombok
- Actuator
- Springdoc OpenAPI Swagger

## Services and Ports
- api-gateway: 8080
- auth-service: 8081
- user-service: 8082
- driver-service: 8083
- ride-service: 8084
- matching-service: 8085
- pricing-service: 8086
- payment-service: 8087
- tracking-service: 8088
- notification-service: 8089

## Swagger and OpenAPI
For every service:
- Swagger UI: `http://localhost:{port}/swagger-ui.html`
- API docs: `http://localhost:{port}/v3/api-docs`

Swagger security uses Bearer JWT (`Authorize` button enabled).
Public endpoints include `/swagger-ui/**`, `/swagger-ui.html`, `/v3/api-docs/**`, and `/actuator/health`.

## Core REST APIs
- `POST /auth/register`
- `POST /auth/login`
- `GET /users`
- `POST /drivers/location`
- `POST /rides/request`
- `POST /rides/{rideId}/accept`
- `POST /rides/{rideId}/start`
- `POST /rides/{rideId}/complete`
- `POST /payments/process`
- `POST /matching/nearest`
- `GET /tracking/subscribe`

## WebSocket/STOMP Tracking
Tracking service (`8088`) exposes endpoint:
- Handshake endpoint: `/ws`
- App destinations:
  - `/app/driver/location/{rideId}`
  - `/app/ride/status/{rideId}`
- Topic subscriptions:
  - `/topic/ride/{rideId}/location`
  - `/topic/ride/{rideId}/status`
  - `/user/queue/notifications` (reserved)

Flow:
1. Rider subscribes to `/topic/ride/{rideId}/location` and `/topic/ride/{rideId}/status`
2. Driver publishes location to `/app/driver/location/{rideId}`
3. Tracking service broadcasts to `/topic/ride/{rideId}/location`

Automated smoke check:
- `tracking-service/src/test/java/com/rideshare/trackingservice/WebSocketSmokeTest.java`

## Infra and Events
- Redis keys:
  - `drivers:geo`
  - `drivers:availability:{driverId}`
- Kafka topics:
  - `ride.requested`
  - `ride.accepted`
  - `ride.completed`
  - `driver.location.updated`
  - `payment.succeeded`

## Run
1. Build all modules:
   - `mvn clean package`
2. Start local stack:
   - `docker compose up --build`
# rider-app

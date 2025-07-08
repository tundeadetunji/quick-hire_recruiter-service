![CI](https://github.com/tundeadetunji/quick-hire_recruiter-service/actions/workflows/ci.yml/badge.svg)

## 📬 Messaging
## 🧪 Testing
## ⚙️ Concurrency & Transactions
## 📘 Pagination


## 📬 Messaging

This service both **sends and receives messages via RabbitMQ**.

- When a recruiter registers or posts/updates a job, a `NotificationMessage` is sent.
  - One message goes to the `recruiter.notify` queue (local logging).
  - Another is forwarded to the `admin.notify` queue (for admin logs).
- When a candidate applies via `candidate-service`, this service **receives** a notification from RabbitMQ.

To observe messaging:
1. Trigger an application in `candidate-service` via Swagger.
2. This service will log the message (via `NotificationListener`).
3. Messages forwarded to `admin.notify` will appear via `/admin/messages`.

## 🧪 Testing

This service uses **JUnit 5**, **Mockito**, and Spring’s `@WebMvcTest` for lightweight HTTP endpoint testing.

Tests are automatically executed through GitHub Actions.

### ✅ What’s Covered

- Creating job posts (`POST /api/v1/post/post`)
- Sending notifications to RabbitMQ (`NotificationProducer`)
- Registering recruiters and triggering notifications
- Rollback behavior when notification delivery fails

## ⚙️ Concurrency & Transactions

This service coordinates multiple transactional workflows involving recruiters, jobs, and job posts:

- Creating or updating jobs
- Publishing job posts and updating their status
- Registering recruiters and moving candidate applications

All major service methods are annotated with `@Transactional` to ensure database consistency and atomic messaging via RabbitMQ.

Entities like `Job` and `Post` use **optimistic locking** with `@Version` to guard against race conditions — preventing concurrent edits (e.g., post status changes or job updates) from silently overwriting one another.

## 📘 Pagination

This service supports pagination on relevant endpoints using Spring’s `Pageable` abstraction:

- `GET /api/v1/recruiter` returns paged recruiter records.
- `GET /api/v1/post/{postId}/applications?status=APPLIED` supports paginated viewing of job post applications filtered by status.

To paginate results, include `page` and `size` query params:

```http
GET /api/v1/post/3/applications?status=APPLIED&page=0&size=10


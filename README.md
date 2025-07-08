![CI](https://github.com/tundeadetunji/quick-hire_recruiter-service/actions/workflows/ci.yml/badge.svg)

## 📬 Messaging and Observability
## 🧪 Testing
## ⚙️ Concurrency & Transactions
## 📘 Pagination


## 📬 Messaging and Observability

This service **publishes messages to RabbitMQ** when a recruiter or job post is created.

- When a recruiter is registered or a job is posted, a `NotificationMessage` is sent.
- Messages are routed through `app.exchange` using keys like `recruiter.notify` and `admin.notify`.
- The `admin-service` listens for these messages.

To observe messaging:
- Use Swagger to create a recruiter or post.
- Then call `/admin/messages` to view received messages (or check logs).

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


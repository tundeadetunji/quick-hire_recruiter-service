![CI](https://github.com/tundeadetunji/quick-hire_recruiter-service/actions/workflows/ci.yml/badge.svg)

# 🧑‍💼 Recruiter Service – QuickHire+ Microservices MVP  

With QuickHire+, recruiters can register, create jobs, publish posts, and receive candidate applications.

📄 API Docs: View <a href="https://quick-hire-recruiter-service.onrender.com/swagger-ui/index.html">Swagger UI</a>

<br/>
<br/>

```  
+-------------------+       RabbitMQ        +-------------------+
|  Candidate Service|  ───────────────▶     |  Recruiter Service|
|                   |       🔔 Notify       |                   |
| - Apply to jobs   |◀───────────────       | - Manage Jobs     |
+-------------------+                      ◀| - Notify Admin    |
                                            +-------------------+
                                                   │
                                                   ▼
                                       +------------------------+
                                       |    Admin Service       |
                                       | - Logs notifications   |
                                       | - In-memory store      |
                                       +------------------------+
```

---

🧰 Tech Stack:
- ☕ Java 17
- 🌱 Spring Boot 3.x
- 📬 RabbitMQ (Messaging)
- 🛡️ Resilience4j (Observability)
- 🧪 JUnit 5, 🎭 Mockito (Testing)
- 🗄️ H2 / PostgreSQL (DB Layer)
- ⚙️ CI/CD via GitHub Actions

---

📬 Messaging  
This service **sends and receives** RabbitMQ messages:

- Sends notifications for recruiter/job/post creation  
- Receives application alerts from `candidate-service`  
- Forwards messages to `admin-service` for logging

🔍 To observe:
1. Apply via candidate-service  
2. Message logged here  
3. Forwarded to `admin.notify` (visible in `admin-service`)

---

🧪 Testing  
Uses JUnit 5, Mockito, and Spring’s `@WebMvcTest`.  
CI powered by GitHub Actions.

✅ What’s Covered
- POST /api/v1/post/post  
- NotificationProducer and rollback logic  
- Recruiter registration + messaging

---

⚙️ Concurrency & Transactions  
Transactional integrity for:

- Job and post workflows  
- Recruiter registration  
- Application forwarding

🛡️ Uses `@Transactional` with `@Version` fields to prevent race conditions.

---

📊 Resilience4j Observability  
Supports:

✅ Circuit Breakers  
🔁 Retry Policies  
⏱️ Rate Limiting  

Actuator endpoints (internal):
- /actuator/resilience4j.circuitbreakers  
- /actuator/resilience4j.retries  
- /actuator/resilience4j.ratelimiters

🧪 To test locally:
- Provide env vars  
- Or use `.env.local` with H2

---

📘 Pagination  
Supports pagination on:

- `GET /api/v1/recruiter`  
- `GET /api/v1/post/{postId}/applications?status=APPLIED`

Example:
`GET /api/v1/post/3/applications?status=APPLIED&page=0&size=10`

---

⚠️ Deployment Note on Cold Start Behavior (Render)

QuickHire+ is deployed on [Render](https://render.com).

When inactive for some time, services may enter a **"cold" state**, resulting in:

- Initial request latency (10–30s) as services spin up
- Temporary 502/504 responses until startup completes

🧪 To trigger warm-up manually:
- Open Swagger UI or hit a simple `/health` or `/actuator/info` endpoint
- Wait a few seconds for services to fully boot

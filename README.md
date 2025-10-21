# DealerMCP-Lab-Automotive-SpringBoot
A multi-module, production-grade Spring Boot MCP orchestration platform for automotive dealers, built around Postgres, Redis, Kafka/EventHub/Kinesis, with optional Reactive Web and full Kubernetes deployability.

# 🚗 DealerMCP-Lab-Automotive-SpringBoot
> Feasibility & reference implementation of a **Spring Boot + MCP (Model Context Protocol)** orchestration platform for the automotive dealer ecosystem.

---

## 📘 Business Requirements Document (BRD)

### 1️⃣ Purpose
DealerMCP-Lab-Automotive demonstrates how **Spring Boot** can host a fully modular, cloud-deployable **MCP server and API gateway** for dealer networks.  
It explores interoperability, multi-agent AI integration, and reactive event streaming across microservices.

### 2️⃣ Business Objectives

| Objective | KPI |
|------------|-----|
| Establish Spring Boot as a reference MCP backend | Working multi-module MCP orchestration |
| Showcase real-time context-aware communication | < 2 s response latency for MCP tool calls |
| Validate hybrid persistence & messaging | Seamless switch between local and cloud services |
| Provide multi-tenant deployment model | Configurable tenant isolation in Kubernetes |
| Demonstrate AI & event integration | Successful publish/consume via Kafka/Event Hub/Kinesis |

### 3️⃣ Scope

**In Scope**
- Spring Boot 3.x multi-module MCP orchestrator
- Postgres + H2 + Redis integration
- Kafka / Event Hub / Kinesis messaging
- Reactive WebFlux & WebSocket endpoints
- Containerized & deployable to AWS / Azure / GCP Kubernetes
- Common DAO / Service / API / MCP layers

**Out of Scope**
- OEM integrations or external dealer CRMs
- Non-automotive domain data (handled in other DealerMCP labs)

### 4️⃣ Stakeholders

| Role | Responsibility |
|------|----------------|
| Product Owner | Define feature roadmap & feasibility KPIs |
| Engineering Lead | Implement multi-module architecture |
| Cloud Ops | Deploy to AWS EKS / Azure AKS / GCP GKE |
| AI Integrator | Connect external MCP clients / agents |
| QA Engineer | Validate cross-module integration |

### 5️⃣ Success Criteria
- All modules compile under one Maven build
- MCP server exposes JSON-RPC endpoints
- Docker Compose stack runs locally with Postgres, Redis, Kafka
- Deployable Helm chart tested in one cloud environment
- Demonstrated event publish/consume cycle

---

## 📗 Product Requirements Document (PRD)

### 1️⃣ Product Overview
DealerMCP-Lab-Automotive is a **multi-module Spring Boot prototype** implementing the **Model Context Protocol** to unify data and events for automotive dealers.  
It acts as both an **MCP Server** and **API Gateway**, using modular services for catalog, orders, vehicles, and subscriptions.

### 2️⃣ Core Modules (Maven Multi-Module)

| Module | Description |
|---------|-------------|
| `dealer-mcp-auto-common` | Shared constants, DTOs, exceptions, utilities |
| `dealer-mcp-auto-core` | Common interfaces (services / DAOs / events) |
| `dealer-mcp-auto-common-svc` | Generic services (auth, validation, logging) |
| `dealer-mcp-auto-common-dao` | Repositories (JPA / R2DBC) |
| `dealer-mcp-auto-api` | REST / Reactive controllers exposing MCP tools |
| `dealer-mcp-auto-mcp` | Main app: runs MCP Server + API Server |

### 3️⃣ Functional Requirements

| ID | Feature | Priority |
|----|----------|-----------|
| FR-01 | Start combined MCP + API server | 🟢 |
| FR-02 | Register & expose MCP tools (context, query, workflow) | 🟢 |
| FR-03 | Manage Postgres / Redis connections | 🟢 |
| FR-04 | Produce / consume Kafka / Event Hub events | 🟢 |
| FR-05 | Serve WebSocket / Webhook channels via WebFlux | 🟡 |
| FR-06 | Deployable Helm chart for AWS EKS / Azure AKS / GCP GKE | 🟢 |
| FR-07 | Support H2 in-memory DB for tests | 🟢 |
| FR-08 | Unified logging + metrics via Micrometer / Prometheus | 🟡 |

### 4️⃣ Non-Functional Requirements

| Category | Requirement |
|-----------|-------------|
| **Performance** | 95 % ≤ 2 s MCP response |
| **Scalability** | Horizontal pods via K8s |
| **Security** | OAuth2 / JWT / TLS 1.3 |
| **Reliability** | 99.8 % uptime (lab goal) |
| **Portability** | Works on Docker Compose & K8s |
| **Maintainability** | Modular build & code ownership per module |

### 5️⃣ Environments

| Environment | DB | Message Bus | Cache |
|--------------|----|-------------|-------|
| Local Dev | H2 | Embedded Kafka | Redis (local) |
| Docker Lab | Postgres + Redis | Kafka broker | Redis |
| Cloud K8s | RDS / Event Hub / Kinesis | External Kafka | Managed Redis |

---

## 📙 Architecture Specification

### 1️⃣ Overview
DealerMCP-Lab-Automotive implements an **event-driven, reactive, multi-module Spring Boot architecture** that hosts both a **REST API** and a **Model Context Protocol (JSON-RPC over WebSocket)** server.

### 2️⃣ High-Level Diagram

| DealerMCP-Lab-Automotive-SpringBoot                             |           |         |
| --------------------------------------------------------------- | --------- | ------- |
| dealer-mcp-auto-mcp  →  Main App (bootstraps MCP Server)        |           |         |
| ├── dealer-mcp-auto-api  → REST / Reactive Endpoints            |           |         |
| │      ├── dealer-mcp-auto-common-svc (services)                |           |         |
| │      │      ├── dealer-mcp-auto-common-dao (DAOs)             |           |         |
| │      │      └── dealer-mcp-auto-core (interfaces)             |           |         |
| │      └── dealer-mcp-auto-common (utilities & DTOs)            |           |         |
| -------------------------------------------------------------   |           |         |
| Infrastructure:                                                 |           |         |
| Postgres / H2 • Redis • Kafka                                   | Event Hub | Kinesis |
| Reactive WebFlux • JSON-RPC MCP Server • Docker / K8s           |           |         |
| +-------------------------------------------------------------+ |           |         |


### 3️⃣ Data Flow Example
1. **Client / Agent** sends MCP `context.resolve` call.
2. **MCP Server** validates & routes to appropriate module service.
3. **Service Layer** queries Postgres (via R2DBC / JPA).
4. **Cache Layer** checks Redis for hot context data.
5. **Event Layer** publishes `context.resolved` message to Kafka / Event Hub.
6. **Consumers** react and update downstream systems or AI agents.

### 4️⃣ Infrastructure Components

| Component | Technology | Purpose |
|------------|-------------|----------|
| **App Framework** | Spring Boot 3.x + WebFlux | Reactive REST / MCP Server |
| **Database** | Postgres / H2 | Persistent / ephemeral data |
| **Cache** | Redis | Caching + Pub/Sub |
| **Message Bus** | Kafka / Event Hub / Kinesis | Event streaming |
| **Containerization** | Docker / Docker Compose | Local runtime |
| **Cloud Runtime** | Kubernetes (EKS / AKS / GKE) | Production deploy |
| **Monitoring** | Prometheus + Grafana | Metrics / dashboards |
| **Tracing** | OpenTelemetry / Zipkin | Distributed tracing |

### 5️⃣ Deployment Options

| Mode | Stack |
|------|-------|
| **Local Dev** | H2 + Embedded Kafka + Redis |
| **Docker Compose** | Postgres + Redis + Kafka services |
| **Cloud K8s** | External DB / Cache / Stream / LB |

### 6️⃣ Cloud Mapping

| Cloud | DB | Stream | Cache |
|--------|----|--------|-------|
| AWS | RDS (Postgres) | MSK / Kinesis | ElastiCache |
| Azure | Azure DB for Postgres | Event Hub | Azure Redis |
| GCP | Cloud SQL (Postgres) | Pub/Sub | Memorystore |

### 7️⃣ Key MCP Features
- Unified MCP Tool Registry (`registry.json`)
- JSON-RPC 2.0 over WebSocket
- Reactive streaming of context events
- Context graph resolution (Customer → Vehicle → Order → Subscription)
- Multi-agent invocation (Sales, Product, Service, Subscription)
- Event sourcing and journal logging
- Federated context across dealers (tenants)

### 8️⃣ Security Architecture
- OAuth2 / JWT authentication
- Role-based access control per module
- TLS 1.3 for all network traffic
- Per-tenant database schemas
- Audit trail for MCP invocations

### 9️⃣ Observability
- **Metrics** → Micrometer + Prometheus
- **Logs** → ELK or CloudWatch / App Insights
- **Tracing** → OpenTelemetry ID propagation

### 🔟 Scalability
- Stateless microservice pods (scale horizontally)
- Redis cluster for shared cache
- Kafka partitioning for event throughput
- Kubernetes HPA autoscaling

---

## ✅ Summary

DealerMCP-Lab-Automotive-SpringBoot is a **reference architecture** for building MCP-based, event-driven, cloud-ready SaaS backends using **Spring Boot 3.x**.  
It separates reusable components into clean Maven modules and proves how MCP can unify traditional REST APIs, reactive events, and AI agent interactions within one consistent Spring ecosystem.

---



## Maven Multi-Module Structure
```vbnet

DealerMCP-Lab-Automotive-SpringBoot/
│
├── pom.xml  (parent/master POM)
│
├── dealer-mcp-auto-common/
│   └── Common utility classes, constants, DTOs, exceptions, configuration
│
├── dealer-mcp-auto-core/
│   └── Core interfaces (service contracts, DAO interfaces, event interfaces)
│
├── dealer-mcp-auto-common-svc/
│   └── Shared service implementations (logging, auth, error handling)
│
├── dealer-mcp-auto-common-dao/
│   └── Common DAO interfaces and repositories (JPA/R2DBC)
│
├── dealer-mcp-auto-api/
│   └── REST/Reactive controllers exposing MCP APIs and endpoints
│
├── dealer-mcp-auto-mcp/
│   ├── Includes `dealer-mcp-auto-api` as dependency/module
│   ├── Runs both MCP Server + API Server
│   ├── Manages lifecycle, orchestration, message consumption
│   └── Entry point (`Application.java`)
│
└── docker/
    ├── docker-compose.yml (Postgres, Redis, Kafka stack)
    └── Dockerfile (for building app image)

```

## Module Dependency Graph

| Module                       | Depends On                                                 | Description                                          |
| ---------------------------- | ---------------------------------------------------------- | ---------------------------------------------------- |
| `dealer-mcp-auto-common`     | —                                                          | Constants, models, enums, shared config              |
| `dealer-mcp-auto-core`       | `dealer-mcp-auto-common`                                   | Interface definitions (MCP tools, services, DAOs)    |
| `dealer-mcp-auto-common-svc` | `dealer-mcp-auto-core`                                     | Shared service logic: validation, messaging, context |
| `dealer-mcp-auto-common-dao` | `dealer-mcp-auto-core`                                     | Common repository interfaces (R2DBC/JPA)             |
| `dealer-mcp-auto-api`        | `dealer-mcp-auto-common-svc`, `dealer-mcp-auto-common-dao` | Web controllers, DTO mapping                         |
| `dealer-mcp-auto-mcp`        | `dealer-mcp-auto-api`                                      | Main orchestrator module — runs API + MCP servers    |


| Layer                     | Technology                                  | Purpose                        |
| ------------------------- | ------------------------------------------- | ------------------------------ |
| **Framework**             | Spring Boot 3.x                             | Main application framework     |
| **Reactive Web**          | Spring WebFlux                              | WebSocket + Webhook support    |
| **Persistence**           | Postgres (primary), H2 (dev)                | Data storage                   |
| **Cache**                 | Redis                                       | Caching + pub/sub              |
| **Messaging**             | Kafka / Azure Event Hub / AWS Kinesis       | Asynchronous event streams     |
| **ORM / Reactive Access** | JPA / R2DBC                                 | Data access layer              |
| **MCP Protocol**          | JSON-RPC over WebSocket                     | Core protocol layer            |
| **Containerization**      | Docker + Docker Compose                     | Local and CI/CD builds         |
| **Cloud Runtime**         | Kubernetes on AWS EKS / Azure AKS / GCP GKE | Production deployment          |
| **CI/CD**                 | GitHub Actions or Jenkins                   | Build, test, deploy automation |
| **Observability**         | Prometheus + Grafana + ELK                  | Metrics and logs               |

 
## 4. Core MCP Module Responsibilities (dealer-mcp-auto-mcp)
### Responsibilities
- Bootstraps the entire MCP environment (server + API)
- Runs both MCP Orchestrator and REST API Gateway 
- Subscribes to Kafka/EventHub/Kinesis topics 
- Publishes context events (e.g., order.created, subscription.inherit)
- Manages WebSocket connections for reactive streaming

## Cloud Deployment Blueprint

| Component  | AWS                   | Azure                 | GCP           |
| ---------- | --------------------- | --------------------- | ------------- |
| Compute    | EKS (K8s)             | AKS                   | GKE           |
| DB         | RDS (Postgres)        | Azure DB for Postgres | Cloud SQL     |
| Messaging  | MSK (Kafka) / Kinesis | Event Hub             | Pub/Sub       |
| Cache      | ElastiCache (Redis)   | Azure Cache for Redis | Memorystore   |
| Storage    | S3                    | Blob                  | Cloud Storage |
| Monitoring | CloudWatch            | Application Insights  | Stackdriver   |

## Resulting Capabilities

- Modular Spring Boot MCP Architecture 
- Multi-environment: local (H2/Redis) → cloud (Postgres/Kafka)
- Reactive and event-driven 
- Multi-agent ready (AI orchestration via MCP Server)
- Portable to AWS / Azure / GCP 
- Fully Dockerized and Kubernetes deployable

```shell
┌────────────────────────┐
│ Client / AI Agent      │
│ (HTTP POST /execute)   │
└──────────┬─────────────┘
           │
           ▼
┌────────────────────────┐
│ McpServerController     │
│ /dealer-mcp/server/v1   │
└──────────┬─────────────┘
           │
           ▼
┌────────────────────────┐
│ McpExecutor             │
│ (finds tool + validates)│
└──────────┬─────────────┘
           │
           ▼
┌────────────────────────┐
│ McpContextResolveTool   │
│  • Uses ProductContextService │
│  • Builds unified context │
└──────────┬─────────────┘
           │
           ▼
┌────────────────────────┐
│ ProductContextService   │
│  • productDao           │
│  • categoryDao          │
│  • featureDao           │
│  • discountDao          │
└────────────────────────┘

```

## Example Use Cases

| Use Case                             | How `McpContextResolveTool` Helps                                                                                                                 |
| ------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| 🧾 **Product summary for dashboard** | Returns a single object containing all related product info.                                                                                      |
| 💬 **AI assistant / chatbot**        | Lets the model query all relevant product data before responding.                                                                                 |
| 🧩 **Workflow orchestration**        | A `workflow.compose` step can first call `context.resolve` to fetch context, then call other tools like `discount.evaluate` or `feature.extract`. |
| 📊 **Analytics / Auditing**          | Provides “context snapshots” for data audits or lineage.                                                                                          |


## Example Input & Output

### Input
```shell
{
"entityId": "P101"
}
```

### Output
```shell
{
  "id": "P101",
  "name": "All-Season Tire",
  "price": 129.99,
  "category": "Tyres",
  "features": [
    {"code": "F01", "desc": "Radial Construction"},
    {"code": "F02", "desc": "High Grip Rubber Compound"}
  ],
  "discounts": [
    {"code": "D01", "percentage": 10.0}
  ]
}

```

## Comparison with Other MCP Tools

| Tool                 | Scope                           | Output                      | Relationship to `context.resolve`                             |
| -------------------- | ------------------------------- | --------------------------- | ------------------------------------------------------------- |
| `feature.extract`    | Narrow (product → features)     | List of features            | `context.resolve` includes its output inside a bigger context |
| `discount.evaluate`  | Narrow (product → discounts)    | Price & discount details    | `context.resolve` aggregates all of them                      |
| `catalog.aggregate`  | Broad (all categories/products) | Large collection            | `context.resolve` focuses on one entity                       |
| 🧠 `context.resolve` | Central contextual entry point  | Unified view (entity graph) | 🔺 Foundation for all contextual reasoning                    |

### In short:
- McpContextResolveTool is your MCP server’s “context entry point.”
- It’s what any external system, workflow, or model calls when it wants to know “everything relevant about this entity.”

## DealerMCP + n8n combo

“Context-aware automation platform” for anything involving products, categories, discounts, and data orchestration.

It’s literally DealerMCP Orchestration-as-a-Service, powered by n8n’s low-code layer.

### TL;DR Summary (N8N and DealerMCP)
| #  | Workflow Use Case     | Primary MCP Tool(s)                    | Outcome                      |
| -- | --------------------- | -------------------------------------- | ---------------------------- |
| 1  | Contextual Enrichment | `context.resolve`                      | Unified product data         |
| 2  | Dynamic Pricing       | `discount.evaluate`                    | Automated price updates      |
| 3  | Slack Alerts          | `context.resolve`                      | Context change notifications |
| 4  | BI Integration        | `catalog.aggregate`                    | Analytics pipeline           |
| 5  | Chatbot Context       | `context.resolve`                      | AI context injection         |
| 6  | Recommendations       | `context.resolve`, `catalog.aggregate` | Similar product finder       |
| 7  | Catalog QA            | `context.resolve`                      | Data validation              |
| 8  | Discount Tracker      | `discount.evaluate`                    | Price impact analysis        |
| 9  | Document Generator    | `context.resolve`                      | PDF specs auto-generation    |
| 10 | Dealer Sync           | All 4 tools                            | Cross-dealer integration     |

### N8N and DealerMCP Use Cases

| #      | What you automate                               | MCP tool you call                       | Example n8n flow                                                                                                      |
| ------ | ----------------------------------------------- | --------------------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| **1**  | Enrich new product records                      | `context.resolve`                       | *Trigger:* new record in DB → *HTTP node:* `POST /execute/context.resolve` → *Write:* result into your product sheet. |
| **2**  | Auto-calculate discounts daily                  | `discount.evaluate`                     | *Cron node* → *HTTP node* to MCP → *Write* back to ERP/CRM.                                                           |
| **3**  | Alert sales team when price or discount changes | `context.resolve`                       | *Webhook trigger* → *HTTP node* → *Slack node* send message.                                                          |
| **4**  | Push catalog snapshot to BI                     | `catalog.aggregate`                     | *Hourly cron* → *HTTP node* → *BigQuery/Sheets node*.                                                                 |
| **5**  | Give chatbots live product data                 | `context.resolve`                       | *Webhook from chat app* → *HTTP node* → *OpenAI node* with MCP JSON as context.                                       |
| **6**  | Recommend similar products                      | `context.resolve` + `catalog.aggregate` | *HTTP node* → get category → *HTTP node* → fetch other products in same category.                                     |
| **7**  | Validate catalog completeness                   | `context.resolve`                       | *Loop node* over IDs → *HTTP node* → *If node* checks missing fields → *Email node* summary.                          |
| **8**  | Track discount effectiveness                    | `discount.evaluate`                     | *Daily cron* → *HTTP node* → *Write* to analytics DB or Sheet.                                                        |
| **9**  | Auto-generate product PDFs                      | `context.resolve`                       | *Webhook trigger* → *HTTP node* → *HTML/PDF node* → *Email node*.                                                     |
| **10** | Sync products between dealers                   | all tools                               | *Webhook trigger* → *HTTP nodes* calling MCP → *HTTP node* to partner API.                                            |

### Workflow-Driven Product Recommendations - Additional N8N and DealerMCP Use Cases

10 Real-World Use Cases: DealerMCP Server + n8n

| #                                                       | Use Case                                                                       | Goal                                                                                                                                                                                                                                                                                                                      | Workflow (Step-by-Step)                                                    | Outcome |
| ------------------------------------------------------- | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------- | ------- |
| **1️⃣ Contextual Product Enrichment**                   | Automatically build complete product data records when new products are added. | **1.** n8n detects a *new product* event (via webhook or DB trigger).<br>**2.** Calls → `/dealer-mcp/server/v1/execute/context.resolve` with `{"entityId":"P101"}`.<br>**3.** Receives enriched JSON (features, category, discounts).<br>**4.** Pushes the data into your Product Master Sheet or PostgreSQL.             | Master data stays automatically enriched with contextual attributes.       |         |
| **2️⃣ Automated Product Pricing & Discount Evaluation** | Continuously monitor and compute final discounted prices.                      | **1.** Cron node triggers daily.<br>**2.** Calls → `/execute/context.resolve` → fetch product data.<br>**3.** Calls → `/execute/discount.evaluate` → calculate final price.<br>**4.** Writes updated price to ERP, Shopify, or Salesforce.                                                                                | Dynamic, rule-based pricing without manual intervention.                   |         |
| **3️⃣ Slack / Teams Product Insight Alerts**            | Notify sales or marketing when a product context changes.                      | **1.** Webhook or Kafka node triggers on MCP `context.updated` event.<br>**2.** Calls → `/execute/context.resolve` for the updated product.<br>**3.** Formats the JSON response into a readable message.<br>**4.** Sends it to Slack or Microsoft Teams channel.                                                          | Real-time, human-readable product insights in chat.                        |         |
| **4️⃣ Product Analytics Pipeline**                      | Stream MCP data into analytics or BI systems.                                  | **1.** Cron node (hourly) calls → `/execute/catalog.aggregate`.<br>**2.** Aggregates catalog data (products, discounts, categories).<br>**3.** Transforms data as needed using Function/Set node.<br>**4.** Pushes results into BigQuery, Snowflake, or Power BI API.                                                     | Always up-to-date analytics view of your entire catalog.                   |         |
| **5️⃣ LLM / Chatbot Context Connector**                 | Provide chatbot or AI assistant with contextual product data.                  | **1.** n8n receives a query like “Tell me about product P101.”<br>**2.** Calls → `/execute/context.resolve` to get JSON context.<br>**3.** Passes the context JSON to OpenAI or ChatGPT node with prompt: “Use this context to answer: {{$json}}.”<br>**4.** Sends generated response back via Slack, Teams, or web chat. | Intelligent, data-grounded AI responses using DealerMCP context.           |         |
| **6️⃣ Workflow-Driven Product Recommendations**         | Suggest related products dynamically.                                          | **1.** User views product P101 → webhook triggers n8n.<br>**2.** Calls → `/execute/context.resolve` to fetch context.<br>**3.** Reads category or features fields.<br>**4.** Calls → `/execute/catalog.aggregate` to find similar items.<br>**5.** Returns 3–5 similar products as a JSON response.                       | Zero-code recommendation engine powered by MCP.                            |         |
| **7️⃣ Product Catalog QA & Validation**                 | Validate product catalog completeness automatically.                           | **1.** n8n reads a list of product IDs from DB or CSV.<br>**2.** For each ID:<br>  a. Calls `/execute/context.resolve`.<br>  b. Validates presence of required fields (e.g., price, features).<br>**3.** Collects missing/invalid field info.<br>**4.** Sends a summary report via email.                                 | Automated data governance and catalog quality checks.                      |         |
| **8️⃣ Discount Performance Tracker**                    | Calculate and log discount impact over time.                                   | **1.** Cron node triggers daily or weekly.<br>**2.** Calls → `/execute/discount.evaluate` for all active products.<br>**3.** Logs results (base vs final price) into Google Sheets or DB.<br>**4.** Sends alert if discount > defined threshold.                                                                          | Continuous tracking of discount performance and margins.                   |         |
| **9️⃣ Automated Product Document Generation**           | Auto-generate product spec sheets or promotional materials.                    | **1.** Trigger: New product added or updated event.<br>**2.** Calls → `/execute/context.resolve` → fetch full product context.<br>**3.** Passes context JSON to PDF Generator or HTML Template node.<br>**4.** Emails the PDF or uploads it to S3 / OneDrive.                                                             | Always-updated, auto-generated product documents and marketing materials.  |         |
| **🔟 Dealer Integration Workflow (Multi-dealer Sync)**  | Sync product and discount data across dealer systems.                          | **1.** Dealer A posts a new product → webhook triggers n8n.<br>**2.** Calls → `/execute/context.resolve` to fetch canonical version.<br>**3.** Normalizes and posts the data to Dealer B’s API.<br>**4.** Calls → `/execute/discount.evaluate` for localized pricing.                                                     | Cross-dealer data consistency with zero manual intervention.               |         |
| **🔄 Bonus: Workflow Compose in N8N**                   | Chain multiple MCP tools visually inside n8n.                                  | **1.** Node 1 executes `/execute/context.resolve`.<br>**2.** Node 2 executes `/execute/feature.extract`.<br>**3.** Node 3 executes `/execute/discount.evaluate`.<br>**4.** Final node writes combined results to DB or sends Slack alert.                                                                                 | Visual “WorkflowComposeTool” powered by n8n for end-to-end MCP automation. |         |


### 50 Additional DealerMCP + n8n Use-Case Ideas (Summary Table)

| #   | Use Case                             | Goal / Description                                                  |
| --- | ------------------------------------ | ------------------------------------------------------------------- |
| 11  | **Inventory Auto-Sync**              | Keep DealerMCP stock levels synchronized with warehouse APIs.       |
| 12  | **Vendor Feed Integration**          | Import supplier catalogs into DealerMCP using n8n FTP + HTTP nodes. |
| 13  | **Price-Change Notifier**            | Compare yesterday’s and today’s catalog data; alert if delta > x%.  |
| 14  | **Auto-Publish New Products**        | Push newly added items from DealerMCP to Shopify / WooCommerce.     |
| 15  | **Customer → Vehicle Mapping**       | Automatically link new customers to their vehicle subscriptions.    |
| 16  | **Subscription Expiry Reminder**     | Email customers before service or subscription end dates.           |
| 17  | **Warranty Registration Workflow**   | Generate warranty PDFs and email confirmation on product sale.      |
| 18  | **After-Sales Feedback Loop**        | Send survey link after a purchase using MCP context data.           |
| 19  | **Parts Availability Checker**       | Chain `context.resolve` + external OEM API to verify part stock.    |
| 20  | **Dynamic Banner Generator**         | Create marketing banners with current discount data.                |
| 21  | **Promotion Approval Flow**          | Route discount proposals for manager approval via Slack.            |
| 22  | **Finance Offer Evaluator**          | Combine product price + finance rules to calculate EMI offers.      |
| 23  | **Lead Scoring Automation**          | Merge CRM leads with product interests from DealerMCP.              |
| 24  | **Email Personalization Engine**     | Use MCP data to inject personalized product details into Mailchimp. |
| 25  | **Bulk Context Exporter**            | Export full context graphs for 10 k products nightly.               |
| 26  | **AI Pricing Advisor**               | Feed MCP data to OpenAI node to suggest optimal prices.             |
| 27  | **Vehicle Recall Notifier**          | Match VINs from OEM recall feed to DealerMCP customers.             |
| 28  | **Regional Tax Calculator**          | Apply local tax tables after `discount.evaluate`.                   |
| 29  | **Product Comparison Sheet**         | Generate Excel comparing specs of similar products.                 |
| 30  | **Social Media Auto-Poster**         | Publish top discounted items to LinkedIn/Twitter.                   |
| 31  | **Voice Assistant Integration**      | Query `context.resolve` via Alexa/Google Actions webhook.           |
| 32  | **Parts Return Authorization**       | Auto-create return tickets when discount > threshold.               |
| 33  | **Catalog Translation Pipeline**     | Translate product descriptions via DeepL node.                      |
| 34  | **Dealer KPI Dashboard**             | Combine sales + MCP analytics for Power BI updates.                 |
| 35  | **Fraudulent Order Detector**        | Correlate abnormal discounts with order logs.                       |
| 36  | **Customer Segmentation Workflow**   | Group customers by product context (category, spend).               |
| 37  | **Predictive Stock Replenishment**   | Trigger reorder when MCP stock < forecast threshold.                |
| 38  | **VIN Lookup Service**               | Wrap `context.resolve` around VIN → vehicle data enrichment.        |
| 39  | **PDF Invoice Composer**             | Merge MCP pricing + customer data → invoice PDF.                    |
| 40  | **Dealer Performance Scorer**        | Compute KPIs by pulling MCP sales + discount data.                  |
| 41  | **Geo-Based Dealer Routing**         | Suggest nearest dealer using product + location context.            |
| 42  | **Seasonal Offer Scheduler**         | Auto-activate discounts on specific dates.                          |
| 43  | **Stock Aging Report**               | Calculate how long items stayed unsold; email summary.              |
| 44  | **Supplier Performance Tracker**     | Measure delivery SLA vs MCP orders.                                 |
| 45  | **Webhook-to-API Bridge**            | Turn MCP events into third-party API calls.                         |
| 46  | **Order Fulfillment Checker**        | Compare MCP order → shipment statuses nightly.                      |
| 47  | **Dealer Onboarding Automation**     | Auto-create dealer accounts and seed base catalog.                  |
| 48  | **Vehicle Service Scheduler**        | Auto-schedule services using subscription context.                  |
| 49  | **Product Lifecycle Alerts**         | Notify when a product is nearing end-of-life.                       |
| 50  | **Data Backup and Sync**             | Backup MCP context data to S3 / Azure Blob.                         |
| 51  | **AI Review Summarizer**             | Summarize customer reviews with MCP context for analytics.          |
| 52  | **Delivery ETA Predictor**           | Combine MCP order data + logistics API.                             |
| 53  | **Multi-Language Chatbot Context**   | Serve localized MCP context to LLM nodes.                           |
| 54  | **Stock Shortage Broadcast**         | Notify all dealers when stock < threshold.                          |
| 55  | **Parts Cross-Reference Tool**       | Map OEM part numbers via `context.resolve`.                         |
| 56  | **Service History Builder**          | Aggregate all service records under each vehicle context.           |
| 57  | **Insurance Quote Generator**        | Use MCP vehicle data to pre-fill insurance forms.                   |
| 58  | **Customer Loyalty Points Updater**  | Update loyalty points after `discount.evaluate`.                    |
| 59  | **Product Metadata Normalizer**      | Clean & standardize external feeds using MCP schemas.               |
| 60  | **AI Spec Writer**                   | Generate marketing copy from `context.resolve` output.              |
| 61  | **Predictive Maintenance Notifier**  | Predict failures from product context + usage data.                 |
| 62  | **Integration with Azure Event Hub** | Publish MCP tool outputs to Event Hub streams.                      |
| 63  | **Workflow Audit Logger**            | Save every MCP execution log into an audit DB.                      |
| 64  | **Parts Recommendation Engine**      | Suggest compatible parts using feature relationships.               |
| 65  | **Cloud Migration Sync**             | Copy MCP data from on-prem DBs to cloud storage.                    |
| 66  | **ElasticSearch Indexer**            | Index MCP contexts for fast search.                                 |
| 67  | **Customer Churn Analyzer**          | Detect at-risk customers via subscription data.                     |
| 68  | **Email Failure Recovery**           | Retry failed transactional emails with MCP context.                 |
| 69  | **Sales Forecast Generator**         | Feed MCP sales data into ML forecasting API.                        |
| 70  | **Regulatory Compliance Checker**    | Validate that catalog items meet region standards.                  |
| 71  | **AI Image Tagger**                  | Auto-tag product images using MCP context.                          |
| 72  | **Coupon Code Distributor**          | Send targeted coupons from `discount.evaluate`.                     |
| 73  | **Data Anonymization Flow**          | Remove PII before exporting MCP datasets.                           |
| 74  | **Dealer Feedback Collector**        | Gather dealer reviews and attach to context graphs.                 |
| 75  | **Context-Aware Workflow Router**    | Route tasks based on MCP context type (Product vs Vehicle).         |
| 76  | **API Latency Monitor**              | Measure response time of each MCP tool execution.                   |
| 77  | **Cloud Backup Verifier**            | Validate backups of MCP DBs nightly.                                |
| 78  | **Elastic Pricing Rule Updater**     | Update pricing rules dynamically from BI outputs.                   |
| 79  | **Energy Consumption Tracker**       | Combine MCP product data with IoT metrics.                          |
| 80  | **Voice Call Summarizer**            | Enrich call logs with MCP product context.                          |
| 81  | **Dealer Ranking Generator**         | Rank dealers based on MCP KPIs.                                     |
| 82  | **Product Recall Automation**        | Auto-notify affected customers using context links.                 |
| 83  | **Image Quality Auditor**            | Detect missing or poor images across catalog.                       |
| 84  | **Customer Onboarding Checklist**    | Create tasks based on subscription context.                         |
| 85  | **Predictive Offer Engine**          | Generate next-best-offer using discount history.                    |
| 86  | **EDI Integration**                  | Exchange order data with ERP via EDI nodes.                         |
| 87  | **IoT Alert Processor**              | Trigger maintenance flows from sensor events.                       |
| 88  | **AI Trend Reporter**                | Summarize weekly sales/discount trends via GPT node.                |
| 89  | **Warranty Expiration Monitor**      | Notify customers before warranty expiry.                            |
| 90  | **Dealer Training Scheduler**        | Auto-invite dealers to training based on new catalog items.         |
| 91  | **Context Delta Tracker**            | Log what changed between two `context.resolve` outputs.             |
| 92  | **Search Engine Feed Generator**     | Build product feeds for Google Merchant Center.                     |
| 93  | **API Key Usage Monitor**            | Count how often each MCP tool is invoked.                           |
| 94  | **Bulk Tool Executor**               | Iterate through all MCP tools for QA testing.                       |
| 95  | **OpenAPI Schema Exporter**          | Export tool schemas to external documentation.                      |
| 96  | **Kubernetes Scaler Trigger**        | Scale MCP pods up/down based on execution load.                     |
| 97  | **Dealer ROI Analyzer**              | Combine sales + discount data per dealer.                           |
| 98  | **Customer Complaint Classifier**    | Tag complaints using MCP context categories.                        |
| 99  | **GraphQL Gateway Builder**          | Wrap MCP REST tools under GraphQL endpoint.                         |
| 100 | **Automated Swagger Updater**        | Regenerate API docs after new tools register.                       |
| 101 | **LLM Tool Tester**                  | Validate AI function-calling against MCP schemas.                   |
| 102 | **Compliance Audit Trail Generator** | Export audit trails of all executed MCP tools.                      |
| 103 | **Product Heatmap Dashboard**        | Visualize context graph activity per category.                      |
| 104 | **Dealer Chatbot Integration**       | Give every dealer an MCP-powered chatbot node.                      |
| 105 | **Predictive Demand Planner**        | Forecast part demand per region using MCP data.                     |
| 106 | **Automated Version Control**        | Snapshot MCP schemas and diffs in Git.                              |
| 107 | **Sales Commission Calculator**      | Compute dealer commissions from MCP sales data.                     |
| 108 | **AI Alert Generator**               | Detect anomalies in discount patterns via AI node.                  |
| 109 | **Context Graph Visualizer**         | Render MCP context links in n8n UI dashboard.                       |
| 110 | **MCP Health Check Flow**            | Ping all MCP tools, log latency & status daily.                     |


### 
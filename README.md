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
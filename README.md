# Marketplace OS/Arch Variant Resolver (Kotlin + Spring Boot)

Minimal Spring Boot service that models plugin versions per OS/Arch and resolves the best match for a client.

## Features
- Simple in-memory store of plugins and versions
- Resolver picks exact OS+Arch match or falls back to universal (legacy) versions
- REST API: `GET /api/plugins/{pluginId}/resolve?os=WINDOWS&arch=X64`
- Unit and integration tests included

## Run
Requirements: JDK 17, Gradle
```bash
./gradlew bootRun
```
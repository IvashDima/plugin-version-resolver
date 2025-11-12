# Plugin Version Resolver

Minimal Spring Boot service that models plugin versions per OS/Arch and resolves the best match for a client.

## 🧩 Overview
Each plugin has multiple versions, targeted to specific OS and architecture combinations (e.g. `WINDOWS/X64`, `LINUX/ARM`).  
The service resolves the **best matching version** for a given request.

- If an exact OS/arch match exists → returns the latest one.
- Otherwise → falls back to a universal version.
- If no version found → returns `404 Plugin not found`.

## 🚀 Run

```bash
./gradlew bootRun
```

## 🧪 Example
Positive case:
```bash
curl "http://localhost:8080/api/plugins/editor/resolve?os=WINDOWS&arch=X64"
```
✅ Response:
```json
{
"pluginId": "editor",
"version": "2.3.1",
"osVariants": ["WINDOWS_X64"],
"releaseDate": "2025-01-12"
}
```
Negative case:
```bash
curl -v 'http://localhost:8080/api/plugins/unknown/resolve?os=WINDOWS&arch=X64'
```
❌ Response:
```json
{
"status": 404,
"error": "Not Found",
"message": "Plugin unknown not found",
"timestamp": 1731448914123
}
```

## 🧠 Tests
```bash
./gradlew test
```
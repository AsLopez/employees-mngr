# Employees-mngr

## Getting Started

### Prerequisites

- Java 8
- maven

### Installation

#### - Install dependencies

```sh
mvn clean install -Dmaven.test.skip=true
```

## Development

### Run server

```sh
mvn spring-boot:run
```

### Test

```sh
mvn verify
```

---

## Resources
| Method | Path                   | Description        |
|--------|------------------------|--------------------|
| GET    | /health                | Get app status     |
| POST   | /company/employee      | Get employee list  |
| GET    | /company/employee/{id} | Get employee by id |

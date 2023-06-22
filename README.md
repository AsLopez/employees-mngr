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

## Resources:

| Method | Path                   | Description        |
|--------|------------------------|--------------------|
| GET    | /health                | Get app status     |
| POST   | /company/employee      | Get employee list  |
| GET    | /company/employee/{id} | Get employee by id |


---

# Execute project in local environment

In the apache-tomcat-9.0.76/webapps folder there is the generated .war file, but the .war can be regenerated and replaced

## 1. Start Tomcat 
Running the “catalina.sh” or “catalina.bat” script in bin directory.

- Step 1: `cd apache-tomcat-9.0.76/bin`

- Step 2: `sh catalina.sh run`

## 2. Access the application 

Navigate to “http://localhost:8080/{war-file-name}" in your web browser.

Example: `http://localhost:8080/EM/company/employees`

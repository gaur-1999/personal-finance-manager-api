# Personal Finance Manager API

A REST API built with Spring Boot for managing personal finances - track income, expenses, and monthly savings.

## Tech Stack
- Java 17
- Spring Boot 3.2
- Spring Security + JWT
- MySQL
- Maven

## Features
- User Registration & Login with JWT Authentication
- Add, Edit, Delete Transactions
- Filter by Type (INCOME/EXPENSE)
- Filter by Category
- Monthly Summary with Net Savings

## API Endpoints

### Auth
| Method | URL | Description |
|--------|-----|-------------|
| POST | /api/auth/register | Register new user |
| POST | /api/auth/login | Login & get JWT token |

### Transactions
| Method | URL | Description |
|--------|-----|-------------|
| POST | /api/transactions | Add transaction |
| GET | /api/transactions | Get all transactions |
| PUT | /api/transactions/{id} | Update transaction |
| DELETE | /api/transactions/{id} | Delete transaction |
| GET | /api/transactions/type/{type} | Filter by INCOME/EXPENSE |
| GET | /api/transactions/category/{category} | Filter by category |
| GET | /api/transactions/summary/{year}/{month} | Monthly summary |

## Setup

### Prerequisites
- Java 17
- MySQL
- Maven

### Run Locally
```bash
git clone https://github.com/gaur-1999/personal-finance-manager-api.git
cd personal-finance-manager-api
```

Configure `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=root
spring.datasource.password=your_password
```

```bash
mvn spring-boot:run
```

# PayX Wallet – Digital Wallet System (Spring Boot + MongoDB)

A backend implementation of a lightweight **digital wallet system**, built using **Spring Boot**, **MongoDB**, and **REST APIs**.  
This project is designed for backend developers who want to learn Spring Boot by building a real, production-style application.

---

## 🚀 Features

### User Management
- Register new users  
- Fetch user details  
- Validate mobile number and email  
- Store user profiles in MongoDB  

### Health Monitoring
- Lightweight `/api/health` endpoint for readiness checks

### Upcoming Modules
- Wallet creation
- Add money to wallet
- Transaction history
- Merchant payments
- JWT Authentication
- API Gateway
- Kafka event-driven payments

---

## 🛠️ Tech Stack

| Technology | Purpose |
|-----------|----------|
| **Java 17** | Programming language |
| **Spring Boot 3.x** | Backend framework |
| **Spring Web** | REST API development |
| **Spring Data MongoDB** | MongoDB ORM |
| **Spring Validation** | Request validation |
| **Maven** | Build tool |
| **MongoDB** | NoSQL database |
| **Postman / Curl** | API testing |

---

## 📁 Project Structure

```
payx-wallet/
 ├── src/main/java/com/payx/payxwallet/
 │    ├── PayxWalletApplication.java
 │    ├── health/
 │    │    └── HealthController.java
 │    ├── user/
 │    │    ├── User.java
 │    │    ├── UserRepository.java
 │    │    ├── UserService.java
 │    │    ├── UserController.java
 │    │    └── dto/
 │    │         ├── UserRegistrationRequest.java
 │    │         └── UserResponse.java
 │
 ├── src/main/resources/
 │    └── application.properties
 │
 ├── pom.xml
 └── README.md
```

---

## ⚙️ How to Run the Project

### Prerequisites
- Java 17+
- Maven 3.8+
- MongoDB (default port 27017)

---

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPO.git
cd payx-wallet
```

### 2. Configure MongoDB

```properties
spring.application.name=payx-wallet
spring.data.mongodb.uri=mongodb://localhost:27017/payx_wallet_db
server.port=8083
```

### 3. Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

---

## 📡 API Endpoints

### Health Check
```
GET /api/health
Response: "OK"
```

---

### Register User
```
POST /api/users
```

**Request Body**
```json
{
  "fullName": "Ravi Prakash",
  "email": "ravi@example.com",
  "mobileNumber": "9876543210"
}
```

---

### Get All Users
```
GET /api/users
```

### Get User By ID
```
GET /api/users/{id}
```

---

## 🧪 Testing APIs (curl)

```bash
curl -X POST http://localhost:8083/api/users   -H "Content-Type: application/json"   -d '{
        "fullName":"Ravi",
        "email":"ravi@example.com",
        "mobileNumber":"9876543210"
      }'
```

---

## 🔮 Future Enhancements

- Wallet module
- Payments module
- JWT authentication
- Kafka integration
- Global exception handler
- Docker support
- Testcontainers integration

---

## 🤝 Contributing

Pull requests are welcome.

---

## 📄 License

MIT License


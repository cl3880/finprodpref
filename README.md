# \[新進人員]玉山銀行後端工程師-Java 實作題

A full-stack financial product management system built with Spring Boot, Vue.js, and PostgreSQL.

## Overview

This system implements CRUD operations for managing a user's liked list of financial products with the following *user specifications*:

1. **新增喜好金融商品** 
    - 使用者可以透過介面進行新增所喜好的金融商品資訊(產品名稱、產品價格、手續費率)、預計要扣款的帳號、購買數量。

2. **查詢喜好金融商品清單** 
    - 使用者可以透過介面進行查詢所喜好的金融商品名稱清單以及預計要扣款的帳號、預計扣款總金額、總手續費用、扣款帳號、使用者聯絡電子信箱。

3. **更改喜好金融商品資訊**
    - 使用者可以透過介面進行更改所喜好的金融商品資訊(產品名稱、產品價格、手續費率)、預計要扣款的帳號、購買數量。

4. **刪除喜好金融商品資訊**
    - 使用者可以透過介面進行刪除所喜好的金融商品資訊(產品名稱、產品價格、手續費率)。

## Architecture

- **Frontend**: Vue.js with Vite
- **Backend**: Spring Boot with RESTful API
- **Database**: PostgreSQL with Stored Procedures
- **Build Tool**: Maven
- **Containerization**: Docker Compose

## Quick Start

### Option 1: Docker One Command

**Prerequisites**: Docker & Docker Compose

```bash
# Clone and start everything
git clone https://github.com/cl3880/finprodpref.git
cd finprodpref
docker compose up --build
```

**Access the application:**
- **Frontend**: http://localhost:4173
- **Backend API**: http://localhost:8080/api
- **Database**: localhost:5432

### Option 2: Manual Dev Setup

**Prerequisites**: Java 17+, Node.js 18+, PostgreSQL

```bash
# 1. Start PostgreSQL database
docker compose up postgres -d

# 2. Start backend (separate terminal)
cd backend
mvn spring-boot:run

# 3. Start frontend (separate terminal)
cd frontend
npm install
npm run dev
```

**Access the application:**
- **Frontend**: http://localhost:5173
- **Backend API**: http://localhost:8080/api
- **Database**: localhost:5432

## API Testing

### Using Postman

1. Install [Postman](https://www.postman.com/downloads/)

2. Import `postman/FinProdPref.postman_collection.json` file

3. The collection uses `{{baseUrl}}` variable set to `http://localhost:8080` (works for both Docker and manual setup)

4. Test any endpoint by expanding the collection and clicking **Send**

### Available Endpoints
- `GET /api/products` - Get all available products
- `GET /api/users/{userId}/accounts` - Get user accounts
- `GET /api/liked-products/{userId}` - Get user's liked products
- `POST /api/liked-products` - Add liked product
- `PUT /api/liked-products` - Update liked product
- `DELETE /api/liked-products/{userId}/{productNo}/{account}` - Delete liked product

## Database

The database automatically initializes with sample data:
- **Users**: Sample users with multiple accounts
- **Products**: NTD-denominated financial products
- **User Accounts**: Multiple accounts per user support

All data access is performed through stored procedures as required.

## Design Assumptions

### Schema

User specification 2 and 4 implies a user can have multiple accounts, and liked product can be modified to user's other account.

### `JdbcTemplate`

JDBC Template was chosen over JPA/Hibernate due to "透過 Stored Procedure 存取資料庫" requirement and managing schema through DDL and DML scripts. Avoided JPA/Hibernate due to potential complexity and mapping issues.

### Currency

The project requirement specifies that the "Total Fee" should be priced in NTD ("台幣計價").

To deliver a functional application within the project's scope and time limit, all product prices stored in the database are assumed to be in New Taiwan Dollars (NTD).

To accommodate global products a multi-currency conversion system would have to be implemented in the future.



## Security Features

- Parameterized queries prevent SQL injection
- CORS configuration for frontend integration
- Input validation with Bean Validation
- Global exception handling with proper HTTP status codes
- XSS prevention through output escaping and Vue's default data binding

## Full API Reference

See [API Documentation](backend/API.md) for detailed endpoints, request/response examples, and usage.
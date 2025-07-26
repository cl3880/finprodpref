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

- **Frontend**: Vue.js
- **Backend**: Spring Boot with RESTful API
- **Database**: PostgreSQL with Stored Procedures
- **Build Tool**: Maven
- **Containerization**: Docker Compose

## Quick Start

### Prerequisites
- Java 17+
- Docker & Docker Compose
- Node.js 18+ (for frontend)
- Git

## API Testing

1. Make sure [Postman](https://www.postman.com/downloads/) and Spring Boot app are installed and running.

2. Import the `FinProdPref.postman_collection.json` file. The "FinProdPref API" collection will now appear in your Postman sidebar. The collection uses a variable `{{baseUrl}}` to define the server address, which default, it's set to `http://localhost:8080`.

3. Expand the "FinProdPref API" collection in the sidebar &rarr; Click on any request (e.g., "Get All Products") &rarr; Click the **Send** button

### Available Endpoints
- `GET /api/products` - Get all available products
- `GET /api/users/{userId}/accounts` - Get user accounts
- `GET /api/liked-products/{userId}` - Get user's liked products
- `POST /api/liked-products` - Add liked product
- `PUT /api/liked-products` - Update liked product
- `DELETE /api/liked-products/{userId}/{productNo}/{account}` - Delete liked product

### Full API Reference
See [API Documentation](backend/API.md) for detailed endpoints, request/response examples, and usage.


## Database

The database automatically initializes with sample data when Docker starts:
- **Users**: Sample users with multiple accounts
- **Products**: NTD-denominated financial products
- **User Accounts**: Multiple accounts per user support

All data access is performed through stored procedures as required.

## Design Assumptions

### Schema

User specification 2 and 4 implies a user can have multiple accounts, and liked product can be modified to user's other account.

### `JdbcTemplate`

JDBC Template was chosen over JPA/Hibernate due to “透過 Stored Procedure 存取資料庫” requirement and managing schema through DDL and DML scripts. Avoided JPA/Hibernate due to potential complexity and mapping issues.

### Currency

The project requirement specifies that the "Total Fee" should be priced in NTD ("台幣計價").

To deliver a functional application within the project's scope and time limit, let all product prices stored in the database are assumed to be in New Taiwan Dollars (NTD).

To accomadate global products a multi-currency conversion system would have to be implemented in the future.

## Project Structure

```
finprodpref/
├── backend/                # Spring Boot application
├── frontend/               # Vue.js application
├── DB/                     # DB scripts (DDL, DML, Stored Procedures)
├── postman/                # API testing collection
└── docker-compose.yml      
```

## Security Features

- Parameterized queries prevent SQL injection
- CORS configuration for frontend integration
- Input validation with Bean Validation
- Basic global exception handling
- XSS prevented by escaping output on the backend as well as Vue's default data binding on frontend.
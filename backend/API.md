# API Documentation

## Base URL
`http://localhost:8080/api`

---

## 1. Liked Products

### Add a Liked Product
*   **Endpoint**: `POST /api/liked-products`
*   **Description**: Adds a new product to a user's liked list.
*   **Request Body**:
    ```json
    {
      "userId": "A123456789",
      "productNo": "2330",
      "quantity": 10,
      "newAccount": "1111999666"
    }
    ```
*   **Responses**:
    *   `201 Created`: Product successfully added to liked list.
    *   `400 Bad Request`: Invalid request body or validation errors.
    *   `409 Conflict`: Product already exists in liked list for the specified account.

### Get Liked Products for a User
*   **Endpoint**: `GET /api/liked-products/{userId}`
*   **Description**: Retrieves a list of all products liked by a specific user.
*   **Path Parameters**:
    *   `userId` (string): The user ID (e.g., "A123456789")
*   **Response Body**:
    ```json
    [
      {
        "productName": "台積電",
        "account": "1111999666",
        "totalAmount": 9493.51,
        "totalFee": 13.51,
        "userEmail": "test@email.com"
      }
    ]
    ```
*   **Responses**:
    *   `200 OK`: Successfully retrieved liked products list.

### Update a Liked Product
*   **Endpoint**: `PUT /api/liked-products`
*   **Description**: Updates the quantity and/or account for a product in the user's liked list.
*   **Request Body**:
    ```json
    {
      "userId": "A123456789",
      "productNo": "2330",
      "oldAccount": "1111999666",
      "newAccount": "1111888555",
      "quantity": 20
    }
    ```
*   **Responses**:
    *   `204 No Content`: Update successful.
    *   `400 Bad Request`: Invalid request body or validation errors.
    *   `404 Not Found`: Product not found in user's liked list.
    *   `409 Conflict`: New account/product combination already exists in liked list.

### Delete a Liked Product
*   **Endpoint**: `DELETE /api/liked-products/{userId}/{productNo}/{account}`
*   **Description**: Removes a product from a user's liked list.
*   **Path Parameters**:
    *   `userId` (string): The user ID (e.g., "A123456789")
    *   `productNo` (string): The product number (e.g., "2330")
    *   `account` (string): The account number (e.g., "1111999666")
*   **Responses**:
    *   `204 No Content`: Deletion successful.
    *   `404 Not Found`: Product not found in user's liked list.

---

## 2. Products

### Get All Products
*   **Endpoint**: `GET /api/products`
*   **Description**: Retrieves a list of all available financial products.
*   **Response Body**:
    ```json
    [
      {
        "productNo": "2330",
        "productName": "台積電",
        "price": 948.00,
        "feeRate": 0.0014
      },
      {
        "productNo": "00929",
        "productName": "玉山永續高息ETF",
        "price": 19.85,
        "feeRate": 0.0010
      }
    ]
    ```
*   **Responses**:
    *   `200 OK`: Successfully retrieved products list.

---

## 3. User Accounts

### Get All Accounts for a User
*   **Endpoint**: `GET /api/users/{userId}/accounts`
*   **Description**: Retrieves a list of all bank accounts associated with a user.
*   **Path Parameters**:
    *   `userId` (string): The user ID (e.g., "A123456789")
*   **Response Body**:
    ```json
    [
      "1111999666",
      "1111888555"
    ]
    ```
*   **Responses**:
    *   `200 OK`: Successfully retrieved user accounts.

---

## Error Handling

### Global Exception Responses

*   **409 Conflict**: When trying to add a product that already exists in the liked list
    ```json
    {
      "error": "Product is already in liked list for the selected account."
    }
    ```

*   **400 Bad Request**: For validation errors
    ```json
    {
      "error": "Validation failed",
      "details": ["Quantity must be at least 1", "User ID is required"]
    }
    ```

---

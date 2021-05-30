
# API Docs

## Account
Handle account creation and search

### POST /accounts

Create new account

#### Payload
| Name      | Description | Example | Required                                                                                      |
| ----------|-------------|---------|----------------------------------------------------------------------- |
| document_number       | Document number of the Account owner | 12345 | true


#### Request
```
curl -X POST http://localhost:8080/accounts -H 'Content-Type: application/json' -d '{ "document_number": "1234562213" }'
```


#### Response

`HTTP 201`
```json
{
	"account_id": 1,
	"document_number": "1234562213"
}
```
`HTTP 400`
```json
{
  "status": 400,
  "message": "Your request body contains invalid data",
  "details": [
    "document_number cannot be blank",
    "document_number cannot be null"
  ]
}
```
`HTTP 422`
```json
{
  "status": 422,
  "message": "An account with this document number already exists"
}
```

### GET /accounts/:accountId
Returns account information

#### Params
| Name      | Description | Example | Required                                                                                     |
| ----------|-------------|---------|------------------------------------------------------------------------------ |
| accountId       | Customer account id | 12 | true


#### Request
```
curl -X GET http://localhost:8080/accounts/1
```

#### Response

`HTTP 200`
```json
{
	"account_id": 1,
	"document_number": "12345"
}
```
`HTTP 404`
```json
{
  "status": 404,
  "message": "Account not found for the given id"
}
```

## Transactions
Handle transactions creation


### POST /transactions

Create transaction for account

#### Payload
| Name      | Description | Example | Required                                                                                     |
| ----------|-------------|---------|------------------------------------------------------------------------------ |
| account_id       | Customer account id | 12345 | true |
| operation_type_id       | The operation type id. Values: <br> 1: PURCHASE_CASH. <br>2: PURCHASE_INSTALLMENTS. <br>3: WITHDRAW. <br>4: PAYMENTS | 1 | true |
| amount       | Transaction value. Must be positive | 123.45 | true |

#### Request
```
curl -X POST http://localhost:8080/transactions -H 'Content-Type: application/json' -d '{ "account_id": 1, "operation_type_id": 4, "amount": 123.45 }'
```

##### Response

`HTTP 201`
`no body`

`HTTP 404`
```json
{
  "status": 404,
  "message": "Account not found for the given id"
}
```
`HTTP 400`
```json
{
  "status": 400,
  "message": "Your request body contains invalid data",
  "details": [
    "amount must be positive"
  ]
}
```

# posProject

This project includes 3 endpoints designed for some pos operations.

1. 	 /api/accounts --> Lists all accounts

    http://localhost:8080/api/accounts
	
2. 	/api/account-balance/{accountId} --> returns actual balance for desired account.
	
    http://localhost:8080/api/account-balance?accountId=4755
	
3. /api/payment --> gets payment requests and updates the balance according to the details.
	
	 curl -H "Content-Type: application/json" -X POST -d {\"messageType\":\"PAYMENT\",\"transactionId\":\"54622353626262626\",\"accountId\":\"4755\",\"origin\":\"VISA\",\"amount\":\"10.01\"} http://localhost:8080/api/payment
	
DATABASE:
H2 db is used as datasource.

it is possible to reach to db with the link and information below.

http://localhost:8080/h2-console
user: sa pwd: password

Tables are created automatically at start up and initial data is inserted.

SELECT * FROM ACCOUNTS;
ACCOUNT_ID  	BALANCE  
4755	1001.88
7735	89.36
9834	456.45

Transactions might be inserted with the requests.

Payment & Adjustment

curl -H "Content-Type: application/json" -X POST -d {\"messageType\":\"PAYMENT\",\"transactionId\":\"54622353626262628\",\"accountId\":\"4755\",\"origin\":\"MASTER\",\"amount\":\"10.01\"} http://localhost:8080/api/payment

curl -H "Content-Type: application/json" -X POST -d {\"messageType\":\"ADJUSTMENT\",\"transactionId\":\"54622353626262628\",\"accountId\":\"4755\",\"origin\":\"MASTER\",\"amount\":\"1000.01\"} http://localhost:8080/api/payment



curl -H "Content-Type: application/json" -X POST -d {\"messageType\":\"PAYMENT\",\"transactionId\":\"54622353626262626\",\"accountId\":\"4755\",\"origin\":\"VISA\",\"amount\":\"10.01\"} http://localhost:8080/api/payment

curl -H "Content-Type: application/json" -X POST -d {\"messageType\":\"PAYMENT\",\"transactionId\":\"54622353626262623\",\"accountId\":\"4755\",\"origin\":\"VISA\",\"amount\":\"10.01\"} http://localhost:8080/api/payment

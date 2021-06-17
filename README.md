# posProject

This project includes 3 endpoints designed for some pos operations.

1. 	 /api/accounts --> Lists all accounts

    http://localhost:8080/api/accounts
	
2. 	/api/account-balance/{accountId} --> returns actual balance for desired account.
	
    http://localhost:8080/api/account-balance?accountId=4755
	
3. /api/payment --> gets payment requests and updates the balance according to the details.
	
	 curl -H "Content-Type: application/json" -X POST -d {\"messageType\":\"PAYMENT\",\"transactionId\":\"54622353626262626\",\"accountId\":\"4755\",\"origin\":\"VISA\",\"amount\":\"10.01\"} http://localhost:8080/api/payment
	

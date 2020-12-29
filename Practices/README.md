# API document

## Record Tracking
-EndPoint: /postTotalRecord (GET,POST)  
-Source: http://git.bigdata.local/data-engineers/ftel-api.Branch: record-tracking-0.0.1  
-Method: GET/POST  
-Format response: Parameter/json  
-Parameter:  
	- ***As GET method:***  
	_ name: (string)  
	_ dataDate: a string format “yyyy-mm-dd”  
	_ total: (int)  
	_ source: (string)  
	- ***As POST method:***  
	```
 	   {  
		"name":"name1",     

		"dataDate": "2020-11-01",  

		"total": 3,   

		"source":"source1"  
	   }  
	```

**Response:**  
	*1. Success:* Show noti as json  
	   ```
	   {  
	  	"code": 200,  
		"message": "Data inserted"  
	   }  
	   ```
	*2. Error:*  
	   - Missing of wrong parameter/field:  
	   ```
	   {  
	  	"code": 400,  
		"message": "Wrong or missing parameter"    
	   }  
	   ```
	   - Data insert duplicate:   
	   ```
	   {  
	        "code": 400,   
	        "message": "Data duplicated"  
	   }  
	   ``` 
	   - Empty body with POST method:  
           ```
	   {  
		"code": 400,   
		"message": "Empty body"   
	   }  
	   ```



# API document

## 1. Record Tracking
###### Insert data into DB from user input
-***EndPoint:*** /postTotalRecord (GET,POST)  
-***Source:*** http://git.bigdata.local/data-engineers/ftel-api.Branch: record-tracking-0.0.1  
-***Method:*** GET/POST  
-***Format response:*** Parameter/json  
-***Parameter:***  
- ***As GET method:***  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*_ name:* (string)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*_ dataDate:* a string format “yyyy-mm-dd”  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*_ total:* (int)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*_ source:* (string)  
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
&nbsp;&nbsp;&nbsp;- Success:  
```
	   - Show noti as json

	   {  
	  	"code": 200,  
		"message": "Data inserted"  
	   }  
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   - Missing of wrong parameter/field:  

	   {  
	  	"code": 400,  
		"message": "Wrong or missing parameter"    
	   }  

	   - Data insert duplicate:   

	   {  
	        "code": 400,   
	        "message": "Data duplicated"  
	   }  

	   - Empty body with POST method:  

	   {  
		"code": 400,   
		"message": "Empty body"   
	   }  
```
## 2. CSMR
###### Get contract Data by Date
-***EndPoint:*** /getContractByDate?date=2020-12-01  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/csmr/getContractByDate?date=2020-12-22  
-***Method:*** GET  
-***Parameter:***  
- *** date=YYYY-MM-DD _(string)_ ***  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
[{"contract":"HUFD43280","program":19,"reason":5,"priority":1,"churn_combo_group":1,"churn_net_group":1,"box_group":1,"conn_group":1},{"contract":"HNH238737","program":19,"reason":5,"priority":1,"churn_combo_group":1,"churn_net_group":1,"box_group":1,"conn_group":1},{"contract":"HNH502723","program":19,"reason":5,"priority":1,"churn_combo_group":1,"churn_net_group":1,"box_group":1,"conn_group":1},{"contract":"HNFD51204","program":19,"reason":5,"priority":1,"churn_combo_group":1,"churn_net_group":1,"box_group":1,"conn_group":1},{"contract":"HNH449056","program":19,"reason":5,"priority":1,"churn_combo_group":1,"churn_net_group":1,"box_group":1,"conn_group":1},…] 
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   {  
	  	"code": 400,  
		"message": "Wrong or missing parameter"    
	   }  
```
-***EndPoint:*** /getDupContractByDate?date=2020-12-22  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/csmr/getDupContractByDate?date=2020-12-22  
-***Method:*** GET  
-***Parameter:***  
- *** date=YYYY-MM-DD _(string)_ ***  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
[{"contract":"AGD019933","program":19},{"contract":"AGD023439","program":19},{"contract":"AGD032747","program":19},{"contract":"AGFD04881","program":19},{"contract":"AGFD07994","program":19},{"contract":"AGFD13486","program":19},{"contract":"AGFD14903","program":19},{"contract":"AGFD15520","program":19},{"contract":"AGFD15879","program":19},{"contract":"AGFD20749","program":19},{"contract":"AGFD14815","program":19},{"contract":"AGFD18747","program":19},{"contract":"BDD033330","program":19},{"contract":"BDD072103","program":19},{"contract":"BDFD64164","program":19},{"contract":"BDH002179","program":19},{"contract":"BDH003688","program":19},{"contract":"BEFD11822","program":19},{"contract":"BGD008925","program":19},{"contract":"BGD025703","program":19},{"contract":"BGFD01011","program":19},{"contract":"BGFD01732","program":19},{"contract":"BGFD08851","program":19},{"contract":"BGFD12274","program":19},{"contract":"BGFD43398","program":19},{"contract":"BID024660","program":19},{"contract":"BIFD02445","program":19},{"contract":"BIFD02461","program":19},...] 
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   {  
	  	"code": 400,  
		"message": "Wrong or missing parameter"    
	   }  
```
## 3. SCC
###### Get contract Data
-***EndPoint:*** /getContractData?contract=BNFD32739  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/scc/getContractData?contract=BNFD32739  
-***Method:*** GET  
-***Parameter:***  
- *** contract _(string)_ ***  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
	    {
	  	"Ip": "20.121.14.1",
	  	"Profile": "Fiber-F6-HD",
	  	"PortID": "3",
	  	"Macaddress": "ec:84:b4:32:d3:8e"
	    }	
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   ERROR! Missing Contract 
```
## 4. Internet
###### Predict leaving the internet
-***EndPoint:*** /  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/internet/churn/predict  
-***Method:*** GET  
-***Parameter:***  
- *** month=YYYY-MM _(string)_ ***  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
	    {
		Contract  : "HNH337270", 
    		ID: 3
	    },
	    ...	
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   {"data": "Something went wrong!", "code": 400}  
```
-***Flow process data:***  
- Check if current date is less than 14th of month, set import_date = 2 days of month  
- Else, set import_date = 14th of month  
==> Ex: current date= ‘2019-09-13' -> import_date = ‘2019-09-02'  
current date= ‘2019-09-16' -> import_date = ‘2019-09-14'  
## 4. Potential Client
###### Get Potential Client
-***EndPoint:*** /  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/client/getClientByMonth?contract=AGD003838&month=2020-06  
-***Method:*** GET  
-***Parameter:***  
- contract _(string)_  
- month=YYYY-MM _(string)_  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
	    {
		"code":200,  

		   "message":"success", 

		   "data":true, 

		   "result":{ 

		      "contract": “AGD003838”, 

		      "region": “Vung 7”,      

		      "location": “An Giang”, 

		      "nhucauSd": “rat_cao”, 

		      “group” : 4 
		      }
	    }	
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   {"code": 400, "data": "Something went wrong!"}  
```


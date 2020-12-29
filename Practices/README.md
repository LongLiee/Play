# API document

## 1. Record Tracking
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
-***EndPoint:*** /getContractByDate?date=2020-12-01  
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
</br>
-***EndPoint:*** /getDupContractByDate?date=2020-12-22  
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


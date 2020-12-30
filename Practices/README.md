# API document

## 1. Record Tracking
#### Insert data into DB from user input
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
#### Get contract Data by Date
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
#### Get contract Data
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
#### Predict leaving the internet
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
## 5. Potential Client
#### Get Potential Client
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
## 6. RKNLT
#### Rớt kết nối liên tục 
-***EndPoint:*** /getAll  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/rknlt/getAll?date=2018-12-11   
-***Method:*** GET  
-***Parameter:***  
- date=yyyy-MM-dd _(string)_  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
	[ {"contract":"BLD001647"}, 
	  {"contract":"HNH541411"}, 
	  {"contract":"SGH427124"}, 
	…]	
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   {"data": "Something went wrong!", "code": 400}  
```
> Note: Data này out daily, trừ ngày **chủ nhật**
## 7. PAYTV
#### Return all contract by month 
-***EndPoint:*** /getAll  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/paytv/getAll?month=2018-10   
-***Method:*** GET  
-***Parameter:***  
- month=yyyy-MM _(string)_  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
	   [ 

	       { 

		   "contract":    "SGH462715", 

		   "active_date":    "2018-03-23 15:54:15.013", 
		   "change_date":    "2018-03-22 09:40:05.82", 
		   "status":    "Binh thuong", 
		   "region":    "Ho Chi Minh", 
		   "location":    "Vung 5", 
		   "amount":    63871 

	       }, 

	       ..... 

	   ] 	
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   {"data": "Something went wrong!", "code": 400}  
```
#### Return all contract predict log by date
-***EndPoint:*** /getPredict  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/paytv/getPredict?date=2019-03-12   
-***Method:*** GET  
-***Parameter:***  
- month=yyyy-MM-dd _(string)_  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
	[ 
	  {"contract":"HNH301986"}, 

	  {"contract":"HNH567432"}, 

	  {"contract":"HNH624499"}, 

	  .... 
	] 	
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   {"data": "Something went wrong!", "code": 400}  
```
## 8. Sessions
#### Get sessions by Last Date 
-***EndPoint:*** /getAll  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/sessions/getAll  
-***Method:*** GET  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
           [ 

             {"contract":"VTFD41938", "name":"vtfdl-180920-938"}, 

             {"contract":"VTFD39759", "name":"vtfdl-180730-759"}, 

             {"contract":"TVFD07323", "name":"tvfdl-180622-323"}, 

             ..... 
            ] 	
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   {"data": "Something went wrong!", "code": 400}  
```
#### Get sessions by Range Date
-***EndPoint:*** /getByDate  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/sessions/getByDate?fromDate=2019-01-01&toDate=2019-01-02  
-***Method:*** GET  
-***Parameter:***  
- fromDate=yyyy-MM-dd _(string)_  
- toDate=yyyy-MM-dd _(string)_  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
        [ 

           {"contract":"SGH577243", "name":"sgfdl-181220-243"}, 

           {"contract":"SGH568330", "name":"sgfdl-181019-330"}, 

         ..... 

        ] 
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   {"data": "Something went wrong!", "code": 400}  
```
## 9. INFRA INSIDE
#### Đối với chương trình Wifi kém 
-***EndPoint:*** /wifi  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/inside/contract/wifi?date=2019-09-11  
-***Method:*** GET  
-***Parameter:***  
- date=yyyy-MM-dd _(string)_  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
	  [{"contract":"HNH404006", "id":"2"},...]     
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   {"data": "Something went wrong!", "code": 400}  
```
#### Đối với chương trình KH lỗi hạ tầng
-***EndPoint:*** /khlht  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/inside/contract/khlht?date=2019-09-11  
-***Method:*** GET  
-***Parameter:***  
- date=yyyy-MM-dd _(string)_  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
	  [{"contract":"SGD744758", "id":"4"},...]
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   {"data": "Something went wrong!", "code": 400}  
```
> Data này chỉ có vào mỗi thứ Tư hàng tuần trong database
#### Đối với chương trình Lỗi kết nối Internet 
-***EndPoint:*** /clkn_net  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/inside/contract/clkn_net?date=2020-10-26   
-***Method:*** GET  
-***Parameter:***  
- date=yyyy-MM-dd _(string)_  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
	  [{"contract":"SGD744758", "id":"4"},...]     
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	   {"data": "Something went wrong!", "code": 400}  
```
## 10. Internet Traffic
#### Get traffic internet
-***EndPoint:*** /  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/traffic/getLstContract   
-***Method:*** POST  
-***Parameter:***  
- date_(string)_ -> enable null  
- region_(string)_ -> not null  
- packageName_(string)_ -> not null  
- target_(double)_ -> not null (0 < value <= 100) : return number of contract  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
		{  

		    "code": 200,  

		    "message": "success",  

		    "totalContract": 170168,  

		    "data": [  

			  {  

			    "contract": "NNFD02573",  

			    "region": "Vung 6",  

			    "location": "Ninh Thuan",  

			    "packageName": "FTTH - Super22",  

			    "totalScore": 13,  

			    "priority": 1,  

			    "rank": 1871409, 

			    “date” : “2020-08-01" 

			  }, 

			  ….  

		      ]  

	         } 	
```

&nbsp;&nbsp;&nbsp;- Error:  
```
	     { 
                "code": 400,  
                "data": "Something went wrong!" 
             } 
```
#### *Example
- Use curl:  
```
curl -d '{"region": "Vung 4", “date” : “2020-08-01", "packageName": "FTTH - Super22","target":100}' -H "Content-Type: application/json" -X POST http://bigdata-api.fpt.vn/api/v0.1/traffic/getLstContract 
```
- Use Postman:  
```
	Result:
		{  

		    "code": 200,  

		    "message": "success",  

		    "totalContract": 111032, 

		    "data": [ 

			{ 

			    "contract": "HUFD05855", 

			    "region": "Vung 4", 

			    "location": "Hue", 

			    "packageName": "FTTH - Super22", 

			    "totalScore": 36.3, 

			    "priority": 1, 

			    "rank": 61, 

			   “date” : “2020-08-01" 

			}, 

			{ 

			    "contract": "NTFD14652", 

			    "region": "Vung 4", 

			    "location": "Nha Trang", 

			    "packageName": "FTTH - Super22", 

			    "totalScore": 35, 

			    "priority": 1, 

			    "rank": 140, 

			   “date” : “2020-08-01" 

			}, 

			  ….  

		      ]  

		} 
```
## 11. UP
#### Get sessions by Last Date 
-***EndPoint:*** /topUpsell  
-***Source:*** http://bigdata-api.fpt.vn/up/v2/getUserInfo/topUpsell?listContract=HNH504859&top=5  
-***Method:*** GET  
-***Parameter:***  
- listContract_(string)_  
- top(string)_  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
	[
	 {
	   "contract": "HNH504859",
	   "topUpsellGroup": {
	   	"top1": "Nâng gói dịch vụ",
	   	"top2": "Bán thêm box truyền hình",
	   	"top3": "Bán thêm Camera",
	   	"top4": "Bán thêm Access Point",
	   	"top5": ""
	   }
	 }
	]
```
&nbsp;&nbsp;&nbsp;- Error:  
```
	     { 
                "code": 400,  
                "data": "Something went wrong!" 
             } 
```
- Reponses explanation:  
  - contract:  Contract number of clients  
  - Top 1-5:  Top upsell groups  
#### Show data Userprofile as tables
-***EndPoint:*** /iframe  
-***Source:*** http://bigdata-api.fpt.vn/up/v2/getUserInfo/iframe?listContract=HNH504859&tMonth=t,t1,t2,t3   
-***Method:*** GET  
-***Parameter:***  
- listContract_(string)_: list contracts separated by character ","  
- tMonth_(string)_: list months in array [ t, t1, t2, t3] and separated by character “,”  
Based on current month to get t1, t2, t3. Example: today is 2020-02-11  
-> t: current month (2020-9)  
&nbsp;&nbsp;t1: 2020-08, t2: 2020-07, t3: 2020-06  
- Each parameter is separated by character “&” 
**Format response:** Tables
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
![image](https://user-images.githubusercontent.com/40731343/103339561-40ffb280-4ab4-11eb-95e2-10ea76697b0f.png)  
&nbsp;&nbsp;&nbsp;- Error:  
![image](https://user-images.githubusercontent.com/40731343/103339666-8fad4c80-4ab4-11eb-8819-9a32ca43206d.png)  
- Reponses explanation:  
  - Success: Show data table as html  
  - Error: Contract (contract number) is invalid   
#### Show data potential client 
-***EndPoint:*** /potentialClient  
-***Source:*** http://bigdata-api.fpt.vn/up/v2/getUserInfo/potentialClient?listContract=HNH504859    
-***Method:*** GET  
-***Parameter:***  
- listContract_(string)_: list contracts separated by character ","  
**Format response:** Json
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
- curl http://bigdata-api.fpt.vn/up/v2/getUserInfo/potentialClient?listContract=HNH504859 
![image](https://user-images.githubusercontent.com/40731343/103340145-f1ba8180-4ab5-11eb-816c-50aad20280a2.png)  
&nbsp;&nbsp;&nbsp;- Error:  
![image](https://user-images.githubusercontent.com/40731343/103340194-1151aa00-4ab6-11eb-8ac4-19a8881bff23.png)  
- Reponses explanation:  
  - contract:  Contract number of clients  
  - upgradeGroup:  Upgrade - Package  
  - upsellPfGroup:  Upsell - Combo  
  - UpsellcamGroup : Upsell - Camera  
## 12. Natnoc
#### Get NAT info
-***EndPoint:*** /  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/noc/natinfo?nat=true&nasname=MX480  
-***Method:*** GET  
-***Parameter:***  
- nasname_(string)_  
- nat_(boolean)_  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
	 { 
	   "MX480":[325, 2211], 
	   "HCM-MP05-5":[238, 3345] 
	 } 
```
&nbsp;&nbsp;&nbsp;- Error: ERROR  
## 13. Infra
#### Auto checklist by request
-***EndPoint:*** /contract/is-error  
-***Source:*** http://bigdata-api.fpt.vn/api/v0.1/infra/contract/is-error   
-***Method:*** GET  
-***Parameter:***  
- date_(string)_: YYYY-MM-dd  
- contract_(string)_  
**Response:**  
&nbsp;&nbsp;&nbsp;- Success:  
```
    { 
	   "code":200, 
	   "message":"success", 
	   "data":true, 
	   "result":{ 
	      "user_down":-1, 
	      "inf_down":-1, 
	      "sf_error":0, 
	      "lofi_error":0, 
	      "rouge_error":-1, 
	      "lost_signal":-1, 
	      "log_off":15, 
	      "device":5, 
	      "wifi_rate":0.54   
	   } 
    } 
```
&nbsp;&nbsp;&nbsp;- Error:  
```
	{"data":"Something went wrong!","code":400}
```









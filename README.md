# Rent System

## API

### Car rental

#### Call path

```
/car/booking
```

#### How to call

```
POST/GET
```

#### Parameters

Parameters | Description
------------- | -------------
mobile|phone number
type|Car model. 1: Toyota Camry; 2: BMW 650
day|Lease period (days)

#### Response

```
normal
{
"CODE":"00",
"MSG":"Car rental is successful"
}

abnormal
{
"CODE":"01",
"MSG":"Invalid User"
}

{
"CODE":"01",
"MSG":"This model has been rented out, please choose another model"
}

{
"CODE":"01",
"MSG":"Insufficient balance"
}

{
"CODE":"01",
"MSG":"Car rental failed"
}
```

### return car

#### Call path

```
/car/return
```

#### How to call

```
POST/GET
```

#### Parameters

Parameters | Description
------------- | -------------
mobile|phone number
type|Car model. 1: Toyota Camry; 2: BMW 650
id|Car rental log id. Can be queried through the interface /car/query

#### Response

```
normal
{
"CODE":"00",
"MSG":"Return successfully"
}

abnormal
{
"CODE":"01",
"MSG":"There is no car to return"
}
```

### Check car rental

#### Call path

```
/car/query
```

#### How to call

```
POST/GET
```

#### Parameters

Parameters | Description
------------- | -------------
mobile|phone number

#### Response

```
normal
{
"DATA":[
{
"car":"Toyota Camry","id":2
},{
"car":"Toyota Camry","id":3
},{
"car":"BMW 650","id":4
}],
"CODE":"00"
}

{
"DATA":[],
"CODE":"00"
}
```

## DB Design

### user table

Field|type|nullable|description
---- | ---- | --- | -----
id|bigint|no|primary key
user_name|varchar(100)|No|Name
mobile|varchar(20)|No|Mobile number
account_balance|decimal|No|account balance

### Car inventory table

Field|type|nullable|description
---- | ---- | --- | -----
id|bigint|no|primary key
car_model|varchar(1)|No|Model code
car\_model\_name|varchar(20)|No|Model description
in_stock|int|No|Inventory
one\_day\_cost|decimal|No|The price of one day rent
update_date|timestamp|No|Update time

### Car Rental Record Form

Field|type|nullable|description
---- | ---- | --- | -----
id|bigint|no|primary key
mobile|varchar(20)|No|Mobile number
car_model|varchar(1)|No|Model code
create_date|timestamp|No|Creation time
return_date| timestamp|yes|return time
duration|int|No|Days of car rental


## TEST CASES

### Car rental interface

Input parameter|output|result
---- | ---- | ----
mobile=13800138444&type=1&day=1|Invalid User|pass
mobile=13800138000&type=1&day=1|This model has been rented out, please choose another model| pass
mobile=13800138111&type=1&day=1|Insufficient balance| pass
mobile=13800138000&type=2&day=1|Car rental is successful| pass

### Return interface

Input parameter|output|result
---- | ---- | ----
mobile=13800138444&type=1&id=1|There is no car to return|pass
mobile=13800138000&type=1&id=2|Return successfully|pass

### Query car rental interface

Input parameter|output|result
---- | ---- | ----
mobile=13800138000|{"DATA":[{"car":"Toyota Camry","id":2},{"car":"Toyota Camry","id":3},{"car":" BMW 650","id":4}],"CODE":"00"}|pass
mobile=13800138222|{"DATA":[],"CODE":"00"}|pass


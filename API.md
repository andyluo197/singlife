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

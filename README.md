# Connect city code challenge

## Introduction

This service will give the the details about the connectivity between the two cities. Configured the connected cities in the cities.txt file and loads the data while starting of the application. This spring boot micro service also includes the below features

- SwaggerUI and REST documentation
- Junit parameterized test cases
- Code coverage with EclEmma
- Functional test cases (Possitive & Negative)
- Logging using logback
- Actuator metrics
- Exception handling
- Java documentation

## Technologies Used

- Java 11
- SpringBoot 2.4.x

## Tools used

- Maven for build
- EclEmma for coverage
- STS 4.x for development

## Coverage Details
![alt text](https://github.com/sureshdharisi/codechallenge/blob/master/find-city-connect/coverage_details.PNG?raw=true)

## Implementation steps:
- Prepare the data which hold complete connectivity details from each city and store it in the map where key is the city and value is the series of connected cities with direct connection and indirect connection at the time of bootstrap.
- This data is case insensitive means user can send the data in any case.
- If user requested for origin and destination city connectivity, then the connecitivity registry will pull the data from map and check the destination is there or not in the connected cities. If it present, then service will return yes else no.
- This service is expecting both origin and destination cities are mandatory. If anyone is not present, then application will not accept the data and respond with 400 status code with some error code details. So that consumer can correct the input data.
- Extensive junits are implemented to check service is responding properly or not.
- Predefined connectivity details are available in cities.txt file.

### Some sample requests
* Input -1 
```
http://localhost:8080/connected?origin=Boston&destination=Newark
```

```json
yes
```

* Input -2
```
http://localhost:8080/connected?origin=Boston&destination=Philadelphia
```

```json
yes
```

* Input -3 
```
http://localhost:8080/connected?origin=Philadelphia&destination=Albany
```

```json
No
```
### Some error responses
* Input -1 
```
http://localhost:8080/connected?origin=Boston&destination=
```
```json
{
  "errorMessage": "Origin city is required",
  "errorCode": "CITY001"
}
```
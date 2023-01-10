# Connect city code challenge

## Introduction

This service will calculated the rewards points based on the purchase amount. This spring boot micro service also includes the below features

- Junit parameterized test cases
- Code coverage 
- Functional test cases (Possitive & Negative)
- Logging using logback
- Actuator metrics
- Exception handling
- Java documentation
- Lombok for automatic code generation

## Technologies Used

- Java 17 (This will work with java 11 also. We need to modify the version in the pom.xml to point to 11 (_<java.version>11</java.version>_)).
- SpringBoot 3.0.x

If you are using java.version 11, then make sure java_home also should point to java 11 to build the project in the command prompt.

## Tools used

- Maven for build
- Jacoco for coverage
- STS 4.x for development
- Spotbugs plugin

## Coverage Details
![alt text](https://github.com/sureshdharisi/codechallenge/blob/master/find-city-connect/coverage_details.PNG?raw=true)

## Configuration details
The current limit configuration details:

Lower limit: 50, Upper limit = 100 , points = 1 
Lower limit: 100, Upper limit = <nolimit> , points = 2

## Test data and Results

Purchase amount | Expected results |
------------------- | -------------------|
120.0 | 90 |
100.0 | 50 |
90.0 | 40 |
99.5 | 49 |
50.0 | 0 |
49.0 | 0 |
51.0 | 1 |


## Junit test cases details
![alt text](https://github.com/sureshdharisi/codechallenge/blob/master/find-city-connect/JunitTestCases.PNG?raw=true)


### Some sample requests
* Input -1 
```
http://localhost:8585/rewards/calculate/120.0
```

```json
{
    "points": 90
}
```

* Input -2
```
http://localhost:8585/rewards/calculate/100.0
```

```json
{
    "points": 50
}
```

* Input -3 
```
http://localhost:8585/rewards/calculate/90.0
```

```json
{
    "points": 40
}
```

* Input -4 
```
http://localhost:8585/rewards/calculate/99.5
```

```json
{
    "points": 49
}
```

* Input -5 
```
http://localhost:8585/rewards/calculate/50.0
```

```json
{
    "points": 0
}
```
### Some error responses
* Input -1 
```
http://localhost:8585/rewards/calculate/abc
```

```json
{
    "errorMessage": "Failed to convert value of type 'java.lang.String' to required type 'java.lang.Double'; For input string: \"abc\"",
    "errorCode": "RC000"
}
```

* Input -2
```
http://localhost:8585/rewards/calculate/ 

(input is blank space)
```

```json
{
    "errorMessage": "Required URI template variable 'purchaseAmount' for method parameter type Double is present but converted to null",
    "errorCode": "RC000"
}
```

### How to run?
1. Download the code from github using below url
```
git clone https://github.com/sureshdharisi/codechallenge.git
```
2. Goto the project where pom.xml is located and run the maven command. The below command will run the junit test cases automatically
```
mvn clean install
```
3. Run the application using spring boot plugin.
```
mvn spring-boot:run
```

### Swagger UI
```
http://localhost:8080/swagger-ui/index.html
```
### Swagger YAML
````
http://localhost:8080/v2/api-docs
````
### Actuator URL
````
http://localhost:8080/actuator/
````
### Generate spotbug report
1. Run below command to generate spot bug report
````
mvn site
````
Now report will be generated in the target folder and the location is "find-city-connect\target\site\spotbugs.html"

## Spotbugs report
![alt text](https://github.com/sureshdharisi/codechallenge/blob/master/find-city-connect/Spotbug_Report.PNG?raw=true)

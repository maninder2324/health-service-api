DataBase Structure
-------------------

CREATE TABLE `enrollee` (
  `id` bigint(20) NOT NULL,
  
  `name` varchar(50) DEFAULT NULL,
  
  `dateOfBirth` date DEFAULT NULL,
  
  `isActive` tinyint(1) DEFAULT '1',
  
  `phone` varchar(20) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
  
) 



CREATE TABLE `dependent` (

  `id` bigint(20) NOT NULL,
  
  `enrolleeId` int(11) DEFAULT NULL,
  
  `name` varchar(50) DEFAULT NULL,
  
  `dateOfBirth` date DEFAULT NULL,
  
  PRIMARY KEY (`id`)
  
)

Configuration Changes :
----------------------

Please update the mysql JDBC url, username and password in application.properties:

	db.driverClassName=com.mysql.cj.jdbc.Driver
	db.url=jdbc:mysql://ip:port/db
	db.username=
	db.password=


How to start the Server ?

	mvn spring-boot:run

How to run JUnit test ?

	mvn test


Enrollee related API URLs :
---------------------------

POST 	- 	http://localhost:8080/api/enrollee

GET 	- 	http://localhost:8080/api/enrollee/100

PUT 	-	http://localhost:8080/api/enrollee/100

DELETE 	-	http://localhost:8080/api/enrollee/100


Dependent related API URLs :
----------------------------

POST 	- 	http://localhost:8080/api/enrollee/100/dependent

GET 	- 	http://localhost:8080/api/dependent/100

PUT 	-	http://localhost:8080/api/dependent/100

DELETE 	-	http://localhost:8080/api/dependent/100




Add Enrollee:
-------------

curl -s -X POST  \
	-H "Content-Type: application/json" \
	-d '{"id":100, "name":"Ravi","isActive":true,"dateOfBirth":"2020-10-10"}' \
	http://localhost:8080/api/enrollee
	

Get Enrollee:
-------------
	
curl -s -X GET http://localhost:8080/api/enrollee/100

Update Enrollee:
----------------

curl -s -X PUT  \
	-H "Content-Type: application/json" \
	-d '{"name":"Ravi Kumar -name changed"}' \
	http://localhost:8080/api/enrollee/100

Delete Enrollee:
----------------
	
curl -s -X DELETE http://localhost:8080/api/enrollee/100


Add Dependent:
--------------


curl -s -X POST  \
	-H "Content-Type: application/json" \
	-d '{"id":200, "name":"Kannan","dateOfBirth":"2020-10-10"}' \
	http://localhost:8080/api/enrollee/100/dependent


Get Dependent:
--------------
	
curl -s -X GET http://localhost:8080/api/dependent/200


Update Dependent:
-----------------

curl -s -X PUT  \
	-H "Content-Type: application/json" \
	-d '{"name":"Kannan-name changed"}' \
	http://localhost:8080/api/dependent/200
	
Delete Dependent:
-----------------

curl -s -X DELETE http://localhost:8080/api/dependent/200

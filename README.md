# health-service-api
Configuration Changes :

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

POST 	- 	http://localhost:9999/api/enrollee
GET 	- 	http://localhost:9999/api/enrollee/100
PUT 	-	http://localhost:9999/api/enrollee/100
DELETE 	-	http://localhost:9999/api/enrollee/100


Dependent related API URLs :
----------------------------

POST 	- 	http://localhost:9999/api/enrollee/100/dependent
GET 	- 	http://localhost:9999/api/dependent/100
PUT 	-	http://localhost:9999/api/dependent/100
DELETE 	-	http://localhost:9999/api/dependent/100




Add Enrollee:
-------------

curl -s -X POST  \
	-H "Content-Type: application/json" \
	-d '{"id":100, "name":"Ravi","isActive":true,"dateOfBirth":"2020-10-10"}' \
	http://localhost:9999/api/enrollee
	

Get Enrollee:
-------------
	
curl -s -X GET http://localhost:9999/api/enrollee/100

Update Enrollee:
----------------

curl -s -X PUT  \
	-H "Content-Type: application/json" \
	-d '{"name":"Ravi Kumar -name changed"}' \
	http://localhost:9999/api/enrollee/100

Delete Enrollee:
----------------
	
curl -s -X DELETE http://localhost:9999/api/enrollee/100


Add Dependent:
--------------


curl -s -X POST  \
	-H "Content-Type: application/json" \
	-d '{"id":200, "name":"Kannan","dateOfBirth":"2020-10-10"}' \
	http://localhost:9999/api/enrollee/100/dependent


Get Dependent:
--------------
	
curl -s -X GET http://localhost:9999/api/dependent/200


Update Dependent:
-----------------

curl -s -X PUT  \
	-H "Content-Type: application/json" \
	-d '{"name":"Kannan-name changed"}' \
	http://localhost:9999/api/dependent/200
	
Delete Dependent:
-----------------

curl -s -X DELETE http://localhost:9999/api/dependent/200

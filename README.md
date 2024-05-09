# Ecommerce
An Ecommerce backend using Java spring boot and Postgres as database. The development was done using TDD approach. 
Added some random data for testing convenience using liquibase. A Postman collection is also added. 

### API that was exposed
- Create an API to return the wish list of a customer.
- Create an API to return the total sale amount of the current day.
- Create an API to return the max sale day of a certain time range.
- Create an API to return top 5 selling items of all time (based on total sale amount).
- Create an API to return top 5 selling items of the last month (based on number of sales).

### Framework 
- Spring Boot 3.2.2 and JDK 17
- Used the latest docker version

### Database 
- PostGRE SQL was used as ecommerce has mostly transactional data 
- Used liquibase to ensure the data migration 

### Log Visualization
- The Simple Logging Facade for Java (SLF4J) serves was used.

### Other Library 
- Mapstruct
- Lombok
- Openapi

### Improvement Area
- Introduce Java Spring boot security to restrict the API
- Implement Java documentation
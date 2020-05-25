1. open cmd
2. cd c:/project (go to your project root folder) 
3. mvn clean install
4. mvn spring-boot:run 

The server will start on port 8090.
Swagger link:  http://localhost:8090/documentation/swagger-ui.html#/

SchoolDiaryAPI is a REST API written in Spring with provides basic CRUD functions for students, grades and student group as well as additional practical functions.

Capabilities:
- CRUD for student, grades, student group

Practical options:
- listing students, grades, classes
- finding (student , grade, class) by id
- get all students by surnames
- get student by age before, after and between some date
- assign student to student group
- assign grade to student
- paggination 

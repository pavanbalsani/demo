Create Docker image:
--------------------
cd C:\code\repos\new\employeeapi>
#Build a docker image using Maven
mvn clean install dockerfile:build

#List all Docker images:
docker image ls

Running a Container Docker Image:
---------------------------------
docker run -p 9005:9005 employeesapi:latest

Useful docker commands:
----------------------
docker ps
docker ps -a
docker stop <containerID>
docker rm <containerID>
docker system prune

Resourses used:
---------------
https://cloudificationzone.com/2020/06/14/create-a-microservice-using-springboot-dockerize-and-run-on-docker/

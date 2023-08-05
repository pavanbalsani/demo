How extract Sonar reports for this project:
-------------------------------------------
1. Move to StartSonar.bat file location in your windows laptop. In my laptop the location is below:
    C:\code\repos\sonar\sonarqube\bin\windows-x86-64
2. Double click on StartSonar and wait for the server to start.
3. Login to sonar using the url http://localhost:9000
4. Click on the account profile and select security tab.
5. Generate a token that expires as per ur requirement and I have selected no expiration as it's a demo project.
6. Copy the token and replace it in the project's pom.xml by searching for sonar.token string.
7. Now open the project's root folder in the terminal and run the commands below:
   mvn clean
   mvn install
   mvn sonar:sonar
8. Login to sonar qube using url : http://localhost:9000/projects to access ur reports.

Sources used to integrate sonar:
--------------------------------
1. https://blog.knoldus.com/integrate-maven-project-sonarqube/
2. https://roytuts.com/analyze-code-quality-of-java-application-using-sonarqube/
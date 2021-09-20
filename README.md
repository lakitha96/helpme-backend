# helpme-backend

Maven is a build automation tool used primarily for Java projects.
https://maven.apache.org

To download libraries and build the project, please use following command for each project.

Before Run the Project, Build the project
- ``` mvn clean install -DskipTests ```

Configure following enviroment varibles
- ``` spring.datasource.url=${MYSQL_URL} ```
- ``` spring.datasource.username=${MYSQL_USERNAME} ```
- ``` spring.datasource.password=${MYSQL_PASSWORD} ```
- ``` spring.jpa.hibernate.ddl-auto=${MYSQL_DD_AUTO} ```

To Run the project 
- If needed, run sql dump -> helpme_sql_dump.sql
- Run main method - /src/main/java/com/bedfordshire/helpmebackend/HelpmeBackendApplication.java

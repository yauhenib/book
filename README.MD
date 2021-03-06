## Book
[![Build Status](https://travis-ci.org/Vaisman/book.svg?branch=master)](https://travis-ci.org/Vaisman/book)


###   Build guide.                                                                                              

#### Prerequisites:

1. Make sure JDK 8 or higher is installed.
2. The same for apache [Maven](https://maven.apache.org/) version 3.3.1 or higher. 
3. Install a [PostgreSQL](https://www.postgresql.org/) database (use 'postgres' as default db owner username and '12345678' as default db owner password)


#### Pre-build steps:

HINT: make sure your project folder doesn't contain spaces

1. Execute credb.sql script from the "book-resources/db/" module or use credb.bat/credb.sh (Windows/Linux).
   The latter is to be run as 'credb <host_name_or_IP>', where <host_name_or_IP> is to be replaced with localhost.
   Other tables will be created once application is launched for the first time.
2. Configure data sources in the "application-dev.yml" in perf-app module and "application.yml" in book-integration-tests module:
   - Specify your local DB URL for the db-perf datasource in case it differs from the default one;
3. Configure data sources in the "pom.xml" in perf-integration-tests module:
   - Specify your local DB URL and user which is granted access to create new DB in the "pom.xml" in case they differ from the default one.
     These properties are marked as "UPDATE HERE";
   - It's not recommended to change "booktester" and temporary "bookdbtest" DB name.


#### Build:

1. Run "mvn clean install"
    - Run to skip integration tests.
      ```shell
      mvn clean install -pl !book-integration-tests
      ```
    - Run to execute only integration tests
      ```shell
      mvn test -pl book-integration-tests
      ```
    - Run to build without an execution of unit tests (NOT RECOMMENDED!)
      ```shell
      mvn clean install -DskipTests
      ```

#### Open in IDE:

NOTE: make sure you've build the app via Maven before opening it in the IDE.

For IDE [IDEA CE](https://www.jetbrains.com/idea/):
  1. Set up the JDK
  2. Import the project from the root "pom.xml"

#### Launch the application:

For IntelliJ IDEA:
  1. Create a Run/Debug configuration of "Application" type, set the "com.igels.book.BookApplication" as a main class. 

Check the running application via:
- opening the endpoint URL, e.g. https://localhost:8443/api/v2/projects/all (empty json array is displayed)
- opening REST API documentation site: https://localhost:8443/swagger-ui.html

In order to execute most of the endpoints authentication is required:
- In Swagger execute 'sso-controller' /api/v2/sso/token endpoint specifying <!> credentials as username and password.
- Copy string returned in the Response Body - security token
- When executing any other endpoints via Swagger paste 'Bearer <security token>' into the 'Authorization' input field for the particular endpoint.
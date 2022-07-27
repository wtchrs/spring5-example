# spring5-example

[![Java][java-badge]][openjdk]
[![Spring][spring-badge]][spring]
[![Gradle][gradle-badge]][gradle]

Original repo : [madvirus/spring5fs][original-repo]

I used the following tools:

- OpenJDK 16.0.2
- Gradle 7.4.2
- Apache Tomcat 9.0.64
- MySQL 8.0

## Getting started

To run this web application, use the following commands:

```sh
git clone https://github.com/wtchrs/spring5-example
cd spring5-example
./gradlew build
```

And create a MySQL table:

```sh
mysql -h localhost -u root  # or with password, mysql -h localhost -u root -p
mysql> source src/sql/ddl.sql
```

It will generate MySQL user `spring5` with password `spring5` and database `spring5fs` and table `MEMBER` in
`spring5fs`.
Alternatively, you can use different database settings by modifying the
profile([src/main/resources/profile/db.dev.properties](src/main/resources/profile/db.dev.properties))
or create a new profile.

And copy the generated war archive into `webapps` folder in your Apache Tomcat directory:

```sh
cp ./build/libs/spring5-example-1.0.war ${YOUR_TOMCAT_DIRECTORY}/webapps/
cd ${YOUR_TOMCAT_DIRECTORY}
./bin/startup.sh  # or ./bin/startup.bat
```

Now, you can access the application in a browser with the following url: http://localhost:8080/spring5-example-1.0/

[java-badge]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white
[spring-badge]:https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[gradle-badge]:https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white
[openjdk]:https://github.com/openjdk/jdk
[spring]:https://github.com/spring-projects/spring-framework
[gradle]:https://github.com/gradle/gradle
[original-repo]:https://github.com/madvirus/spring5fs
# Collection of Games - Website
This web application lets user create account and build their own collection of games. Users can search website in order to find games which they would like to add to their collection. If application doesn't contain information about user's game, user can add one. This project helped me learn about creating web application, connecting application to database and use technologies listed below.
## Technologies
* Java 8
* Spring 5+ (Core, MVC, Data Access)
* Spring Security 5+
* MySQL
* Thymeleaf 3+
* Bean Validation 2
* Maven
* HTML5
* CSS3
## Launch
1. Login to your MySQL account via command line:
```
mysql -u <your username> -p
```
2. Run command:
```
source <path to games.sql script from repository>
```
3. Exit MySql.
4. Go to project directory and then create new directory named 'resources' under src/main/ directory.
5. Create file named 'database.properties' in 'resources' directory and enter following properties:
```
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/games_collection?useSSL=false
jdbc.username=<your db username>
jdbc.password=<your db password>
```
6. Create file named 'upload.properties' in 'resources' directory and enter followind properties:
```
upload.location=<location of game covers uploaded by users>
upload.prefix=<prefix in the name of images>
```
7. Go to project directory and run command:
```
mvn package
```
8. Copy created .war file from target directory to $CATALINA_HOME/webapps directory.
9. Run Tomcat.
10. Website should be accessible via "localhost:<tomcat port number>/<name of .war file>".

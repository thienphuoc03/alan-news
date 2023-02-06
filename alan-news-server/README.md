# Alan News Spring Boot project

## Configure Spring Datasource, JPA, App properties (MySQL v-8.0.30)

Open `src/main/resources/application.properties`

```properties
# PORT SERVER
server.port=8080
# Connect Mysql v-8.0.30
spring.datasource.url=jdbc:mysql://localhost:3306/alan-news?useUnicode=true&useJDBCCompliantTimezoneShift=true&\
  useLegacyDatetimeCode=false&serverTimezone=UTC
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username={username}
spring.datasource.password={password}
# Hibernate ddl auto (create, create-drop, update, none)
spring.jpa.hibernate.ddl-auto=update
# Show or not log for each sql query
spring.jpa.show-sql=true
logging.level.org.hibernate.stat=debug
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.type=trace
# App Properties
htphuoc.app.jwtSecret=htphuocSecretKey
htphuoc.app.jwtExpirationMs=12 * 60 * 60 * 1000
htphuoc.app.jwtRefreshExpirationMs=12 * 60 * 60 * 1000
```

## Run Spring Boot application

```
mvn spring-boot:run
```

## Run following SQL insert statements

```mysql
INSERT INTO roles(name)
VALUES ('ROLE_ADMIN');
INSERT INTO roles(name)
VALUES ('ROLE_MODERATOR');
INSERT INTO roles(name)
VALUES ('ROLE_USER');
```

## APIs (http://localhost:8080/api)

### AUTH

| Method | Url | Description | Sample Valid Request Body |
|--------|-----|-------------|---------------------------|
| POST | /auth/signin | login user | [JSON](#signin) |
| POST | /auth/signup | register user | [JSON](#signup) |
| POST | /auth/refreshtoken | refresh token | |
| POST | /auth/signout | logout user | |

### CATEGORY

| Method | Url | Description | Sample Valid Request Body |
|--------|-----|-------------|---------------------------|
| GET | /categories?page={page}&size={size}&sortBy={sortBy} | get all category | |
| GET | /categories/{id} | get category by id | |
| POST | /categories | add new category (logged in user is admin or moderator) | [JSON](#categorycreate)
| PUT | /categories/{id} | update category by id (logged in user is admin or moderator) | [JSON](#categoryupdate) |
| DELETE | /categories/{id} | delete category by id (logged in user is admin or moderator) | |

## Sample Valid JSON Request Bodys

#### <a id="signin">Sign In</a>

```json
{
  "username": "test",
  "password": "123456"
}
```

#### <a id="signup">Sign Up</a>

```json
{
  "username": "test",
  "email": "test@gmail.com",
  "password": "123456"
}
```

#### <a id="categoryupdate">Update Category</a>

```json
{
  "name": "new name"
}
```

#### <a id="categorycreate">Create Category</a>

```json
{
  "name": "category name"
}
```
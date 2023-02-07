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

### CATEGORIES

| Method | Url | Description | Sample Valid Request Body |
|--------|-----|-------------|---------------------------|
| GET | /categories?page={page}&size={size}&sortBy={sortBy} | get all category | |
| GET | /categories/{id} | get category by id | |
| POST | /categories | add new category (logged in user is admin or moderator) | [JSON](#categorycreate)
| PUT | /categories/{id} | update category by id (logged in user is admin or moderator) | [JSON](#categoryupdate) |
| DELETE | /categories/{id} | delete category by id (logged in user is admin or moderator) | |

### ALBUMS

| Method | Url | Description | Sample Valid Request Body |
|--------|-----|-------------|---------------------------|
| GET | /albums?page={page}&size={size}&sortBy={sortBy} | get all album | |
| GET | /albums/{id} | get album by id | |
| GET | /albums/{id}/photos | get all photo by album id | |
| POST | /albums | add new album (logged in user is user or moderator) | [JSON](#albumcreate) |
| PUT | /albums/{id} | update album (logged in user is user or moderator) | [JSON](#albumupdate) |
| DELETE | /albums/{id} | delete album | |

### PHOTOS

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/photos?page={page}&size={size}&sortBy={sortBy} | Get all photos | |
| GET    | /api/photos/{id} | Get photo by id | |
| POST   | /api/photos | Create new photo (logged in user) | [JSON](#photocreate) |
| PUT    | /api/photos/{id} | Update photo (logged in user or logged in user is moderator) | [JSON](#photoupdate) |
| DELETE | /api/photos/{id} | Delete photo (logged in user or logged in user is moderator) | |

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

#### <a id="albumcreate">Create Album</a>

```json
{
  "title": "new album"
}
```

#### <a id="albumupdate">Update Album</a>

```json
{
  "title": "new album"
}
```

##### <a id="photocreate">Create Photo</a>

```json
{
  "title": "photo a",
  "url": "https://via.placeholder.com/600/92c952",
  "thumbnailUrl": "https://via.placeholder.com/150/92c952",
  "albumId": 2
}
```

##### <a id="photoupdate">Update Photo</a>

```json
{
  "title": "phto a",
  "url": "https://via.placeholder.com/600/771796",
  "thumbnailUrl": "https://via.placeholder.com/150/771796",
  "albumId": 2
}
```
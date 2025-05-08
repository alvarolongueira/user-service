# TODO

generar eventos de kafka
validar que no se adelantan modificaciones (version? synchronized?)

get all con paginacion

validar blank spaces
validar campos nulos
validar duplicados de mail?

logs

test unitarios
test integracion

update postman collection
ofuscar password https://docs.spring.io/spring-xd/docs/current/api/org/springframework/xd/dirt/rest/PasswordUtils.html

jacoco-report test coverage
crear usuario por grpc

update readme
    * open api spec
    * liquibase y pos
    * kafka
    * postman
    * docker

-----
```
{
"id":"d2a7924e-765f-4949-bc4c-219c956d0f8b",
"first_name": "Alice",
"last_name": "Bob",
"nickname": "AB123",
"password": "supersecurepassword",
"email": "alice@bob.com",
"country": "UK",
"created_at": "2019-10-12T07:20:50.52Z",
"updated_at": "2019-10-12T07:20:50.52Z"
}
```

The service must allow you to:
● Add a new User
● Modify an existing User
● Remove a User
"UK")
● Return a paginated list of Users, allowing for filtering by certain criteria (e.g. all Users with the country
The service must:
● Provide an HTTP and/or gRPC API
● Use a sensible storage mechanism for the Users
● Have the ability to notify other interested services of changes to User entities
● Have meaningful logs
● Have a health check
● Be well documented

--
Please also provide a README.md that contains:
● Instructions to start the application on localhost (dockerised applications are preferred)
● An explanation of the choices taken and assumptions made during development
● Possible extensions or improvements to the service (focusing on scalability and deployment to production)
We expect to be able to run the tests, build the application and run it locally.
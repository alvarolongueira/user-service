# TODO

crear open api
generar api java desde spec
generar eventos de kafka
meter version en la entity
validar que no se adelantan modificaciones (version? synchronized?)

get all con paginacion
test unitarios
test integracion

ofuscar password
crear usuario por grpc


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
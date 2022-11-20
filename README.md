# microservice_security_oauth2

ejecutar el docker compose docker-compose -f keycloak-postgres.yml up
importar el json para keycloak username: admin, pass:admin
si no funciona, importar el desde el realm y crear usuarios
cambiar el client credential para que coincida con los de keycloak tanto para microservicios como para api-gateway
en el microservicio de ms-bill, en la parte de data.sql estan seteados unos customer_bill (userID), cuando se creen los usuarios,
asignar a algunos de los bills creados el id de esos usuarios.
Lo mismo cuando se vaya a probar la coleccion en postman, cambiar el parametro por alguno id de usuario existente 

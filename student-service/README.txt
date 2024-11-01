1. Lancer docker :

docker run -d -p 27017:27017 --name mongodb -v C:/Dev/postgres-volume:/data -e MONGO_INITDB_ROOT_USERNAME=james -e MONGO_INITDB_ROOT_PASSWORD=1234 mongo:latest
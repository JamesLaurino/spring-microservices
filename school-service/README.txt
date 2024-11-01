1. Lancer docker :

docker run -d -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=SchoolDatabase mysql:latest
docker stop admin;

docker stop gateway;

docker stop eureka;

docker stop common;

docker stop mariadb;


docker rmi admin;

docker rmi gateway;

docker rmi eureka;

docker rmi common;

docker rmi mariadb;


docker network rm srs-cloud_net;

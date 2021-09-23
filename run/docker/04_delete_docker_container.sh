docker stop admin;

docker stop gateway;

docker stop eureka;

docker stop common;

docker stop mongo;

docker stop maria;


docker rmi admin;

docker rmi gateway;

docker rmi eureka;

docker rmi common;

docker rmi mongo;

docker rmi maria;


docker network rm srs-cloud_net;
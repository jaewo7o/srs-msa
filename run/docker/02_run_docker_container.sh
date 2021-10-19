docker network create srs-cloud_net

docker run -d --rm \
      --name mariadb \
      --network srs-cloud_net \
      -p 3306:3306 \
      -v ~/dev/data/mariadb/:/data/db \
      -e TZ=Asia/Seoul \
      -e MYSQL_HOST=localhost \
      -e MYSQL_PORT=3306 \
      -e MYSQL_ROOT_PASSWORD=root \
      -e MYSQL_DATABASE=srs \
      -e MYSQL_USER=srs \
      -e MYSQL_PASSWORD=srs123!! \
      mariadb:10

docker run -d --rm --name admin \
      --network srs-cloud_net \
      -p 8000:8000 \
      -e SPRING_PROFILES_ACTIVE=docker \
      admin

docker run -d --rm --name eureka \
      --network srs-cloud_net \
      -p 8761:8761 \
      eureka

docker run -d --rm --name gateway \
      --network srs-cloud_net \
      -p 443:8080 \
      -e SPRING_PROFILES_ACTIVE=docker \
      -e SERVER_SSL_KEY_STORE=file:/keystore/gateway-docker.p12 \
      -e SERVER_SSL_KEY_STORE_PASSWORD=Touchme7!! \
      gateway

docker run -d --rm --name common \
      --network srs-cloud_net \
      -e SPRING_PROFILES_ACTIVE=docker \
      common

docker cp $PWD/keystore gateway:/keystore

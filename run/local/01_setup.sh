docker network create srs-network

docker run -d --rm \
      --name redis \
      -p 6379:6379 \
      --network srs-network \
      redis

docker run -d --rm \
      --name mariadb \
      --network srs-network \
      -p 3306:3306 \
      -v ~/dev/workspace/srs-msa/data/mariadb/conf.d:/etc/mysql/conf.d \
      -v ~/dev/workspace/srs-msa/data/mariadb/data:/var/lib/mysql \
      -v ~/dev/workspace/srs-msa/data/mariadb/initdb.d:/docker-entrypoint-initdb.d \
      -e TZ=Asia/Seoul \
      -e MYSQL_HOST=localhost \
      -e MYSQL_PORT=3306 \
      -e MYSQL_ROOT_PASSWORD=root \
      -e MYSQL_DATABASE=srs \
      -e MYSQL_USER=srs \
      -e MYSQL_PASSWORD=srs123!! \
      mariadb:10

docker run -d --rm \
      --name mongodb \
      --network srs-network \
      -p 27017:27017 \
      -v ~/dev/workspace/srs-msa/data/mongodb/:/data/db \
      mongo
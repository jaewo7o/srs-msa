pid=`ps -ef | grep libs/eureka | grep -v grep | awk '{print $2}'`
kill -9 $pid

pid=`ps -ef | grep libs/common | grep -v grep | awk '{print $2}'`
kill -9 $pid

pid=`ps -ef | grep libs/gateway | grep -v grep | awk '{print $2}'`
kill -9 $pid

docker stop mariadb mongodb

docker rmi mariadb mongodb

docker network rm srs-network

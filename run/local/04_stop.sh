pid=`ps -ef | grep libs/eureka | grep -v grep | awk '{print $2}'`
kill -9 $pid

pid=`ps -ef | grep libs/common | grep -v grep | awk '{print $2}'`
kill -9 $pid

pid=`ps -ef | grep libs/gateway | grep -v grep | awk '{print $2}'`
kill -9 $pid

docker stop maria mongo redis

docker rmi maria mongo redis

docker network rm srs-network
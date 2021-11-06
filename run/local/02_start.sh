cd ../..

./gradlew build -x test

java -jar ./admin/build/libs/admin-1.0.0.jar &

java -jar ./cloud/eureka/build/libs/eureka-1.0.0.jar &

java -jar ./services/common/build/libs/common-1.0.0.jar &

java -jar ./cloud/gateway/build/libs/gateway-1.0.0.jar &

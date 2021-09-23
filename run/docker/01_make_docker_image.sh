cd ../..

projectHome=$(pwd)

cd $projectHome
./gradlew clean build -x test -Pprofile=docker

cd $projectHome/admin
docker build -t admin -f ./Dockerfile .

cd $projectHome/cloud/eureka
docker build -t eureka -f ./Dockerfile .

cd $projectHome/cloud/gateway
docker build -t gateway -f ./Dockerfile .

cd $projectHome/services/common
docker build -t common -f ./Dockerfile .
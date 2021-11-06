curl --header "Content-Type: application/json" \
    --request POST \
    --data '{"groupCode":"CODE1", "groupCodeNameKo":"KO_DESC", "groupCodeNameEn": "EN_DESC"}' \
 http://localhost:8080/common/api/group-codes

curl http://localhost:8080/common/api/group-codes/CODE1 | jq

curl -X "DELETE" http://localhost:8080/common/api/group-codes/CODE1

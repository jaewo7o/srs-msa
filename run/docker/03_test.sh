curl --header "Content-Type: application/json" \
    --request POST \
    --data '{"groupCode":"CODE1", "groupCodeNameKo":"KO_DESC", "groupCodeNameEn": "EN_DESC"}' \
-k https://localhost/common/api/group-codes

curl -k https://localhost/common/api/group-codes/CODE1 | jq

curl -X "DELETE" -k https://localhost/common/api/group-codes/CODE1

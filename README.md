# coding_test_honma

## 必要なもの

- Docker (開発時のバージョン: 20.10.7)

## DB/APIサーバーの起動

先にjarファイルの生成を行う
```
$ ./gradlew build
```

その後、docker-composeでコンテナを起動する
```
$ docker-compose up -d
```

※初回起動時は`--build`をつけて実行する
```
$ docker-compose up -d --build
```

---
## API仕様書

### ユーザー

---

### ユーザー一覧を取得
- エンドポイント: /api/user/list
- メソッド: GET

##### レスポンスボディ
```
{
    "users": [
        {
            "id": "7d6824b6-0b07-4cb9-829d-47ebe74de20d",
            "name": "test user"
        },
        {
            "id": "a05e7832-3276-40f8-9781-336a65a1a71a",
            "name": "test user2"
        }
    ]
}
```

例:
```
$ curl http://localhost:8080/api/user/list
```
---
### ユーザー詳細を取得
- エンドポイント: /api/user/{user_id}
- メソッド: GET

##### リクエストパラメータ
- user_id: string
  - ユーザーID
  - 必須

##### レスポンスボディ
```
{
    "id": "484cb6d9-d1fe-47cc-b543-cbb0b20742b9",
    "name": "test user2"
}
```

例:
```
$ curl http://localhost:8080/api/user/484cb6d9-d1fe-47cc-b543-cbb0b20742b9
```

---

### ユーザーを登録
- エンドポイント: /api/user
- メソッド: POST

##### リクエストボディ
```
{
    "name": "test user"
}
```


##### レスポンスボディ
```
{
    "id": "a05e7832-3276-40f8-9781-336a65a1a71a",
    "name": "test user2"
}
```

例:
```
$ curl -X POST -H "Content-Type: application/json" -d '{"name":"test user"}' localhost:8080/api/user
```
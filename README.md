# coding_test_honma

## 必要なもの

- Docker (開発時のバージョン: 20.10.7)

## DB/apiサーバーの起動

先にjarファイルの生成
```
$ ./gradlew build
```

その後、docker-compose起動
```
$ docker-compose up -d
```


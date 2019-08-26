# Shigure(仮称)
## API一覧
### ログイン / ログアウト
#### POST `/login`
- ログイン

##### Request
  |name|type|description|
  |---|---|---|
  |username|string|ユーザ名|
  |password|string|パスワード|

##### Response `200`
- `Set-Cookie`ヘッダにセッションIDが入ってる

##### Response `403`
- ログイン失敗
  ```
  {"timestamp":"2019-08-26T09:02:07.090+0000","status":403,"error":"Forbidden","message":"Forbidden","path":"/shigure/login"}
  ```

##### Example
```
curl -i -c cookie.txt -X POST "http://localhost:8080/shigure/login"
```

#### POST `/logout`
- ログアウト

##### Request
- `Cookie`ヘッダにセッションIDを入れる

##### Response `200`

##### Example
```
curl -i -b cookie.txt -X POST "http://localhost:8080/shigure/logout"
```

---

### その他
| type       | description                                                                                    |
| ---------- | --------------------------------------------------------------------------------------- |
| /api/**       | 未ログインで使用可能なAPI                                                               |
| /user/api/**  | ログイン必須なAPI<br>未認証でアクセスすると401                                          |
| /admin/api/** | adminユーザだけのAPI<br>未認証でアクセスすると401<br>adminユーザ以外がアクセスすると403 |


#### POST `/api/user`
- ユーザ登録

##### Request
  |name|type|description|
  |---|---|---|
  |username|string|ユーザ名|
  |passward|string|パスワード|
  |admin|boolean|adminフラグ(これ受け付けないようにしたほうがいいかもしれん…)|

##### Response `201`
```
// passwordはnullにして返してる
{"username":"test","password":null,"admin":false}
```

##### Response `409`
```
{"timestamp":"2019-08-26T09:27:01.087+0000","status":409,"error":"Conflict","message":"Username:test is already existed.","path":"/shigure/api/user"}
```

##### Example
```
curl -i -X POST "http://localhost:8080/shigure/api/user" -d "username=test1" -d "password=test1"
```

#### GET `/api/hello` `/user/api/hello` `/admin/api/hello`
- テスト用に作ったAPI
- `/api/hello` は `Hello world`が返ってくる
- `/user/api/hello` `/admin/api/hello` は `Cookie`ヘッダにセッションIDを入れる必要あり
  - `Hello {username}`が返ってくる

##### Example
```
curl -i "http://localhost:8080/shigure/api/hello"
```
```
curl -i -b cookie.txt "http://localhost:8080/shigure/user/api/hello"
```
```
curl -i -b cookie.txt "http://localhost:8080/shigure/admin/api/hello"
```

## デプロイ
1. war作成
    ```
    ./gradlew war
    ```
2. `build/libs/shigure.war` を `webapps`以下にデプロイ


## DBの設定
- DBの構造は、`resources/db/migration`を参照
- 必要があれば、`resources/application.properties`の以下の項目を変更
    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/shigure?characterEncoding=utf-8 # DBのURL
    spring.datasource.username= # ユーザ名
    spring.datasource.password= # パスワード
    ```

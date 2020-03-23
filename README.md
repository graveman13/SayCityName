# quadratic
Типова Web Гра "Міста"
Гра полягає в наступному:
1. Система пропонує місто;
2. Гравець повинен назвати слово, на останню лiтеру міста;
3. Система відповідає містом на останню лiтеру міста Гравця;

Потрібно реалізувати наступні endpoints:
* GET / begin - Система пропонує слово;
* GET / next? Word = $ word - Гравець говорить своє слово ($ word) Системі. Система у відповідь вiдправляє своє слово;
* POST / end - Система відправляє текст "Спасибі за гру";
Так само потрібно додати обробку помилок - Гравець ввів слово не на ту лiтеру.
Frontend не обов'язковий, будуть перевірятися тільки endpoints.

Проект передбачає використання готової база данних назв міст на російскій мові, в якості сервера застосовано MySQL.
Перегляд списку : [список назв міст](https://docs.google.com/spreadsheets/d/13EjHfYHkoYwOjfm4FiXrTxsnztck0tWWBtQz3m0mj1Q/edit?ouid=112141505115808143280&usp=sheets_home&ths=true)

<h6>resources.db.properties</h6>

```
db.drive=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost/city_name?serverTimezone=UTC
db.username=root
db.password=root
``` 
 Метод endingGame являється опціональним. Оскільки відсутна frontend 
 частина було приведено приклад можливого варіанта об'єкта в якості параметра.
```
 @PostMapping(value = "/end")
    public EndGameMessageDtoImpl endingGame(EndGameMessageDtoImpl endGameMessageDto) {
        return new EndGameMessageDtoImpl();
    }
```"# SayCityName" 

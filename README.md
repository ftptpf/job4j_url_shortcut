# job4j_url_shortcut

## RestFul API сервис "UrlShortCut"

Сервис позволяет зарегистрировать сайт и после аутентификации получать для него сокращенные ссылки.
Доступна статистика количества обращений к сокращенным ссылкам.
Реализованы различные виды валидации передаваемых данных и обработка исключений.
Для переадресации по сокращенным ссылкам регистрация пользователя не требуется.

### Стек технологии

В проекте используется:
- Java 17
- Spring 2.7 (boot, data, security, web, validation)
- PostgreSQL 14
- Liquibase 4.20
- Maven 3.8
- PostMan

### Требования к окружению

Для запуска проекта вам необходимо установить:
- Java 17
- PostgreSQL 14
- Maven 3.8
- PostMan

### Запуск проекта

Запустите SQL shell(psql) из пакета PostgreSQL.
Создайте базу данных проекта "urlshortcut_db" выполнив команду
```create database urlshortcut_db;```

Запустите ваш терминал с командной строкой (например для Windows это программа cmd)
перейдите в папку с вашим проектом и запустите проект командой
```mvn spring-boot:run```

После запуска, для начала работы с "RestFul API сервисом UrlShortCut" воспользуйтесь любым HTTP-клиентом, например PostMan.

### Взаимодействие с приложением

#### Регистрация сайта
```POST http://localhost:8080/api/v1/url/registration```

Для начала работы необходимо зарегистрировать website.
![Alt-текст](https://github.com/ftptpf/job4j_url_shortcut/blob/master/img/1.JPG "Регистрация сайта")
В ответ на отравленный JSON с именем сайта мы получаем подтверждение о регистрации, логин и пароль доступа в систему.
Если сайт уже зарегистрирован, мы получим соответствующее уведомление.
![Alt-текст](https://github.com/ftptpf/job4j_url_shortcut/blob/master/img/2.JPG "Сайт уже зарегистрирован")

#### Аутентификация
```POST http://localhost:8080/login```

Чтобы получить доступ к статистике и регистрации URL, необходимо пройти аутентификацию.
Отправляем JSON c логином и паролем. В ответ в HEAD получаем jwt-токен.
![Alt-текст](https://github.com/ftptpf/job4j_url_shortcut/blob/master/img/3.JPG "Логин в систему")

#### Регистрация URL
```POST http://localhost:8080/api/v1/url/convert```

Отправляем JSON с URL в ответ получаем преобразованный сокращенный код короткой ссылки.
В HEAD запросе используем jwt-токен.
![Alt-текст](https://github.com/ftptpf/job4j_url_shortcut/blob/master/img/4.JPG "Регистрация URL")

#### Переадресация кода на URL
```GET http://localhost:8080/api/v1/url/redirect/{code}}```

Отправляем URL c кодом и в ответ получаем URL для перехода. 
![Alt-текст](https://github.com/ftptpf/job4j_url_shortcut/blob/master/img/5.JPG "Переадресация")
Каждый данный запрос фиксируется в счетчике.

#### Получение статистики переадресации
```GET http://localhost:8080/api/v1/url/statistic```

В теле ответа получаем статистику переадресаций из которой мы видим количество запросов по каждому URL. 
В HEAD запросе используем jwt-токен.
![Alt-текст](https://github.com/ftptpf/job4j_url_shortcut/blob/master/img/6.JPG "Статистика")

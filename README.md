# Partnerkin Test | Timofey Voychik

## Описание модулей:
* app - Основной модуль для запуска приложения
* core-db - Модуль для работы с локальной базой данных (Room)
* core-network - Модуль для работы с сетью (Ktor)
* core-ui - Базовый модуль интерфейса (UI-модели, кастомные компоненты, стили, утилиты)
* data - Модуль работы с данными из core-db и core-network
* domain - Модуль абстракции
* feature-conference - Модуль-фича для экрана просмотра конференции
* feature-list - Модуль-фича для экрана списка конференций

## Что использовал:
Room, Ktor client, Jetpack Compose, Jetpack Navigation, Hilt, Coil, Flow, Coroutines

## Комментарий:
Сделал адаптивный UI через LazyColumn, LazyRow и FlowRow. Если нет интернета - данные берутся из локальной БД. Если при первом запуске нет интернета и БД пустая, то выводится ошибка.

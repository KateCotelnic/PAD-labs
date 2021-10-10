# **Yandex Taxi Alternative**
## **What the application must do from client’s side:**
- Register (Name, Surname, phone number, mail*) 
- Add card (card number, date, name) 
- Add address 
- История заказов 
- Login (номер, пароль) 
- Стать водителем (Имя, Фамилия, Номер телефона, есть машина или нет)(potential) 
- Новая поездка:
    - Выбор (доставка, стандарт, премиум, с грузом, на 5 и более человек) 
    - Адрес назначения 
    - Приблизительная сумма к оплате 
    - Выбор способов оплаты 
    - Приходит инфа о водителе (номер машины, цвет, марка, имя водителя, номер водителя) 
    - Заканчивается поездка, приходит инфа об поездке (время, сумма) 
    - выбор чаевых 
    - Оценка водителя
- Режим водителя (potential) 

## **External API:** ##
- GET, POST, PUT, DELETE 
- POST register data 
- POST login data 
- DELETE/PUT current user 
- POST/DELETE card data 
- POST/DELETE/PUT new address 
- GET history 
- POST type of trip 
- POST address 
- POST payment type 
- POST stop trip 
- GET info about driver+car 
- POST end of the trip 
- GET finish info about trip 
- POST payment with tips (if by card) 

## **Gateway: ** ##
- Monitoring 
- Authentication (jwt tokens) 
- Load balancer (multiple api gateway) 

## **Services:** ##
- Users’ handling  (for registration) 
- Current user  (handling gets, posts) 
    - Current User’s cards 
    - Current user’s addresses 
    - Current user’s general info 
    - Current user’s history 
- Trip user’s info to send  (when submitting a new trip) 
- Высчитывание предполагаемой оплаты 
- Trip info  (для поддержания ) 
- Payment  
- Driver’s received info 
- Trip final info to receive  

## **Technologies:** ##
- Java, Spring 
- Zuul 
- Nginx 
- Aws 

## **Architecture:** ##
![image](/docs/image.png)

The above architecture may need changes during the progress of the development. Initially, there would be 3 databases: Users-SQL, Trips-NoSQL and Payments-SQL. The Trips DB is NoSql because it is mainly more flexible, allowing to quickly and continuously integrate changes. While making requests and getting responses, the API calls will be added in the next version of the architecture, where each call and its response could be tracked.  
# Vaccine-Notifier
It is Full stack project. I have used Angular as a frontend and spring boot as a backend.

* Functionality : 
  * Send mail for email verification of user
  * User can create alert for particular location
  * when admin update vaccine data, then send alert to all users who have created alert for that location.

# Features 
Admin : 
* Update location
* Add new location
* Add vaccines
* Update vaccines

User :
* Login
* Sign up
* Create alert for particular location
* Recieve alert on emails.

# Details
* Vaccine notifier angular :  
    * It is front end application
 
* Vaccine notifier service :  
    * This is a spring boot application
    * In this application all the logic is written for add location, add vaccine, update vaccine, send mail to all users.
    * This application will stay in contact with database.

* Vaccine notifier server :
    * It is also spring boot application
    * In this application I have implemented eureka server.
    * In this application we can add N number of vaccine notifier client  apps.
    * With this server we have tried to achieve load balancing.

* Vaccine notifier client : 
    * It is wrapper spring boot application
    * front end will connect to this application.
    * This application will send request to vaccine notifier server and server will connect with vaccine notifier service.


# How to use ?

* Front end :
  * clone github frontend application 
  * open it in any IDE ( reccomended VS Code ) <br>
  * Open integrated terminal and hit command  - npm install <br>
 
* Back end : 
  * Clone application and open it in IDE. <br>
  * After that download the dependencies and run applications. <br>
  * Flow to run application  <br>
  * First run vaccine notifier server <br>
       * Then run vaccine notifier service  <br>
       * In the last run vaccine notifier client. <br>
       * And then run front end application. <br>

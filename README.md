# reservation_system

A reservation system for restaurants created by five TUM-Students. 

Using Spring Boot as Backend, React as Frontend and currently no DBMS.

## Usage

### Prerequisites
1. Create an API Key at https://developers.google.com/maps/documentation/javascript/get-api-key
2. Create a file "apikey.jsx":
`touch src/main/java/hello/world/demo/view/react-project/src/data/apikey.jsx`
3. Edit apikey.jsx:
    ```javascript
    const apikey = "your_api_key";
    export default apikey;
    ``` 
4. edit **EMAIL_PASSWORD**, **EMAIL_USERNAME** and **EMAIL_HOST** in *src/main/java/hello/world/demo/control/email/EmailServiceImpl.java*,
e.g. 
    ```java
    private static final String EMAIL_PASSWORD = "my_password";
    private static final String EMAIL_USERNAME = "my_name@yahoo.com";
    private static final String EMAIL_HOST = "smtp.mail.yahoo.com";
    ```
### Start the client and server
1. `./gradlew clean build` 
2. `chmod +x start_client && ./start_client`
3. run **DemoApplication.java** 

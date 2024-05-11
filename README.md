## Weather Forecast Backend 

### Project Description 
Backend of an application used for predicting the weather for the next 7 days.
Integrated with an external API  <a href = "https://open-meteo.com/">open-meteo</a>. <br/> 
<a href= "https://github.com/soberyoda/WeatherAppFrontend">FRONTEND REPOSITORY</a>

The created endpoint returns data in JSON format: Date, weather code, minimum and maximum temperature during the day, as well as the estimated value of generated energy.
#### Endpoint: 
```dff
/weather/forecast/${latitude}/${longitude}
```

### Sample usage
```dff
https://weatherapp-xi8f.onrender.com/weather/forecast/50.049683/19.944544
```

### Tech Stack 
<div>
    <img width="50" src="https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" alt="Spring Boot" title="Spring Boot"/>
    <img width="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java"/>
	<img width="50" src="https://user-images.githubusercontent.com/25181517/117207242-07d5a700-adf4-11eb-975e-be04e62b984b.png" alt="Maven" title="Maven"/>
	<img width="50" src="https://user-images.githubusercontent.com/25181517/117533873-484d4480-afef-11eb-9fad-67c8605e3592.png" alt="JUnit" title="JUnit"/>
    <img width="50" src="https://user-images.githubusercontent.com/25181517/183892181-ad32b69e-3603-418c-b8e7-99e976c2a784.png" alt="mocikto" title="mocikto"/>
    <img width="50" src="https://user-images.githubusercontent.com/25181517/190229463-87fa862f-ccf0-48da-8023-940d287df610.png" alt="Lombok" title="Lombok"/>
    <img width="50" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" alt="IntelliJ" title="IntelliJ"/>
</div>
<ul>
    <li>Java Oracle OpenJDK version 17.0.10</li>
    <li>Maven 3</li>
    <li>Lombok</li>
    <li>JUnit</li>
    <li>Mockito</li>
    <li>IntelliJ IDEA</li>
</ul>

### Documentation 
<details><summary><b>Configuration package</b></summary>
    <img width="auto" src="./assets/Config.png" alt="Config" title="Config"/>
</details>
<details><summary><b>Controller Package</b></summary>
    <img width="auto" src="./assets/controller.png" alt="Controller" title="Controller"/>
</details>
<details><summary><b>Model Package</b></summary>
    <img width="auto" src="./assets/model.png" alt="Model" title="Model"/>
</details>
<details><summary><b>Validation Package</b></summary>
    <img width="auto" src="./assets/validation.png" alt="Validation" title="Validation"/>
</details>
<details><summary><b>Tests</b></summary>
    <img width="auto" src="./assets/Tests.png" alt="Tests" title="Tests"/>
</details>





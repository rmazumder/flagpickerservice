
# Java Code Challenge

### Flag Picker 

Challenge:
Build a generic services using Spring Boot that allows the user to pick different world flags based on the provided JSON file (attached to this email).
Instructions: 
1. Build a generic rest based services that can pull list of items that fits for the given criterion -  Filter these options based on the user's input:-All data, if continent is provided then pull list of countries and flag. If countries is provided then send the flag. 
2. Build Unit tests for your search service 
3. Feel free to add Design pattern or more functionality as it fits this problem. 

Requirements: 
- Use Sprint Boot with Rest End points
- Only use the provided JSON file (as is)

## Solution
The FlagPicker application is a restfull webservice , implemented with Spring boot. It uses standard spring restfull webservices architeture also uses spring JPA architeture style.

 -  Modular Controller to serve the three api endpoints
 - Service and JPA layer is loosely coupled so that implementation can be changed without modifying other components.
- JSON Repository is used on top of the provided json file directly 
- Mongo Repository is used as an additional Repository. Which can be configured from application.properties 	The schema for mongo db is
   the json as it is provided.
- Spring boot actuator is addded to the project to enable monitoring and tracing of the apis.
- Mock Test case are added to test some of the MVC , service and Repository code. TODO : Need to add testcase for live api endpoint.

### How to run the application
To run the application unzip the zip provided and navigate to the root folder of the application and type
 mvn spring-boot:run 

### APIs
The application exposes 3 api endpoints and the application is configure to run on 8090 port
Example :
          http://localhost:8090/api/all
	      http://localhost:8090/api/continent/Asia
	      http://localhost:8090/api/country/India
		

GET :  

	/api/all
	Description : Return all data in json format
	Response Type : Application/JSON
	Response : [{"continentId":null,"continent":"Africa","countries":		[{"name":"Nigeria","flag":"\uD83C\uDDF3\uD83C\uDDEC"},{"name":"Ethiopia","flag":"\uD83C\uDDEA\uD83C\uDDF9"},{"name":"Egypt","flag":"\uD83C\uDDEA\uD83C\uDDEC"},{"name":"DR Congo","flag":"\uD83C\uDDE8\uD83C\uDDE9"},{"name":"South Africa","flag":"\uD83C\uDDFF\uD83C\uDDE6"}]},{"continentId":null,"continent":"America","countries":[{"name":"USA","flag":"\uD83C\uDDFA\uD83C\uDDF8"},.....]
	
	/api/continent/{continentName}
	Description : Return all the countries data of the given continent
	Response Type : Application/JSON
	Response : {"continentId":null,"continent":"Asia","countries":[{"name":"China","flag":"\uD83C\uDDE8\uD83C\uDDF3"},{"name":"India","flag":"\uD83C\uDDEE\uD83C\uDDF3"},{"name":"Indonesia","flag":"\uD83C\uDDEE\uD83C\uDDE9"},{"name":"Pakistan","flag":"\uD83C\uDDF5\uD83C\uDDF0"},{"name":"Bangladesh","flag":"\uD83C\uDDE7\uD83C\uDDE9"}]}
	
	/api/country/(countryName)
	Description : Return the flag string of the given country
	Response Type : Application/Text
	Response : 🇮🇳 (\uD83C\uDDEE\uD83C\uDDF3)

### Bonus: 
1. Performance and Audit logging enhancement to service. 
	Spring boot actuator is added in the application to monitor application health and metrics
	/actuator/health
	/actuator/metrics
	
	TODO: We can add custom monitoring and auditing using Spring AOP with Spring Interceptor. The implementation is out of scope because of time constrain
	
	
2. A metrics service that can provide - number of times certain continent/country flag has been viewed.
	Spring boot actuator metrics can be used for this. There is a special end points for getting the trace
	/actuator/trace
	We can further customize is by extending WebRequestTraceFilter to exclude certain endpoints and also extends InMemoryTraceRepository to implement custom logging.

3. Create a schema for this problem for MySQL/NoSql data store
           Mongo repository is added in the implementation. We used the same json as it is and imported in mongo db database 
	mongoimport -c continent -d test --mode upsert --file ~/study/FlagPickerService/continents.json --jsonArray
	
	For SQL , I would prefer to normalize the table and have separate table for Continent and Country and Continent to have one to many relationship with country(name, flag)

### Improvements needs to be done
-  Versioning needs been done for the APIs.
- Adequete Unit test cases needs to be added.
- Live test cases for the apis need to be added
- Custom actuator needs to be added
- Security filters needs to be added for security vulnarability
- API authorization needs to be enforced with api tokens like JWT etc..

	




# Fetch-PetStore-Api
### Aim of this api:
* Develop a console application return a list of Pets from the Pet Store
source api:  https://petstore.swagger.io/
### Tech-Stack used :
* JDK 13 : required on a machine as pre-Requisite
    * Type ```java --version``` into the console to see if you have the jdk, if not then install it first.
* Spring Boot 2.4.0
* Java 13

### Assumptions under which the console application has been developed:
1) Pets are segregated into Categories and each category can be sorted in Ascending or Descending order of Name of the pets
   * When the console api prints its result , it is of the format : ```[category id]::[{Pet1},{Pet2}]``` 
2) Some pets don't have any categories associate with them in the pet store source api. They are programmatically assigned to a category with ID: ```-9223372036854775808```
3) This api can fetch SOLD or PENDING or AVAILABLE Pets from the pet store source api.
### Usage instructions for the console application:
1) Clone the git repo using ```git clone [git repo url]```
2) Open your terminal/Console at "fetch-api/target" folder.
3) Run the following command in the terminal : ```java -jar fetch-api-0.0.1-SNAPSHOT.jar [Pet Status Type] [Pet Sort Order] ```
    * ```Pet Status Type``` : SOLD or PENDING or AVAILABLE
        * Pet Status Type is used to fetch pets which are either available for sale, or sold or pending in transactions
    * ```Pet Sort Order```: ASC or DESC
        * Pet Sort Order is used to order the pets according to their name when they are segregated into categories
### Test Cases Tested for the console application:
1) E2E Test case for fetching pets
    * Command: ```java -jar fetch-api-0.0.1-SNAPSHOT.jar AVAILABLE ASC ```
    * Output: ![E2E output](output.png?raw=true "E2E output")
2) E2E Test case for testing an error case when a wrong ```Pet Status Type``` is supplied
    * Command: ```java -jar fetch-api-0.0.1-SNAPSHOT.jar AVAILABLE!@#!@# ASC ```
    * Output: ```Error===>Wrong Pet Status Type Given. Valid Keywords are SOLD or PENDING or AVAILABLE```
3) E2E Test case for testing an error case when a wrong ```Pet Sort Order``` is supplied
    * Command: ```java -jar fetch-api-0.0.1-SNAPSHOT.jar AVAILABLE ASC!@#!@#! ```
    * Output: ```Error===>Wrong Pet Sort Order Given. Valid Keywords are ASC or DESC```


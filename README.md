
# AEMX-QUARKUS
## Intro
This is an application that provides REST API endpoints and support file uploads using the Quarkus framework and Mongo DB for storing data. These endpoints are consumed by aemx-angular project in Adobe Experience Manager (AEM) to be displayed within AEM components in Angular. The application also utilizes the aemx-apicontract project which generates the PersonApi interface and models for person endpoints.

### Key Features:

* Exposes RESTful endpoints for country and person related resources.
* Integration with the "aemx-angular" project in AEM for data display in Angular components using REST.
* Utilizes the "aemx-apicontract" project for generating the "PersonApi" interface and models.
* Uses mongo-db for storing data
* Allows CSV file upload for adding data to the system.

### Usage Guide
* Install project aemx-apicontract ([ view aemx-apicontract)](https://github.com/Aurazor/Aemx_apicontract))
* Install this project

       mvn clean install
* Start docker and run command:

       docker run -ti --rm -p 27017:27017 mongo:4.4
* Run the project

       mvn quarkus:dev

## ENDPOINTS
### Country API
#### GET (retrieves list of countries)
     curl --location 'http://localhost:8080/country'
#### POST (add new country)
    curl --location 'http://localhost:8080/country' 
    --header 'Content-Type: application/json' 
    --data '{
        "name": "Mauritius",
        "code": "mur",
        "image": "https://mauritius.png"
    }'

#### PUT (modify existing country)
    curl --location --request PUT 'http://localhost:8080/country' 
    --header 'Content-Type: application/json' 
    --data '{
            "name": "Mauritius",
            "code": "code-mur",
            "image": "https://mauritius1.png"
        }'

#### DELETE (remove country)
    curl --location --request DELETE 'http://localhost:8080/country' \
    --header 'Content-Type: text/plain' \
    --data 'Mauritius'

### Persons API
### GET (get persons)
    curl --location 'http://localhost:8080/person'
### POST (add person)
    curl --location 'http://localhost:8080/person' \
    --header 'Content-Type: application/json' \
    --data '{
        "firstName": "Nikhil",
        "lastName": "Aukhaj"
    }'


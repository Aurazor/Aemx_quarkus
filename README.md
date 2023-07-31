
# Usage Guide
### Installation / Startup
1. Install project aemx-apicontract (view project for installation guide)
2. Install this project using: **mvn clean**
3. Run the project with **mvn quarkus:dev**
4. Start docker and run command **docker run -ti --rm -p 27017:27017 mongo:4.4**

### Endpoints
#### Country REST
*  **GET** request (retrieves list of all countries): http://localhost:8080/country
* **POST** request (add a new country): http://localhost:8080/country
    * Body : {"name": "Mauritius", "code": "mur","image": "https://mauritius.png"}
* **PUT** request (update a country); http://localhost:8080/country
    * Body : {"name": "Mauritius", "code": "mur","image": "https://mauritius-1.png"}
* **DELETE** request (Delete a country); http://localhost:8080/country
    * Body : "Mauritius"

#### Person REST
*  **GET** request (retrieves list of all person): http://localhost:8080/person
* **POST** request (add a new person): http://localhost:8080/person
    * Body : {"firstName": "string","lastName": "string"}

# Transaction API
## Overview
The Transaction API is an application built with Spring Boot that provides features to manage transactions. It allows creating, deleting and get statistics of transactions in a simple and efficient way, with easy deployment using Docker.
## Technologies Used
 - **Java 21**
 - **Spring Boot**: Framework used for building the API.
 - **Docker**: Containerization of the application.
 - **Slf4j**: For logs.
 - **Spring Actuator**: For health check and performance statistics.
 - **Springdoc OpenApi**: To build the API documentation.
## Build and Run
Before you begin, make sure you have **Docker** installed.
### Step 1: Clone the Repository
Clone the repository to your local machine:
```bash
git clone https://github.com/cesar-404/transaction-api.git
cd transaction-api
```
### Step 2: Build the Docker Image
Now, you'll build the Docker image based on the Dockerfile.<br>
In the same directory where the repository was cloned (where the Dockerfile is located), run the command to build the image:
```bash
docker build -t my-app .
```
- The -t option lets you name the image (in this case, my-app).
- The . indicates that the Dockerfile is in the current directory.
This will download the base images (if you don't have them in cache) and create your custom image for the app.
### Step 3: Run the Docker Container
Once the image is built, it's time to run the container.
```bash
docker run -p 8080:8080 my-app
```
- The -p 8080:8080 maps port 8080 on your local machine to port 8080 on the container, allowing you to access the app at http://localhost:8080.
- my-app is the name of the image you created in the previous step.
### Step 4: Verify the App is Working
Now, you can verify if your app is running properly and check Swagger API documentation.<br>
open http://localhost:8080/swagger-ui/index.html in your browser.
### Step 5: Stop the Container
Once you're done testing, you can stop the container.
```bash
docker ps
docker stop CONTAINER_ID
```
## API Endpoints
#### POST /transactions
Add a transaction.
#### DELETE /transactions
Clear transactions.
#### GET /statistics
Get the transaction statistics within the indicated interval (default 60 seconds).

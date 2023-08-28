# BookMyConsultation

> Book My Consultant for doctor and patients

## Tech stack used

* ***Spring boot***
    * Doctor microservice
    * User microservice
    * appointment microservice
    * payment microservice
    * rating microservice
    * notification microservice
    * api gateway
    * eureka service discovery
    
* ***kafka***
* ***Mongo DB***
* ***SQL DATABASE***
* ***AWS Services***
    - **VPC Service**
    - **SG Service**
    - **IAM Service** for Identity access management
    - **EC2 Instance** Service
        * **for running micros services, mongo db and kafka** 
    - **RDS Service** for running SQL Database
    - **ECR Service** for storing images
    - **S3 Services** for storing files
    - **SES Service** for mailing     
* ***Docker***
    * multi stage docker build
* ***Docker Compose*** To run all microservices


## architecture

![architecture svg](./images/architecture_1.svg)
<!-- <img src="./images/architecture.svg"> -->

## more details at coelogic.pdf

[CodeLogic.pdf](./CodeLogic.pdf)
# Event sourcing with Axon (WIP)

This repository is for practicing event sourcing with Axon server and Axon framework. We will create a simple application that uses event sourcing and CQRS.

We will focus on mostly event sourcing & CQRS paradigm. So business logics can be false and irrelevant.

## Axon Server
We will use Axon server as the event store and message broker. Axon server is a zero-configuration message broker and event store. It is a dedicated server that is optimized for the Axon Framework, but it can also be used as a message broker for other systems. It is a great tool for event sourcing and CQRS.

## MongoDB
We will use MongoDB as the read model database. MongoDB is a NoSQL database that is great for storing JSON documents. It is a great tool for storing the read model.

### Running the Application
As this project runs with Spring Boot 3, we added docker-compose dependency to work with Axon server and MongoDB.

When you run the application, it will start the Axon server and MongoDB. You can access the Axon server dashboard at `http://localhost:8024` and MongoDB at `http://localhost:27017`.

Application runs in `http://localhost:8080` and you can access the application from there. It has several endpoints to create customer, add mail to customer,
create wallet for customer and get customer by id.

# Roadmap
- [x] Create a simple application with Axon server and MongoDB
- [ ] Seperate the read api from write api

<h3 align="center">Team 02 ICT3102 Assignment 2</h3>

---

## 📝 Table of Contents

- [About](#about)
- [Usage](#usage)

## ✍️ Prepared By

- [Hafiz Azmi](https://www.linkedin.com/in/hafiz-azmi-35661816a/)
- [Ho Hong Yue](https://www.linkedin.com/in/hongyue1995/)
- [Jovan Ho](https://www.linkedin.com/in/jovanho/)
- [Lee Qi Cheng](https://www.linkedin.com/in/lee-qicheng-10041b174/)
- [Lim Zheng Shun](https://www.linkedin.com/in/zheng-shun-lim-039420174/)
- [Nicholas Lee](https://www.linkedin.com/in/nicholas-lee-4ab684130/)

## 🧐 About <a name = "about"></a>

This project aims to create a system that provides object detection services. The system consists of a Single Page Application (SPA) frontend, and a backend that provides the object detection services. The project aims to create a system focused on scalability and has such been designed with reference to the microservice architecture. The microservice architecture breaks down a monolithic application into small invidividually callable services. The idea behind it is to separate all services so that instances of highly demanded services can be scaled up appropriately.

### Frontend

The system frontend is built in Vue.js, a javascript framework for building user interfaces and SPAs.

### Backend

The system backend comprises of a Zuul server, Eureka server, and Flask server.

#### Zuul

Zuul is an edge service that provides dynamic routing and load balancing services. The system uses the base functionalities of Zuul mentioned above. On boot, Zuul requests for a list of services and IPs that provided these services. Requests from the frontend are directed to the Zuul server, the Zuul server then requests for the required service from the pool of service IPs and makes the call. The response is from the service is returned to the frontend.

#### [Eureka](https://github.com/Netflix/eureka)

[Eureka](https://github.com/Netflix/eureka) is a service registry used for discovering and locating services. Eureka offers multiple REST endpoints for clients to register, and de-register themselves. Clients are required to send heartbeats to the Eureka server to renew their leases. Clients that fail to renew their lease are removed from the registry. This ensures that services listed on Eureka are actually available.

#### Flask

The original code had been converted into a REST endpoint through the use of

## 🎈 Usage <a name="usage"></a>

### Prerequisites

You will require Docker and Docker Compose to be installed on your local machine to run this project.

As we will be running 3 `yolo` containers, it is recommended to allocated at least **8GB of RAM** to your Docker Engine resources.

### Running the project

Running the command below will spin up the Docker containers that will run the application code.

```bash
docker-compose up --scale yolo=3
```

Access the frontend from `http://localhost`

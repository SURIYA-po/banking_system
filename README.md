🏦 **💡 What This Banking System Does**
---------------------------------------

This system simulates core features of a digital banking backend:

*   🔐 **User Authentication (JWT)**
    
*   👤 **Customer Management**
    
*   🏛️ **Account Management**
    
*   💳 **Transaction Management**
    
*   🔎 **Service Discovery**
    
*   🌐 **API Gateway Routing**
    

Everything is built following real microservice principles — _each service runs independently, communicates through REST APIs, and registers with Eureka for service discovery._

🧩 **🛠 Microservices Included**
--------------------------------

1️⃣ **Auth Service**→ Handles user login, registration, JWT generation & validation.

2️⃣ **Customer Service**→ Stores customer profiles, updates details, and links customers to accounts.

3️⃣ **Account Service**→ Manages accounts, balances, deposits, withdrawals, and transfers.

4️⃣ **Transaction Service**→ Records transaction history and handles money movement logic.

5️⃣ **API Gateway (Spring Cloud Gateway)**→ Routes all incoming requests to appropriate microservices with JWT checks.

6️⃣ **Eureka Service Registry**→ Automatically registers and discovers all services.

🧰 **🔧 Tech Stack Used**
-------------------------

*   **Java 17**
    
*   **Spring Boot 4**
    
*   **Spring Cloud (Eureka, OpenFeign, Gateway)**
    
*   **Spring Data JPA**
    
*   **MySQL / PostgreSQL**
    
*   **Lombok**
    
*   **Microservices Architecture**
    
*   **RESTful APIs**
    
*   **JWT Security**
    

📐 **🌍 Key Features**
----------------------

*   Fully distributed microservices environment
    
*   Secure API authentication & authorization
    
*   Independent services with resilient communication
    
*   Centralized API gateway
    
*   Database per service pattern
    
*   Clean and modular backend design
    
*   Perfect project for scalable fintech systems
    

📸 **🎯 Why I Built This**
--------------------------

To practice:

*   Real enterprise-level backend design
    
*   Microservices communication
    
*   Distributed architecture
    
*   Fault tolerance
    
*   Clean coding & separation of concerns
    

And to strengthen my skills as a **Spring Boot Backend Developer**.

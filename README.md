# Staymmate

Full-stack PG and roommate discovery for Maharashtra: React frontend, Spring Boot REST API and MySQL.

## Run

### Backend

Create a MySQL database named `staymmate`, then:

```powershell
$env:DB_USERNAME='root'
$env:DB_PASSWORD='your_password'
cd backend
mvn spring-boot:run
```

For a no-install development database (data is kept in `backend/data` between restarts):

```powershell
cd backend
mvn spring-boot:run '-Dspring-boot.run.profiles=h2'
```

### Frontend

```powershell
cd frontend
npm install
npm run dev
```

Visit `http://localhost:5173`. The API is `http://localhost:8081/api`.

## Deploy

### Run as one deployable application

Install Docker Desktop, open PowerShell in the project folder, and run:

```powershell
docker compose up --build -d
```

Open `http://localhost:8080`. React, Spring Boot and MySQL run together in containers. The MySQL `mysql-data` volume preserves profiles, requests, replies and chat messages when containers restart.

To stop it without deleting data:

```powershell
docker compose down
```

### Access it from another device

For a device on the same Wi-Fi, start the Docker version above and open `http://YOUR-COMPUTER-IP:8080` from the other device. Run `ipconfig` and use the IPv4 address shown for your active Wi-Fi adapter. Allow Docker/Desktop through Windows Firewall if prompted.

For access from anywhere on the internet without manually starting frontend or backend, copy this project to an always-on cloud VM, install Docker there, change the two passwords in `docker-compose.yml`, then run `docker compose up --build -d`. Open `http://YOUR-SERVER-IP:8080` (or connect a domain and HTTPS reverse proxy). The server, not a browser, keeps the backend and MySQL running and stores all entered data.

This project currently has demonstration profile sign-in by email. Before a public deployment with real users, add password/JWT authentication and HTTPS so profiles and private conversations are protected.

## Data note

The seed listings make every screen usable immediately. Live commercial housing inventory requires a licensed provider/direct owner onboarding and often an API key; this project does not scrape third-party sites. Replace the seed data through the REST API or an approved importer before production.

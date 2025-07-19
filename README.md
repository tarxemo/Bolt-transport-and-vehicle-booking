# 🚗 Vehicle Sharing Management System

A modern web application to manage vehicle-sharing operations, built using **Spring Boot**, **Thymeleaf**, **Hibernate/JPA**, and **PostgreSQL**. This system offers robust features for administrators, vehicle owners, and service requesters, providing clear user flows and intuitive interfaces.

---

## ✨ Features

### 👩‍💼 Admin Dashboard
- Manage users with roles (Admin, Owner, Requester).
- Approve or reject pending owners.
- Add, edit, and remove vehicle routes.
- Search and filter users by role and activity.
- Clean, responsive, and accessible management interface.

### 🚗 Vehicle & Route Management
- Owners can:
  - Register vehicles and manage their fleet.
  - View and manage routes associated with their vehicles.
- Admins can:
  - Add, edit, or delete routes.
  - Configure route distances, times, and pricing.

### 📅 Booking System
- Requesters can:
  - Browse available routes and book rides.
- Owners can:
  - Approve or reject incoming booking requests.
  - View pending bookings in an organized manner.

---

## 🛠️ Tech Stack

| Layer            | Technology           |
|------------------|----------------------|
| Backend          | Spring Boot, Spring MVC |
| ORM              | Hibernate / JPA      |
| Database         | PostgreSQL           |
| Template Engine  | Thymeleaf            |
| Frontend Styling | Tailwind CSS         |
| Build Tool       | Maven                |

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- PostgreSQL

### Setup Instructions

1. **Clone the repository:**

```bash
git clone https://github.com/your-username/vehicle-sharing-system.git
cd vehicle-sharing-system



spring.datasource.url=jdbc:postgresql://localhost:5432/vehicle_sharing
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update


Navigate to:
http://localhost:8080/admin/dashboard (Admin Dashboard)
http://localhost:8080/owner/dashboard (Owner Panel)
http://localhost:8080/requester/dashboard (Requester Panel)
# Hotel Reception Management System

The **Hotel Reception Management System** is a modular application designed to streamline various aspects of hotel operations, including room bookings, restaurant services, Wi-Fi management, and room service functionalities. Each feature is implemented as a separate service for scalability and maintainability.

**GitHub Repository**: [Hotel Reception Management System](https://github.com/anuragk456/Hotel_Reception)

---

## Services Overview

### 1. WifiService
Handles Wi-Fi-related management, such as connecting guests to the hotel network and tracking usage.

**Key Features**:
- Guest Wi-Fi registration and authentication.
- Usage monitoring and reporting.

**Structure**:
- `src`: Contains core application logic.
- `project`: Build-related configurations.
- `build.sbt`: SBT configuration for dependencies.

---

### 2. RestaurantService
Manages restaurant operations, including order processing, table reservations, and menu management.

**Key Features**:
- Handles orders from hotel guests and restaurants.
- Table reservation system.
- Billing and payment integration.

**Structure**:
- `app`: Core application logic (controllers, models, views).
- `conf`: Configuration files, including `application.conf`.
- `public`: Static files (e.g., CSS, JavaScript, images).
- `project`: Build-related configurations.
- `build.sbt`: SBT configuration for dependencies.

---

### 3. BookingService
Facilitates room bookings, guest check-ins/check-outs, and reservation management.

**Key Features**:
- Room booking and availability checks.
- Guest check-in and check-out workflows.
- Reservation conflict management.

**Structure**:
- `app`: Application logic (controllers, models, views).
- `conf`: Configuration files.
- `public`: Static files.
- `project`: Build-related configurations.
- `build.sbt`: SBT configuration for dependencies.

---

### 4. RoomService
Handles room-related operations, including updating statuses (available, occupied, under maintenance).

**Key Features**:
- Tracks room availability and status.
- Integrates with other services for real-time updates.

**Structure**:
- `src`: Contains core application logic.
- `project`: Build-related configurations.
- `build.sbt`: SBT configuration for dependencies.

---

## Tools and Technologies

- **Programming Language**: Scala
- **Frameworks**: Play Framework, Akka
- **Database**: MySQL
- **Build Tool**: SBT
- **Containerization**: Docker

---

## How to Set Up and Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/anuragk456/Hotel_Reception
   cd HotelReception

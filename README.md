# Resort Management System

A desktop application built with Java Swing/JavaFX for managing resort operations — including room management, customer records, bookings, payments, and admin login access.

## Description

This project is a Resort Management System developed as a team project (5 members). It allows resort staff to manage rooms, register and search customers, create and cancel bookings, process payments, and restrict access through an admin login panel.

## Features

### Room Management
- Add rooms with room number, type (DELUXE, SUPER_DELUXE, KING_SIZE), and price
- View all rooms
- Book / free a room
- Set / end maintenance status

### Customer Management
- Add new customers (name, National ID, phone) with phone number validation
- Search customer by National ID
- View all customers
- Delete customer by phone number

### Booking Management
- Create a booking by linking a customer to a room (with check-in/check-out dates and stay duration)
- Automatic total cost calculation (price × days)
- Prevents double-booking an already booked room
- Search booking by room number or customer phone
- Cancel/delete a booking (automatically frees the room)

### Payment System
- Record a payment with amount and method (Cash, Card, Mobile Banking)
- View list of all payments made
  
### Resort Service
- Add resort services with a service name and price
- View the list of all available/added services

### Admin Login
- Secure login panel with ID and password
- Clears input fields on demand

## Technologies Used

- **Language:** Java
- **GUI Framework:** Java Swing
- **Build Tool:** Apache Ant
- **IDE:** NetBeans

## How to Run

1. Clone the repository:
```bash
   git clone https://github.com/Ahanaf242/Resortmanagementsystem.git
```
2. Open the project in **NetBeans** (uses an Ant-based `nbproject` setup).
3. Build the project:
```bash
   ant build
```
4. Run the project (starting from `AdminGUI` for login, or any module's GUI directly):
```bash
   ant run
```
   Or press **Run** inside NetBeans.

## Project Structure
- `dist/` – Compiled build output
- `lib/` – External libraries
- `nbproject/` – NetBeans project configuration
- `src/resortmanagemn/` – Main source code
  - `Room.java`, `RoomGUI.java` – Room management
  - `Customer.java`, `CustomerManager.java`, `CustomerGUI.java`, `CustomerManagerGUI.java` – Customer management
  - `Booking.java`, `BookingManager.java`, `BookingGUI.java`, `BookingManagerGUI.java` – Booking management
  - `Paymentsystem.java`, `PaymentsystemGUI.java` – Payment system
  - `Resortservice.java`, `ResortserviceGUI.java` – Resort service management
  - `User.java`, `AdminGUI.java` – Admin login
- `build.xml` – Ant build script
- `manifest.mf` – Manifest file
  
## Team Members & Contributions

- Tanbin – Room management (`Room`, `RoomGUI`)
- Mitu – Customer management (`Customer`, `CustomerManager`, `CustomerGUI`, `CustomerManagerGUI`)
- Hira – Booking management (`Booking`, `BookingManager`, `BookingGUI`, `BookingManagerGUI`)
- Ful – Payment system (`Paymentsystem`, `PaymentsystemGUI`) and Resort Service (`Resortservice`, `ResortserviceGUI`)
- Ahanaf – Admin login (`User`, `AdminGUI`); merged all branches, resolved conflicts, and ensured the final build runs

## Citation
https://drive.google.com/file/d/1ybNNV7RGUKxiHQwxwlfDaya6rToy5Te4/view

## Instructor Information
Teacher Name       :  Md. Mezbaul Islam Zion                                   
Designation        : Lecturer  
Department         : Computer Science and Engineering (DIU)

## License

This project was developed for academic purposes.

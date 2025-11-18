# FlightBooking

A Spring Boot-based flight booking system with scheduling, ticketing, and search functionality.
---
## Features

### Airline Schedule
- Add new flight schedules (`AirLineService` / `AirlineController`)
- Validations:
    - Flight exists
    - Departure time is not in the past
    - Departure and arrival cities exist
    - Schedule time does not overlap with existing schedules

### Flight Search
- Search flights by departure date, source city, and destination city (`SearchService` / `SearchController`)
- Validations:
  - Throws `CityNotFoundException` if cities are invalid

### Ticket Booking
- Book tickets for a flight (`TicketBookingService` / `BookingController`)
- Validations:
    - Seats are available
    - Seats are not already booked
    - Handles optional return trip
- Saves passengers and booked seats

### Ticket Management
- Find ticket by PNR (`TicketDetailsService` / `TicketController`)
- Search booking history by user email (`TicketDetailsService` / `BookingController`)
- Cancel tickets: frees booked seats and deletes passengers
- Validations:
  - Throws `UsersNotFoundException` if user not found
  - Throws `TicketNotFoundException` if ticket not found

---


## Endpoints
### 1) Add Flight Schedule

**Endpoint:**  
`POST /api/v1.0/flight/airline/inventory/add`

**Request Body:**

```json
{
  "flight": {
    "id": 1
  },
  "fromCity": {
    "id": 4
  },
  "toCity": {
    "id": 2
  },
  "departureDate": "2026-01-22",
  "departureTime": "2026-01-21T09:30:00",
  "price": 4500.50,
  "seatsAvailable": 120,
  "duration": 180
}
```
**Response Body:**
```json
{
    "id": 10005,
    "flight": {
        "id": 1,
        "name": "AN101",
        "year": "2025",
        "rows": 30,
        "columns": 6,
        "airline": {
            "id": 1,
            "airlineName": "Air India"
        }
    },
    "fromCity": {
        "id": 4,
        "cityName": "Bengaluru",
        "airportCode": "BLR"
    },
    "toCity": {
        "id": 2,
        "cityName": "Mumbai",
        "airportCode": "BOM"
    },
    "departureDate": "2026-01-22",
    "departureTime": "2026-01-21T09:30:00",
    "price": 4500.5,
    "seatsAvailable": 120,
    "duration": 180
}
```
### 2) Searches for flight

**Endpoint:**  
`POST /api/v1.0/flight/search`

**Request Body:**

```json
{
    "fromCity": "Mumbai",
    "toCity":"Bengaluru",
    "date" : "2025-01-15"
}
```
**Response Body:**
```json
    [
  {
    "id": 1,
    "flight": {
      "id": 3,
      "name": "BS303",
      "year": "2025",
      "rows": 32,
      "columns": 6,
      "airline": {
        "id": 2,
        "airlineName": "Indigo"
      }
    },
    "fromCity": {
      "id": 2,
      "cityName": "Mumbai",
      "airportCode": "BOM"
    },
    "toCity": {
      "id": 4,
      "cityName": "Bengaluru",
      "airportCode": "BLR"
    },
    "departureDate": "2025-01-15",
    "departureTime": "2025-01-15T14:30:00",
    "price": 4500.5,
    "seatsAvailable": 126,
    "duration": 180
  },
  {
    "id": 2,
    "flight": {
      "id": 1,
      "name": "AN101",
      "year": "2025",
      "rows": 30,
      "columns": 6,
      "airline": {
        "id": 1,
        "airlineName": "Air India"
      }
    },
    "fromCity": {
      "id": 2,
      "cityName": "Mumbai",
      "airportCode": "BOM"
    },
    "toCity": {
      "id": 4,
      "cityName": "Bengaluru",
      "airportCode": "BLR"
    },
    "departureDate": "2025-01-15",
    "departureTime": "2025-01-15T14:30:00",
    "price": 4500.5,
    "seatsAvailable": 126,
    "duration": 180
  }
]
```
### 3) Book Ticket

**Endpoint:**  
`POST /api/v1.0/flight/booking/{flightid}`

**Request Body:**
``` json
{
  "user": {
    "id": 3,
    "name": "Vinod Patel",
    "email": "vinod@example.com",
    "gender": "MALE"
  },
  "scheduleId": 1,
  "returnTripId": null,
  "passengers": [
    {
       "name": "Ramesh Kumar",
       "gender": "MALE",
       "mealOption": "VEG",
       "seatPosition": "17A"
    },
    {
      "name": "Anita Singh",
      "gender": "FEMALE",
      "meal": "NONVEG",
      "seatPos": "17B"
    }
  ]
}
```
**Response Body**
``` json
{
    "id": 6,
    "pnr": "PNR17634284600131",
    "bookedByUsers": {
        "id": 3,
        "name": "Vinod Patel",
        "email": "vinod@example.com",
        "gender": "MALE"
    },
    "schedule": {
        "id": 1,
        "flight": {
            "id": 3,
            "name": "BS303",
            "year": "2025",
            "rows": 32,
            "columns": 6,
            "airline": {
                "id": 2,
                "airlineName": "Indigo"
            }
        },
        "fromCity": {
            "id": 2,
            "cityName": "Mumbai",
            "airportCode": "BOM"
        },
        "toCity": {
            "id": 4,
            "cityName": "Bengaluru",
            "airportCode": "BLR"
        },
        "departureDate": "2025-01-15",
        "departureTime": "2025-01-15T14:30:00",
        "price": 4500.5,
        "seatsAvailable": 126,
        "duration": 180
    },
    "returnTrip": null,
    "passengers": [
        {
            "id": 11,
            "name": "Ramesh Kumar",
            "gender": "MALE",
            "mealOption": "VEG",
            "seatPosition": "17A"
        },
        {
            "id": 12,
            "name": "Anita Singh",
            "gender": "FEMALE",
            "mealOption": "NONVEG",
            "seatPosition": "17B"
        }
    ],
    "status": "BOOKED"
}
```
### 4) Get Booked ticket data

**Endpoint:**  
`GET /api/v1.0/flight/ticket/{pnr}`

**Response Body:**
``` json
{
    "id": 5,
    "pnr": "PNR10005",
    "bookedByUsers": {
        "id": 2,
        "name": "Priya Sharma",
        "email": "priya@example.com",
        "gender": "FEMALE"
    },
    "schedule": {
        "id": 3,
        "flight": {
            "id": 2,
            "name": "AN202",
            "year": "2025",
            "rows": 28,
            "columns": 6,
            "airline": {
                "id": 1,
                "airlineName": "Air India"
            }
        },
        "fromCity": {
            "id": 2,
            "cityName": "Mumbai",
            "airportCode": "BOM"
        },
        "toCity": {
            "id": 4,
            "cityName": "Bengaluru",
            "airportCode": "BLR"
        },
        "departureDate": "2025-01-15",
        "departureTime": "2025-01-15T14:30:00",
        "price": 4500.5,
        "seatsAvailable": 126,
        "duration": 180
    },
    "returnTrip": null,
    "passengers": [
        {
            "id": 9,
            "name": "Vinod Patel",
            "gender": "MALE",
            "mealOption": "VEG",
            "seatPosition": "16A"
        },
        {
            "id": 10,
            "name": "Sita Reddy",
            "gender": "FEMALE",
            "mealOption": "NONVEG",
            "seatPosition": "16B"
        }
    ],
    "status": "CANCELED"
}
```
### 5) Get booked ticket history based on email

**Endpoint:**  
`GET /api/v1.0/flight/booking/history/{emailId}`

**Response body:**

```json
[
  {
    "id": 1,
    "pnr": "PNR10001",
    "bookedByUsers": {
      "id": 1,
      "name": "Arun Kumar",
      "email": "arun@example.com",
      "gender": "MALE"
    },
    "schedule": {
      "id": 3,
      "flight": {
        "id": 2,
        "name": "AN202",
        "year": "2025",
        "rows": 28,
        "columns": 6,
        "airline": {
          "id": 1,
          "airlineName": "Air India"
        }
      },
      "fromCity": {
        "id": 2,
        "cityName": "Mumbai",
        "airportCode": "BOM"
      },
      "toCity": {
        "id": 4,
        "cityName": "Bengaluru",
        "airportCode": "BLR"
      },
      "departureDate": "2025-01-15",
      "departureTime": "2025-01-15T14:30:00",
      "price": 4500.5,
      "seatsAvailable": 126,
      "duration": 180
    },
    "returnTrip": null,
    "passengers": [
      {
        "id": 1,
        "name": "Arun Kumar",
        "gender": "MALE",
        "mealOption": "VEG",
        "seatPosition": "12A"
      },
            {
                "id": 2,
                "name": "Priya Sharma",
                "gender": "FEMALE",
                "mealOption": "NONVEG",
                "seatPosition": "12B"
            }
        ],
        "status": "BOOKED"
    }
]
```
### 6) Delete a ticket using pnr

**Endpoint:**
`DELETE /api/v1.0/flight/booking/cancel/{pnr}`

**Response Body**
```json
{
    "id": 6,
    "pnr": "PNR17634284600131",
    "bookedByUsers": {
        "id": 3,
        "name": "Vinod Patel",
        "email": "vinod@example.com",
        "gender": "MALE"
    },
    "schedule": {
        "id": 1,
        "flight": {
            "id": 3,
            "name": "BS303",
            "year": "2025",
            "rows": 32,
            "columns": 6,
            "airline": {
                "id": 2,
                "airlineName": "Indigo"
            }
        },
        "fromCity": {
            "id": 2,
            "cityName": "Mumbai",
            "airportCode": "BOM"
        },
        "toCity": {
            "id": 4,
            "cityName": "Bengaluru",
            "airportCode": "BLR"
        },
        "departureDate": "2025-01-15",
        "departureTime": "2025-01-15T14:30:00",
        "price": 4500.5,
        "seatsAvailable": 126,
        "duration": 180
    },
    "returnTrip": null,
    "passengers": [
        {
            "id": 11,
            "name": "Ramesh Kumar",
            "gender": "MALE",
            "mealOption": "VEG",
            "seatPosition": "17A"
        },
        {
            "id": 12,
            "name": "Anita Singh",
            "gender": "FEMALE",
            "mealOption": "NONVEG",
            "seatPosition": "17B"
        }
    ],
    "status": "CANCELED"
}
```

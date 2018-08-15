# Delivery Now!

Rest API for restaurant and delivery.


## How to build

The service is built using `gradle`, so building it is just a matter of running:

```
gradle build

```

If you want to build the docker image, you should run:

```
gradle build docker

```

## How to run

#### Pulling the docker images from the server

I made all the images available in DockerHub, so save this file as `docker-compose.yml`:

```
version: '2.0'

services:

  postgres:
    image: postgres
    restart: always
    environment:
      POSTGRES_USER: delivernow
      POSTGRES_PASSWORD: 123456

  notifications-api:
    image: docker.io/ricardobevi/notifications-api
    restart: always

  delivernow:
    image: docker.io/ricardobevi/deliver-now
    restart: always
    environment:
      SPRING_PROFILES_ACTIVE: prod
    ports:
      - 8081:8081
```

And in the same directory run

```
docker-compose up
```

That should download the images and lunch this service, a postgresql database and a notification service.

#### With local images

You should build an image for [notifications-api](https://github.com/ricardobevi/notifications-api) fist. The steps are the same as for this service.

Then build this project and use the included `yml` file to run 

```
docker-compose up
```

#### Life is too short for a build

Fine, I agree, that's why I took the time to put it all on a [server](http://142.93.81.129:8081/restaurant?minRating=3). Also there is a Postman collection to requests to this server.


## Notifications API

Fist, you should know that I'm terrible with naming things. Having said that, one requirement was to have a separate service to send SMSs and EMails. This service provides that in a way. Today is just a mock to show the possibility of communication between two services.

You can find this project [here](https://github.com/ricardobevi/notifications-api).

## Endpoints

### /order

Places an order on the given restaurant. 

> WARNING! ETA service will only work if called from my server (listed above). Otherwise the call will fail because it is using an unauthorized IP.

Sample request:

```
{
	"meals": [
		{
            "name": "Fried Potatoes",
            "description": "Yummy potatoes",
            "price": 2.5
        },
    	{
            "name": "Baked Potatoes",
            "description": "Dummy potatoes",
            "price": 3.5
        }
	],
	"totalCost": 6.0,
	"address": "221b Baker Street",
	"latLong": "-34.598310,-58.417344"
}
```

Sample response:

```
{
    "error": false,
    "status": "Order placed successfully",
    "eta": "Your order will arrive in 33 mins"
}
```

##### Checks for /order

- Missing fields: no field can be null.
- Restaurant can't fullfill the order: the restaurant doesn't serve those meals.
- Price check: the total cost equals the sum of the meals price.
- Correct LatLong: checks for correct latitude and longitude.
- Restaurant doesn't exist.


### /restaurant

Delete, edit and list restaurants.

#### GET /restaurant

Sample `GET` request ([try it!](http://142.93.81.129:8081/restaurant?minRating=3)):

```
http://142.93.81.129:8081/restaurant?minRating=3
```

Sample `GET` response:


```
[
    {
        "id": 1,
        "rating": 4.5,
        "reviews": [
            {
                "name": "Richard",
                "review": "Nice place!",
                "rating": 4
            },
            {
                "name": "Anne",
                "review": "I LOVE POTATOES!",
                "rating": 5
            }
        ],
        "meals": [
            {
                "name": "Fried Potatoes",
                "description": "Yummy potatoes",
                "price": 2.5
            },
            {
                "name": "Baked Potatoes",
                "description": "Dummy potatoes",
                "price": 3.5
            }
        ],
        "location": {
            "latitude": -34.629359,
            "longitude": -58.537554
        },
        "commercialEmail": "commercial.email@mail.com",
        "logo": "http://restaurant.com/logo.png",
        "commercialName": "Betty's",
        "legalName": "BETT",
        "adminNumber": "343444442233",
        "address": "221b Baker Street"
    }
]
```

#### DELETE /restaurant

Sample `DELETE` request:

```
http://142.93.81.129:8081/restaurant/1
```

##### Checks for /restaurant DELETE

- Restaurant doesn't exist.


#### PUT /restaurant

Sample `PUT` request:


```
{
    "id": 1,
    "rating": 4.5,
    "reviews": [
        {
            "name": "Richard",
            "review": "Nice place!",
            "rating": 4
        },
        {
            "name": "Anne",
            "review": "I LOVE POTATOES!",
            "rating": 5
        }
    ],
    "meals": [
		{
            "name": "Fried Potatoes",
            "description": "Yummy potatoes",
            "price": 2.5
        },
    	{
            "name": "Baked Potatoes",
            "description": "Dummy potatoes",
            "price": 3.5
        }
    ],
    "location": {
        "latitude": -34.629359,
        "longitude": -58.537554
    },
    "commercialEmail": "commercial.email@mail.com",
    "logo" : "http://restaurant.com/logo.png",
	"commercialName" : "Betty's",
	"legalName" : "BETT",
	"adminNumber" : "343444442233",
	"address" : "221b Baker Street"
}
```


Sample `PUT` response:


```
{
    "id": 1,
    "rating": 4.5,
    "reviews": [
        {
            "name": "Richard",
            "review": "Nice place!",
            "rating": 4
        },
        {
            "name": "Anne",
            "review": "I LOVE POTATOES!",
            "rating": 5
        }
    ],
    "meals": [
		{
            "name": "Fried Potatoes",
            "description": "Yummy potatoes",
            "price": 2.5
        },
    	{
            "name": "Baked Potatoes",
            "description": "Dummy potatoes",
            "price": 3.5
        }
    ],
    "location": {
        "latitude": -34.629359,
        "longitude": -58.537554
    },
    "commercialEmail": "commercial.email@mail.com",
    "logo" : "http://restaurant.com/logo.png",
	"commercialName" : "Betty's",
	"legalName" : "BETT",
	"adminNumber" : "343444442233",
	"address" : "221b Baker Street"
}
```


### /review

Adds reviews to a restaurant.

Sample request:

```
{
	"name": "Ricky",
	"review": "Nice review",
	"rating": 1.5
}
```

Sample response:

```
{
    "id": 1,
    "rating": 3.5,
    "reviews": [
        {
            "name": "Richard",
            "review": "Nice place!",
            "rating": 4
        },
        {
            "name": "Anne",
            "review": "I LOVE POTATOES!",
            "rating": 5
        },
        {
            "name": "Ricky",
            "review": "Nice review",
            "rating": 1.5
        }
    ],
    "meals": [
        {
            "name": "Fried Potatoes",
            "description": "Yummy potatoes",
            "price": 2.5
        },
        {
            "name": "Baked Potatoes",
            "description": "Dummy potatoes",
            "price": 3.5
        }
    ],
    "location": {
        "latitude": -34.629359,
        "longitude": -58.537554
    },
    "commercialEmail": "commercial.email@mail.com",
    "logo": null,
    "commercialName": null,
    "legalName": null,
    "adminNumber": null,
    "address": null
}
```

##### Checks for /review

- Missing fields: no field can be null.
- Restaurant doesn't exist.
- Rating between 1 and 5.


## About the code

This code tries to follow the guidelines suggested in the following books.

> Clean Code: A Handbook of Agile Software Craftsmanship (Robert C. Martin)

Gives guidelines to writing nice code that can be tested and extended with ease.

> Clean Architecture: A Craftsman's Guide to Software Structure and Design (Robert C. Martin Series)

This book introduces the concept of [clean architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html).

Also, in order to organize my workflow I took advice from:

> Agile Estimating and Planning by Mike Cohn

And also:

> User Stories Applied by Mike Cohn


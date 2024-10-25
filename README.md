# customer-list-application

This is a sample application for review, and also a bit of a demo-project I might expand upon in the future when learning about new technologies.

Reality however is that time ran short and I had other pledges to attend to. But, I did cross off the various requirements for the coding assignment. 

If I had more time I would have made environment configurations for local and when running in Docker, but since I fell short on time before the weekend I had to sum this up. **Because of this, there might exist some code/files that are environment configurations, and again.. Time fell short on my end here.** (And paradoxically there might be configuration files with the same configuration variables but commented out..)SS

I would have added update and delete functionality as well as this was the added bonus, but I instead wanted to try and implement my own Quarkus-with-Cucumber testing project and run that in a container. Testing is essential as is a good CI/CD strategy so that is why I chose to achieve that out of the bonus excersises instead of the update and delete functionality.

## Comments-in-code

Good code is self-explanatory, and I am happy to explain my reasoning to any inspector. When you talk about code, you seem to learn more about code! But since I follow the principles of clean code I avoid commenting code as much as possible.

_However, if I had to write some sort of special and crude logic that would be hard to understand, I would write a comment to explain the reasoning behind it.. **I'm not a monster!**_

## Running the application

To run the application as well as generate a proper set of data (IE our Customers), you can run the following command:
```./run-application.ps1```

If however you cannot run PowerShell scripts, you can run the following command:
```docker-compose -f docker-compose.yml up --build```
However, you will not have any data in your database.. So you will have to create some customers yourself using the API. **Examples of doing so exists in the http_examples folder.**

This will launch the backend at `http://localhost:8080` and the frontend at `http://localhost:3000`.
Access to the Swagger UI in the backend is available at `http://localhost:8080/q/swagger-ui/`.
And if you wish to perform a health check: `http://localhost:8080/q/health`. (However, the frontend-application will do a health-check before fetching the customers, as will the testing suite)

All operations within the backend are also described under `http-examples/backend/backend.http`, if you have the HTTPClient extension for VSCode installed.

## Running the tests

To run the testing suite:
```docker-compose -f docker-compose.test.yml up --build```

The tests rely on data existing in the database, so if you cannot run the PowerShell script or if you do not generate customers, some tests will fail.

Unit tests on the other hand work without the need of a database, and these tests can be found in the backend application.

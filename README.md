# Table Display

A simple display of a database table with full CRUD operations.

## Building
Build via Gradle: `./gradlew build`.

To gather all necessary dependencies, running `./gradlew --refresh-dependencies` may be necessary.

## Running
Docker Desktop is required for setup. I haven't ran this project from scratch, so I may be missing a step or two.

To instantiate the project's database run: `docker compose up`.

After the database is setup, run the application through your IDE, or by running `DisplayTableApplication`. 
Once running, navigate to [http://localhost:8080/](http://localhost:8080/).
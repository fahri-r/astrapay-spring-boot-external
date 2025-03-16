# Spring Boot Astrapay Base Project
I developed this application with a focus on developer experience, so the features included are only examples. Several dependencies have been changed from the Spring Boot Astrapay Base Project, including:

- io.springfox has been replaced with org.springdoc due to compatibility issues with the Spring Boot version in use
- org.mapstruct has been added to simplify the conversion between entities and DTOs.

Additionally, I’ve incorporated base or parent classes in some of the classes to reduce redundant code. To ensure software quality, I also added tests including positive case and negative case. Finally, I’ve included configurations for CORS.

## Usage Instructions

To run this project, here are the steps you can follow:

1. Clone the repository from https://github.com/fahri-r/astrapay-spring-boot-external.git
2. Load the project in your IDE (IntelliJ, Eclipse)
3. Run the project using the Run button in the top-right corner or by typing the following commands:
```
mvn install
mvn spring-boot:run
```

Also if you wanna run the frontend, open the terminal and run these command:
```
cd ./frontend
yarn install
yarn start
```

## Screenshoots
### Frontend
![An empty notes](/img/fe-1.png "An empty notes")
![Multiple notes](/img/fe-2.png "Multiple notes")
![Edit form](/img/fe-3.png "Edit form")
![Create form](/img/fe-4.png "Create form")

### Backend
#### Swagger
![Swagger](/img/swagger.png "Swagger")

#### GET /notes
![Get all notes](/img/notes-1.png "Get all notes")
![Get all notes when empty](/img/notes-2.png "Get all notes when empty")

#### GET /notes/{id}
![Get note by id](/img/note-1.png "Get note by id")
![Get note by id when not found](/img/note-2.png "Get note by id when not found")

#### POST /notes
![Create note](/img/create-note-1.png "Create note")
![Create note invalid](/img/create-note-2.png "Create note invalid")

#### PUT /notes/{id}
![Update note](/img/update-note-1.png "Update note")
![Update note invalid](/img/update-note-2.png "Update note invalid")

#### DELETE /notes/{id}
![Delete note](/img/delete-note-1.png "Delete note")
![Delete note invalid](/img/delete-note-2.png "Delete note invalid")


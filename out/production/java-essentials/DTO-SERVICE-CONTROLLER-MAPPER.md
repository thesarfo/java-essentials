## Relationships Between DTOs, Controllers, Services, and Mappers in Spring Applications

**1. DTO (Data Transfer Object)**

* A lightweight object designed for data transfer between application layers (controller ↔ service, service ↔ external API).
* Contains a subset of properties from a complex domain model entity.
* Focuses on data exchange rather than complex business logic.
* Improves separation of concerns and data encapsulation.

**2. Controller**

* The entry point for user requests (often web API requests).
* Receives requests, interacts with services to process logic, and returns responses.
* Uses annotations like `@GetMapping`, `@PostMapping`, etc. to handle different HTTP request types (GET, POST, PUT, DELETE).
* Typically receives and returns DTO objects, separating the API layer from the internal domain model.

**3. Service**

* Encapsulates the core business logic of an application.
* Interacts with repositories (data access), performs calculations/validations, and orchestrates functionalities.
* Doesn't have a direct dependency on the web layer (controllers).
* Might receive/return DTO objects (after conversion from/to entity format) for service communication.

**4. Mapper**

* Responsible for converting data between different formats (DTO ↔ Entity).
* Takes a DTO object and creates a corresponding entity object (and vice versa).
* Maintains clean separation of concerns and ensures proper data representation for each layer.

**Relationships:**

1. Controllers receive requests with data in an API-suitable format (often JSON/XML). This data might be mapped to a DTO object.
2. Controllers interact with services, passing DTO objects as arguments.
3. Services use DTO objects or retrieve data from repositories based on DTO information.
4. Services might use mappers to convert DTOs to entities before performing domain model operations.
5. Services process requests using business logic and might interact with other services/repositories.
6. To return data, services might convert entities back to DTOs using mappers.
7. Controllers receive DTOs (potentially after conversion from entity format) from services and prepare the response body.
8. Controllers return the HTTP response containing DTO data (often serialized to JSON/XML) to the client.

**Key Points:**

* DTOs facilitate data exchange between layers.
* Controllers handle requests and responses.
* Services encapsulate business logic and interact with repositories.
* Mappers translate between DTO and entity formats.
* These components work together for a well-structured and maintainable application.


## Implementing a DTO 

For instance, we have a student entity, which has all the information about the user. Now when making a request that needs the student as a request body, we dont want to send the entire user object. This is where a dto comes in. In a DTO object, we can specify only the fields in the student entity we need to make the request, and then pass the dto as a request body. see below

```java
public record StudentDto(
    String firstName,
    String lastName,
    String email
){

}
```

Now our controller can look like this
```java
@PostMapping("/students")
public Student post(
    @RequestBody StudentDto student
){
    return repository.save(student);
}
```

Now, perhaps the repository is expecting to save a student object, but we are passing a dto object to it. Which means that we need to have a method that converts the dto object into an actual student entity. 

```java
private Student toStudent(StudentDto dto){
    // we can use the constructor to do the conversion or we can use getters and setters. below we use getters and setters
    var Student = new Student();
    student.setFirstName(dto.firstname());
    student.setLastName(dto.lastname());
    student.setEmail(dto.email());
}
```

And now in our controller, we have to create a student object, which calls the toStudent method and takes the dto as a param. see below

```java
@PostMapping("/students")
public Student post(
    @RequestBody StudentDto studenDto
){
    var student = toStudent(studentDto);
    return repository.save(student);
}
```

But if you look at the controller above, we actually return the entire Student entity/object, which will still return the sensitive stuff of the student. So now we need to create another dto, that we will use to handle our responses. see below

```java
public record StudentResponseDto(
    String firstname,
    String lastname,
    String email
){
      
}
```

And now instead of returning a Student in the controller, we return a StudentResponseDto

```java
@PostMapping("/students")
public StudentResponseDto post(
    @RequestBody StudentDto studenDto
){
    var student = toStudent(studentDto);
    return repository.save(student);
}
```

But note that, the repository is returning a Student object, but we are returning a StudentReponseDto on the method level. So we need to create a method which then takes a student object as a param, and then convert it into a StudentResponseDto. see below

```java
private StudentResponseDto toStudentResponseDto(Student student){
    return new StudentREsponseDto(
        student.getFirstName(),
        student.getLastName(),
        student.getEmail()
    );
}
```

And now, in our controller, we need to convert the dto to a student object, and then return the student object as a studentresponsedto object. see below

```java
@PostMapping("/students")
public Student post(
    @RequestBody StudentDto studenDto
){
    var student = toStudent(studentDto);
    var savedStudent = repository.save(student);
    return toStudentResponseDto(savedStudent);
}
```
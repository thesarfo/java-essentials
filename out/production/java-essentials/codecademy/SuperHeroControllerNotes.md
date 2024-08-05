Responding to HTTP requests is a complicated process. Thankfully, the Spring framework handles this complexity and allows us to easily use the functionality. Even when exceptions are thrown in Spring applications, the framework provides a way for us to access more information about the errors using the ResponseStatusException class or the @ResponseStatus annotation.

With the annotations we learned in this lesson, we can now:

1. Map HTTP requests to controllers and methods (@RestController and @RequestMapping)


2. Specify a path attribute to become a base path (@RequestMapping at the class level)


3. Declare request types using HTTP method annotations (@GetMapping, @PostMapping, @PutMapping, and @DeleteMapping)


4. Access request parameters in a method (@RequestParam)


5. Bind data using template variables (@PathVariable)


6. Fine-tune the status code returned by a method (@ResponseStatus)


7. All of these annotations and ResponseStatusException are imported from the org.springframework.web.bind.annotation package.
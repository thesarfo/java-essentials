## Getting started with Spring MVC

Lets say we want to add a static web page with the content we wanna display in the browser. To do that, we first have to write an html document with the content we wanna be displayed, and then we write a controller with an action for the web page created.

To mark a class as a controller, you use the @Controller annotation. This means that Spring will add a bean of this class to its context to manage it. Inside the controller class, you can define controller actions whhich are methods associated with specific HttP requests.

For instance, we want the browser to display the web page when the user accesses the /home path. We will annotate the action method with the @RequestMapping annotation, specifying the path as a value of the annotation. @RequestMapping("/home"). The method needs to return, as a string, the name of the document we want the app to send as a response. see below
```java
@Controller
public class MainController {
    @RequestMapping("/home")
    public String home(){
        return "home.html";
    }
}
```

First we have annotated the outer class as a Controller. and then we are mapping the first method with the path "/home". so that it will return a static page called "home.html".

You can use thymeleaf as a template engine to display dynamic content in the webpage. if so, put the html file in the resources/templates folder instead of resources/static. An example thymeleaf html file looks like this
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1>Welcome <span th:style="'color:' + ${color}"
                          th:text="${username}"></span>!</h1>
    </body>
</html>
```
refer to ch8-ex1 in the spring start here directory for more

## Getting data on the HTTP request
Take this example, You implement a web forum where you allow users to add and edit new posts. The client sends the post details to the server, which stores or changes the details in a database.

This requires giving the client the ability to send information to the server. In most cases, to send data through the HTTP request, you use one of the following ways:

1. Request Parameter - An HTTP request parameter represents a simple way to send values from client to server in a key-value(s) pair format. To send HTTP request parameters, you append them to the URI in a request query expression. They are also called query parameters. You should use this approach only for sending a small quantity of data.

2. Request Header - An HTTP request header is similar to the request parameters in that the request headers are sent through the HTTP header. The big difference is that they don’t appear in the URI, but you still cannot send large quantities of data using HTTP headers.

3. Path Variable - A path variable sends data through the request path itself. It is the same as for the request parameter approach: you use a path variable to send a small quantity of data. But we should use path variables when the value you send is mandatory.

4. HTTP Request Body - The HTTP request body is mainly used to send a larger quantity of data (formatted as a string, but sometimes even binary data such as a file). Mostly used when implementing REST endpoints.


### Using a Request Parameter
It is used to send optional data. An often encountered use case is when you want to define some search and filtering criteria. The lcient sends only some of the request parameters, and the server knows to use only the values it receives. Therefore, you implement the server to consider it might not get values for some of the parameters. To get the value from a request parameter, you need to add one more parameter to the controller’s action method and annotate that parameter with the @RequestParam annotation. The @RequestParam annotation tells Spring it needs to get the value from the HTTP request parameter with the same name as the method's parameter name. Below is an example controller that implements a request parameter.
```java
@Controller
public class MainController { 
  @RequestMapping("/home")
  public String home(
      @RequestParam(required = false) String color,
      Model page) {
    page.addAttribute("username", "Katy");
    page.addAttribute("color", color);
    return "home.html";
  }
}
```
In the above code, we define a new parameter for the controllers action method and annotate it with @RequestParam. We also add the Model parameter that we use to send data from the controller to the view. The controller passes the color sent by the client to the view. You can add more request params to the controllers action.


### Using a Path Variable
This is also a way of sending data from the client to the server. But instead of using the HTTP request parameters, you directly set variable values in the path. View the comparison between request param and path variable below.
- request parameter = "http://localhost:8080/home?color=blue"
- path variable = "http://localhost:8080/home/blue"

You dont identify the value with a key anymore. You just take that value from a precise position in the path. On the server side, you extract that value from the path from the specific position. You can have more than one path variable but avoid using more than 2. You can use request parameters for that. Also, path variables aren't used for optional values, they are rather used for mandatory parameters. see below
```java
@Controller
public class MainController {
  @RequestMapping("/home/{color}")
  public String home(
      @PathVariable String color,
      Model page) {
    page.addAttribute("username", "Katy");
    page.addAttribute("color", color);
    return "home.html";
  }
}
```
To define a path variable, you assign it a name and put it in the path between curly braces. You mark the parameter where you want to get the path variable with the @PathVariable annotation. The name of the parameter must be the same as the name of the variable in the path.
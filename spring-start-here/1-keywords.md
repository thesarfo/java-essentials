1. Service - An object that implements a use case in a real world application. They are used to add business functionalities.

2. Repository - An object that works directly with a database.

3. Proxy - An object that establishes communication with something outside the 

4. Servlet Container - What you need is not only something that understands HTTP, but something that can translate the HTTP request and response to a Java app. This something is a servlet container (sometimes referred to as a web server) - a translator of the HTTP messages for your Java app. A servlet container (e.g., Tomcat) speaks HTTP. It translates the HTTP request to our Spring app and the app’s response into an HTTP response. This way, we don’t need to care about the protocol used for communication on the network, as we simply write everything as Java objects and methods.

5. View - The json response of our web api or the template you render from the server side

6. Controller - A component of the web app that contains methods(actions) executed for a specific Http request. It is very dumb. All

7. Group - It is the reverse domain. i.e example.com will become com.example

8. Artifact - It refers to the name of the project you're building

9. Bean - An instance of a class that the Spring Application context is managing for you

10. @Component - tells Spring that we want it to be incharge of managing the class
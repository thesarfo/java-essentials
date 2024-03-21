1. Service - An object that implements a use case in a real world application.

2. Repository - An object that works directly with a database.

3. Proxy - An object that establishes communication with something outside the 

4. Servlet Container - What you need is not only something that understands HTTP, but something that can translate the HTTP request and response to a Java app. This something is a servlet container (sometimes referred to as a web server) - a translator of the HTTP messages for your Java app. A servlet container (e.g., Tomcat) speaks HTTP. It translates the HTTP request to our Spring app and the app’s response into an HTTP response. This way, we don’t need to care about the protocol used for communication on the network, as we simply write everything as Java objects and methods.
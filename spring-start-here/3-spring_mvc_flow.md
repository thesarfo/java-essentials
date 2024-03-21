## This is how the Spring MVC flow looks like

1. The client sends an HTTP request to the web server.

2. The dispatcher servlet uses the handler mapping to find out what controller action to call.

3. The dispatcher servlet calls the controller’s action.

4. After executing the action associated with the HTTP request, the controller returns the view name the dispatcher servlet needs to render into the HTTP response.

5. The response is sent back to the client.


Number 4 is where we need to make a change. We want the controller to not only return the view name but somehow also send data to the view. The view will incorporate this data to define the HTTP response.
Spring manaages a bean's life cycle differently depending on how you declare the bean in the Spring Context. In any Spring app, you can choose to declare a bean as one of the following.

1. Singleton - The default bean scope in Spring, for which the framework uniquely identifies each instnace with a name in the context

2. Prototype - The bean scope in spring, for which the framework only manages the type and creates a new instasnce of that class every time someone requests it(Directly from the context or through wiring or auto wiring)


In web apps, you can use other bean scopes that are only relevant to web applications - Web Scopes

1. Request scope - Spring creates an instance of the bean class for every HTTP request. The instance exists only for that specific HTTP request.

2. Session scope - Spring creates an instance and keeps the instance in the server’s memory for the full HTTP session. Spring links the instance in the context with the client’s session.

3. Application scope - The instance is unique in the app's context, and it's available while the app is running


### Understanding the web scopes.
To fully understand how these web scopes work in a Spring Application, we'll work on an example in which we implement a login functionality. Most web apps today provide the possibility to log in and access an account. You will use a request scoped bean to take the user's credentials for login and make sure the app uses them only for the login request. Then you'll use a session scoped bean to store all the relevant details you need, to keep the user logged in as long as they remain logged in. Then you'l use the applications scoped bean to add a capability to count logins.


##### Step 1 - Implement the login logic.
If the user provides a correct set of credentials, the app recognizes the user and confirms a successful login. We don't want Spring to keep the credentials in the app’s memory for more than it takes the login request to finish, so we’ll use a request-scoped bean to implement this functionality.

#### Step 2 - Keep the logged in user details
Once the user correctly authenticates with their credentials, we want to keep them logged in for a period of time. To store the user details and keep the user logged in for a longer period of time, we use the HTTP session through a session-scoped bean.

#### Step 3 - Count the login requests
Finally, we want our app to count all the login requests from all the users. We need to store the total number of requests received by the app. To achieve this functionality, we will use an Application Scoped bean.
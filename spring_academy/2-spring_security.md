## Spring Security

Spring Security is a powerful framework for implementing robust security features in Spring-based applications. It offers tools and mechanisms to address various security challenges, helping developers protect their applications and users from common web exploits.

1. **Authentication**: Authentication is the process of proving a user's identity to the system. This typically involves providing credentials, such as a username and password, to gain access. Spring Security handles authentication by creating authentication sessions using session tokens stored in cookies, providing efficient and secure authentication mechanisms.

2. **Authorization**: Authorization occurs after authentication and determines the permissions granted to authenticated users. Spring Security implements role-based access control (RBAC), where users are assigned roles defining their permissions. This allows for granular control over which users can perform specific actions.

3. **Security Policies**:
   - **Same Origin Policy (SOP)**: Ensures that scripts in a web page can only make requests to the same origin (URI) as the web page itself, preventing unauthorized access to sensitive resources.
   - **Cross-Origin Resource Sharing (CORS)**: Allows servers to explicitly define which origins are permitted to access resources, relaxing SOP restrictions for legitimate cross-origin requests.
4. **Common Web Exploits**:
   - **Cross-Site Request Forgery (CSRF)**: Exploits the user's authenticated session to perform unauthorized actions on a website. Spring Security provides built-in support for CSRF protection using CSRF tokens.
   - **Cross-Site Scripting (XSS)**: Injects malicious scripts into web pages, allowing attackers to execute arbitrary code on the client or server. Proper data validation and escaping are crucial for mitigating XSS vulnerabilities.

# IMPLEMENTING SPRING SECURITY
Obviously you need to add the spring security dependency to your pom.xml or build.gradle

**1: Understand your Security Requirements**
Who should be allowed to manage any given item(Cash Card)? In our simple domain, let's state that the user who created the item(Cash Card) "owns" the Cash Card. Thus, they are the "card owner". Only the item owner can view or update an item (Cash Card).

**The logic will be something like this:**

IF the user is authenticated

... AND they are authorized as a "item owner"(cashcard owner)

... ... AND they own the requested item (Cash Card)

THEN complete the users's request

BUT don't allow users to access items (Cash Cards) they do not own.
1. **ORM**: is the automated persistence of objects in a java app to the tables in an RDBMS, using metadata that describes the mapping between the classes of the application and the schema of the SQL database.

2. **JPA**: is a specification defining an API that manages the persistence of objects and object/relational mappings. 

**Hibernate** is the most popular implementation of the JPA specification. So JPA specifies what must be done to persist objects, while Hibernate will determine how to do it. 

**Spring Data Commons** provides the core Spring framework concepts that support all Spring Data modules. 

**Spring Data JPA** is an additional layer on top of JPA implementations such as Hibernate. Not only can Spring Data JPA use all the capabilities of JPA, but it adds its own capabilities such as generating database queries from method names. 
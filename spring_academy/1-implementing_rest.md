### Notice: this note waa made while following spring.academy's cashcard project, so wherever you see cashcard, you can replace it with "item".

```java
@RestController
class CashCardController {
  @GetMapping("/cashcards/{requestedId}")
  private ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
     CashCard cashCard = /* Here would be the code to retrieve the CashCard */;
     return ResponseEntity.ok(cashCard);
  }
}

```

Here, we annotate the class with @RestController to tell spring that this class handles rest implementation. Then we annotate the first method with @GetMapping to perform GET requests, to the specified endpoint. The endpoint takes the "requestedId" as a path parameter. So we use the @PathVariable annotation to make sure that spring knows that we'll add a path param. Note how we use the ResponseEntity class to return a response body of "Cashcard". And then re return a response code of 200, using the "ResponseEntity.ok" method.

#### Response Entity

// I have to write sth about the response entity class here

## IMPLEMENTING A GET REQUEST(by ID)

For instance, getting a specific resource by it's id.

```java
@GetMapping("/{requestedId}")
    private ResponseEntity<CashCard> findById(@PathVariable Long requestedId){
        Optional<CashCard> cashCardOptional = cashCardRepository.findById(requestedId);
        if (cashCardOptional.isPresent()){
            return ResponseEntity.ok(cashCardOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
```

First we try to find the resource using the CrudRepository's findById method. The findById method returns an Optional - which means that the db might or might not contain the cashcard that we are looking for. We store this in a variable called cashCardOptional, which is of type CashCard(the resource we are trying to get is a CashCard).

And then, we check if the cashCard is present, if so then it means our repository successfully found the cashCard, and we can retrieve it with cashCardOptional.get()..ie the .get() method. If it doesn't exist(isNotPresent), we return a not found response.

## IMPLEMENTING A GET REQUEST(ALL ITEMS WITH PAGINATION AND SORTING)

1. **Fetching Multiple Items**: When requesting multiple items(Cash Cards) from an API, it's ideal to make a single request returning a list of all items (Cash Card objects) in JSON array format.

2. **Pagination**: Pagination is essential to prevent overwhelming API responses with a large number of items. It involves specifying a page length and page index to retrieve subsets of data.

3. **Sorting**: To ensure consistent pagination results, sorting is necessary. Sorting by a specific field helps in organizing the data effectively, reducing cognitive overhead and minimizing potential errors.

4. **Spring Data Pagination API**: Spring Data provides tools like PageRequest and Sort classes for implementing pagination and sorting functionalities conveniently.

5. **Request URI Composition**:

   - To get the second page: `/cashcards?page=1` (page index starts at 0)
   - With a page size of 3: `/cashcards?page=1&size=3`
   - Sorted by amount in descending order: `/cashcards?page=1&size=3&sort=amount,desc`

6. **Java Implementation**: The controller method for fetching a page of items(Cash Cards) utilizes Pageable to parse page number, size, and sort parameters. It then retrieves the items (Cash Cards) accordingly and returns them in the response.

7. **Page Object**: The Page object contains metadata about the current page, such as total elements, total pages, current page number, etc. However, for client response, it's often sufficient to return only the content (Cash Cards) without the extra metadata.

Below is how an example get request for all items will look like...while adding pagination and sorting.

```java
@GetMapping
    private ResponseEntity<List<CashCard>> findAll(Pageable pageable){
        Page<CashCard> page = cashCardRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.DESC, "amount"))));
        return ResponseEntity.ok(page.getContent());
    }
```

Sure, let's focus on the most crucial parts of the code:

- `private ResponseEntity<List<CashCard>> findAll(Pageable pageable)`:

  - `private`: Specifies that the method is accessible only within the class.
  - `ResponseEntity<List<CashCard>>`: Defines the return type, indicating that the response will contain a list of `CashCard` objects.
  - `findAll(Pageable pageable)`: Method name and parameter list. It takes a `Pageable` object, which is provided by Spring Data for pagination purposes.

- `cashCardRepository.findAll(...)`: Invokes the `findAll` method of the `cashCardRepository`, which retrieves a page of `CashCard` objects from the database based on the provided pagination and sorting parameters.

- `ResponseEntity.ok(page.getContent())`: Constructs an HTTP response entity with a status code of 200 (OK) and sets the response body to the content of the fetched page of `CashCard` objects. `page.getContent()` retrieves the actual content (i.e., the list of `CashCard` objects) from the `Page` object.

## IMPLEMENTING A GET REQUEST (FOR ALL ITEMS)
```java
@GetMapping()
private ResponseEntity<Iterable<CashCard>> findAll() {
   return ResponseEntity.ok(cashCardRepository.findAll());
}
```
Once again we're using one of Spring Data's built-in implementations: CrudRepository.findAll(). Our implementing Repository, CashCardRepository, will automatically return all CashCard records from the database when findAll() is invoked.


## IMPLEMENTING A POST REQUEST

To implement a post endpoint, you

1. Use the POST method
2. Define the endpoint URI
3. Accept a JSon request body(let the server create the id for you)
4. Respond with a 201 created status code
5. Include a location header with the URI of the newly created resource

```java
@PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb){
        CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);
        URI locationOfNewCashCard = ucb
                .path("cashcards/{id}")
                .buildAndExpand(savedCashCard.id())
                .toUri();
        return ResponseEntity.created(URI.create(locationOfNewCashCard)).build();
    }
```

The crudRepository.save() method saves a new item(cashCard) for us. and it returns the saved object with a unique id, provided by the database.

Unlike the GET request, the POST request expects a request "body". This is the data we submit to the API, and then spring web will deserialize the data into the item(cashcard) we want to create for us.

The "URI locationOfNewCashCard" code constructs a nw URI to the newly created item(cashCard). This is the URI that the caller can then use to GET the newly created item(CashCard).

Note that savedCashCard.id is used as the identifier, which matches the GET endpoint's specification of cashcards/<CashCard.id>.

Where did UriComponentsBuilder come from? We were able to add UriComponentsBuilder ucb as a method argument to this POST handler method and it was automatically passed in. How so? It was injected from our now-familiar friend, Spring's IoC Container.

return ResponseEntity.created(locationOfNewCashCard).build(); Finally, we return 201 CREATED with the correct Location header.

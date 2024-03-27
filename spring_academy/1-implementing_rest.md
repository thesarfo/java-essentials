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

## IMPLEMENTING A GET REQUEST
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
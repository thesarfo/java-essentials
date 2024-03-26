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

And then, we check if the cashCard is present, if so then it means our repository successfully found the cashCard, and we can retrieve it with cashCardOptional.get(). If it doesn't exist(isNotPresent), we return a not found response.
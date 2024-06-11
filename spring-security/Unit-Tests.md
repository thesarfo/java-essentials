Assuming that we have a service

```java
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<String> getProductNamesWithEvenNuoOfChar(){
        List<String> names = productRepository.getPRoductNames();
        List<String> result = new ArrayList<>();
        for (String n : names){
            if (n.length() % 2 != 0){
                result.add(n);
            }
        }
        return result;
    }
}
```

And we also have a repository
```java
@Repository
public class ProductRepository {

    public List<String> getPRoductNames(){
        return Arrays.asList("aaa", "bb", "cacc", "aca");
    }
}
```

We can test it by first calling the method we want to test, and using the `assert` keyword to check the results. See below
```java
@SpringBootTest
class TestingApplicationTests {


	@Autowired
	private ProductService productService;


	@Test
	void noProductsreturnedTest() {
		List<String> res = productService.getProductNamesWithEvenNuoOfChar();

		assertTrue(res.isEmpty()); // assert is used for checking results of tests
	}
```

The above test fails because the ProductService.getProductNamesWithEvenNuoOfChar() method returns a list of products with odd number of characters. But if we look into our product service, we realize that the method is actually calling another method from the product repository which returns a list of products with even number of characters. 

So we also need to test the Product repository's method call, or in other words, assume that testing the service method, will assume that we test the repository's method call. This is a typical scenario where we need to mock the product repository to return a list of products with odd number of characters. We can achieve this by using the `@MockBean` annotation from the Mockito library.


Mocking is a process of creating a fake object representing your dependency that you can manipulate and control. In this case, we will create a fake product repository that returns a list of products with odd number of characters.

```java
	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;


	@Test
	void noProductsreturnedTest() {
		given(productRepository.getProductNames())
				.willReturn(List.of()).willReturn(Collections.emptyList()); // assumption

		List<String> res = productService.getProductNamesWithEvenNoOfChar(); // method call

		assertTrue(res.isEmpty()); // assert is used for checking results of tests -- results
	}
```

After creating a mock out of the dependency, we will tell this mock that when it is called, it should return no objects in the list. This is because we want to test the ProductService.getProductNamesWithEvenNuoOfChar() method which returns a list of products with even number of characters. If the mock returns no objects, then the ProductService.getProductNamesWithEvenNuoOfChar() method will return an empty list. This is what we want to test.

We can do that by for instance using the `given` keyword. Given that something will happen to my mock dependency, when i call the method, i get a result and something should happen. So the test contains assumptions, call of the methods, and then expections.

<br>

We can also test to see if our method actually returns something from the db. Lets do that below
```java
	@Test
	public void moreProductsAreFoundTest() {
		List<String> input = Arrays.asList("aa", "bbbb", "ccc"); // input list

		given(productRepository.getProductNames()).willReturn(input); // given that the productRepository.getProductNames() is called, it should return the input list

		List<String> res = productService.getProductNamesWithEvenNoOfChar(); // when the productService.getProductNamesWithEvenNoOfChar() is called, it should return the result

		assertEquals(2, res.size()); // assert that the result size is 2
		//verify(productRepository, times(2)).addProduct(any());
	}
```

<br>

When you're writing a unit test for a class (like a service) that depends on other classes (like repositories), you typically create mock versions of the dependent classes and inject them into the class you're testing. This allows you to isolate the class under test and control the behavior of its dependencies.

In your case, you would create a mock ProductRepository and inject it into ProductService for testing. This allows you to specify the behavior of ProductRepository in your test (like what it should return when getProductNames() is called), so you can test ProductService under different scenarios.

```java
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void getProductNamesWithEvenNuoOfCharTest() {
        when(productRepository.getPRoductNames()).thenReturn(Arrays.asList("aa", "bbb", "cccc", "ddddd"));

        List<String> res = productService.getProductNamesWithEvenNuoOfChar();

        assertEquals(2, res.size());
        assertTrue(res.containsAll(Arrays.asList("aa", "cccc")));
    }
}
```

So, if you are testing a controller, you will use the `@Mock` annotations on let's say the services and other classes that are injected in the controller, and then use the `@InjectMocks` annotation on the Controller class itself. 
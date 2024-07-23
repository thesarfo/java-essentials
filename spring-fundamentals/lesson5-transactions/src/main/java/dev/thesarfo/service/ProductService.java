package dev.thesarfo.service;

import dev.thesarfo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    /* the transactional annotation ensures that the db insertion will not take place if an exception thrown */
    @Transactional
    public void addOneProduct(){
        productRepository.addProduct("Beer");
        throw new RuntimeException(":(");
    }

}

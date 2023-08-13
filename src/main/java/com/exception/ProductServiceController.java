package com.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class ProductServiceController {
   private static Map<String, Product> productRepo = new HashMap<>();
   static {
      Product tshirt = new Product();
      tshirt.setId("1");
      tshirt.setName("T-shirt");
      productRepo.put(tshirt.getId(), tshirt);
      
      Product jeans = new Product();
      jeans.setId("2");
      jeans.setName(" Denim Jeans");
      productRepo.put(jeans.getId(), jeans);
   }
   //@PutMapping("/products/{id}") 
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
      if(!productRepo.containsKey(id))throw new ProductNotfoundException();
      productRepo.remove(id);
      product.setId(id);
      productRepo.put(id, product);
      return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
   }
}
package com.onion.axon.axonshop.queryend;

import com.onion.axon.axonshop.persistence.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query/product")
public class QueryProductController {


    @Autowired
    private ProductEntityRepository repository;

    @RequestMapping(value="all",method = RequestMethod.GET)
    public Object getAll(){
        return repository.findAll();
    }

}

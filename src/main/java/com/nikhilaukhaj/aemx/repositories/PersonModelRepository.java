package com.nikhilaukhaj.aemx.repositories;


import com.nikhilaukhaj.apicontract.model.PersonDetailsFull;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonModelRepository implements PanacheMongoRepository<PersonDetailsFull> {

}

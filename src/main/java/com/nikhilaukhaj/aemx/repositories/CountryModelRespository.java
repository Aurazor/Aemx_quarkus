package com.nikhilaukhaj.aemx.repositories;

import com.nikhilaukhaj.aemx.models.CustomCountryModel;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CountryModelRespository implements PanacheMongoRepository<CustomCountryModel> {

}

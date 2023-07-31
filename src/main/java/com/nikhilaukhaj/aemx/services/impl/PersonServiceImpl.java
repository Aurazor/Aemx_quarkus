package com.nikhilaukhaj.aemx.services.impl;


import com.nikhilaukhaj.aemx.services.PersonService;
import com.nikhilaukhaj.apicontract.model.PersonDetails;
import com.nikhilaukhaj.apicontract.model.PersonDetailsFull;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonServiceImpl implements PersonService {
    @Override
    public PersonDetailsFull getPersonDetailsFullFromPersonDetails(PersonDetails personDetails) {
//        ObjectId
        PersonDetailsFull personDetailsFull = new PersonDetailsFull();
        personDetailsFull.setFirstName(personDetails.getFirstName());
        personDetailsFull.setLastName(personDetails.getLastName());
        return personDetailsFull;
    }
}

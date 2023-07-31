package com.nikhilaukhaj.aemx.api;

import com.nikhilaukhaj.aemx.repositories.PersonModelRepository;
import com.nikhilaukhaj.aemx.services.PersonService;
import com.nikhilaukhaj.apicontract.api.PersonApi;
import com.nikhilaukhaj.apicontract.model.PersonDetails;
import com.nikhilaukhaj.apicontract.model.PersonDetailsFull;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

public class RestPerson implements PersonApi {
    @Inject
    PersonModelRepository personRepositiory;
    @Inject
    PersonService personService;

    @Override
    public Response createPerson(PersonDetails personDetails) {
        PersonDetailsFull personDetailsFull = personService.getPersonDetailsFullFromPersonDetails(personDetails);
        personRepositiory.persist(personDetailsFull);
        return Response.ok().entity(personDetailsFull).build();
    }

    @Override
    public Response getAllPersons() {
        List<PersonDetailsFull> personDetailsFullList =  personRepositiory.findAll().list();
        return Response.ok().entity(personDetailsFullList).build();
    }

    @Override
    public Response getPersonById(String personId) {
        PersonDetailsFull personDetailsFull = personRepositiory.findById(new ObjectId(personId));
        if(Objects.isNull(personDetailsFull)) {
            return Response.status(404).build();
        }else {
            return Response.ok().entity(personDetailsFull).build();
        }
    }
}

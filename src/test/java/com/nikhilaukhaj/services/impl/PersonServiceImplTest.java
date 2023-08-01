package com.nikhilaukhaj.services.impl;

import com.nikhilaukhaj.aemx.services.impl.PersonServiceImpl;
import com.nikhilaukhaj.apicontract.model.PersonDetails;
import com.nikhilaukhaj.apicontract.model.PersonDetailsFull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {
    @InjectMocks
    PersonServiceImpl personService;

    @Test
    public void getPersonDetailsFullFromPersonDetailsTest(){
        PersonDetails personDetails = new PersonDetails("publicis", "groupe");
        PersonDetailsFull personDetailsFull = personService.getPersonDetailsFullFromPersonDetails(personDetails);
        assertAll(
                () -> assertEquals("publicis",personDetailsFull.getFirstName()),
                () -> assertEquals("groupe",personDetailsFull.getLastName())
        );
    }
}

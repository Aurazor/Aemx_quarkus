package com.nikhilaukhaj.aemx.services;


import com.nikhilaukhaj.apicontract.model.PersonDetails;
import com.nikhilaukhaj.apicontract.model.PersonDetailsFull;

public interface PersonService {
   PersonDetailsFull getPersonDetailsFullFromPersonDetails(PersonDetails personDetails);
}

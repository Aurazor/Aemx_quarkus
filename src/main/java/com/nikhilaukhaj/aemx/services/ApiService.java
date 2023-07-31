package com.nikhilaukhaj.aemx.services;

import com.nikhilaukhaj.aemx.models.CustomCountryModel;
import javax.ws.rs.core.Response;

public interface ApiService {
   Response getResponse();
   Response getCountries();
   Response addCountry(CustomCountryModel customCountryModel);
   Response updateCountry(CustomCountryModel customCountryModel);
   Response deleteCountry(String countryName);
}

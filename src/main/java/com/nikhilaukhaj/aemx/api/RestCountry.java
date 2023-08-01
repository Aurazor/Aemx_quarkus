package com.nikhilaukhaj.aemx.api;

import com.nikhilaukhaj.aemx.models.CustomCountryModel;
import com.nikhilaukhaj.aemx.services.ApiService;
import com.nikhilaukhaj.aemx.services.CsvReaderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/country")
public class RestCountry {

    @Inject
    ApiService apiService;
    @Inject
    CsvReaderService csvReaderService;

    @GET
    public Response getAllCountries() {
        return apiService.getCountries();
    }


    @POST
    public Response addCountry(CustomCountryModel customCountryModel){
        return apiService.addCountry(customCountryModel);
    }

    @PUT
    public Response updateCountry(CustomCountryModel countryModel){
        return apiService.updateCountry(countryModel);
    }

    @DELETE
    public Response deleteCountry(String countryName){
        return apiService.deleteCountry(countryName);
    }
}

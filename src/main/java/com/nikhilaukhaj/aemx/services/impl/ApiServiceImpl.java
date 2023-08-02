package com.nikhilaukhaj.aemx.services.impl;

import com.nikhilaukhaj.aemx.models.CustomCountryModel;
import com.nikhilaukhaj.aemx.repositories.CountryModelRespository;
import com.nikhilaukhaj.aemx.services.ApiService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class ApiServiceImpl implements ApiService {

    @Inject
    CountryModelRespository countryModelRespository;

    @Override
    public Response getCountries() {
        return getResponse();
    }

    @Override
    public Response addCountry(CustomCountryModel customCountryModel) {
         countryModelRespository.persist(customCountryModel);
         return getResponse();
    }

    @Override
    public Response updateCountry(CustomCountryModel customCountryModel) {
        CustomCountryModel mongoCustomCountryModel = countryModelRespository.find("name",customCountryModel
                .getName()).firstResult();
        customCountryModel.id = mongoCustomCountryModel.id;
        countryModelRespository.update(customCountryModel);
        return getResponse();
    }

    @Override
    public Response deleteCountry(String countryName) {
        CustomCountryModel mongoCustomCountryModel = countryModelRespository.find("name",countryName).firstResult();
        countryModelRespository.delete(mongoCustomCountryModel);
        return getResponse();
    }

    @Override
    public Response getResponse() {
        List<CustomCountryModel> countries = getAllCountries();
        return Response.ok().entity(countries).build();
    }

    @Override
    public Response getErrorResponse(String message) {
        return Response.status(500).entity(message).build();
    }

    @Override
    public Response getOkResponse(Object entity) {
        return Response.ok().entity(entity).build();
    }

    public List<CustomCountryModel> getAllCountries(){
        return countryModelRespository.listAll();
    }
}

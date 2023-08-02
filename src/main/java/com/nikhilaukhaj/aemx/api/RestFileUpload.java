package com.nikhilaukhaj.aemx.api;

import com.nikhilaukhaj.aemx.exceptions.InvalidCsvException;
import com.nikhilaukhaj.aemx.models.CustomCountryModel;
import com.nikhilaukhaj.aemx.services.ApiService;
import com.nikhilaukhaj.aemx.services.CsvReaderService;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/upload")
public class RestFileUpload {
    @Inject
    CsvReaderService csvReaderService;
    @Inject
    ApiService apiService;

    @POST
    @Path("files")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleFileUpload( @RestForm("image") FileUpload file)  {
        List<CustomCountryModel> countries;
        try {
            countries = csvReaderService.getCountriesFromCsv(file.uploadedFile().toFile());
        }catch(InvalidCsvException | IOException e) {
            return apiService.getErrorResponse(e.getMessage());
        }
        return apiService.getOkResponse(countries);
    }
}

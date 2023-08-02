package com.nikhilaukhaj.aemx.services.impl;

import com.nikhilaukhaj.aemx.exceptions.InvalidCsvException;
import com.nikhilaukhaj.aemx.models.CustomCountryModel;
import com.nikhilaukhaj.aemx.repositories.CountryModelRespository;
import com.nikhilaukhaj.aemx.services.CsvReaderService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class CsvReaderServiceImpl implements CsvReaderService {

    private final String COMMA_DELIMETER = ",";
    @Inject
    CountryModelRespository countryRepository;

    @Override
    public List<CustomCountryModel> getCountriesFromCsv(File csvFile) throws IOException,InvalidCsvException {
        List<CustomCountryModel> countries = new ArrayList<>();
        int lineNumber = 0;

        try{
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            while((line = br.readLine()) != null) {
                lineNumber++;

                String[] values = line.split(COMMA_DELIMETER);
                if(lineNumber == 1){
                    if(!validHeaders(values)){
                        throw new InvalidCsvException("Invalid headers");
                    }
                    continue;
                }
                CustomCountryModel customCountryModel = getCountryFromArray(values);
                countries.add(customCountryModel);
            }
        }catch (InvalidCsvException e){
            String errorMessage = StringUtils.isBlank(e.getMessage()) ? "Invalid line at " + lineNumber : e.getMessage();
            throw new InvalidCsvException(errorMessage);
        }catch (IOException e) {
            throw new IOException();
        }

        addCountriesToMongoDb(countries);
        return countries;
    }

    public boolean validHeaders(String[] values) {
        return values[0].equalsIgnoreCase("name") && values[1].equalsIgnoreCase("code") && values[2].equalsIgnoreCase("image");
    }

    public void addCountriesToMongoDb(List<CustomCountryModel> countries) {
        for(CustomCountryModel country : countries) {
            countryRepository.persist(country);
        }
    }

    public CustomCountryModel getCountryFromArray(String[] values) throws InvalidCsvException{
        //check validity
        for(String value: values){
            if(StringUtils.isBlank(value)) {
                throw new InvalidCsvException();
            }
        }

        String countryName = values[0];
        String countryCode = values[1];
        String countryImg = values[2];
        CustomCountryModel customCountryModel = new CustomCountryModel(countryName,countryCode,countryImg);
        return customCountryModel;
    }
}

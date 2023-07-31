package com.nikhilaukhaj.aemx.services.impl;

import com.nikhilaukhaj.aemx.models.CustomCountryModel;
import com.nikhilaukhaj.aemx.services.CsvReaderService;

import javax.enterprise.context.ApplicationScoped;
import javax.resource.spi.ConfigProperty;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CsvReaderServiceImpl implements CsvReaderService {
    @ConfigProperty(defaultValue = "countries.location")
    String countriesFileLocation;
    private final String COMMA_DELIMETER = ",";

    @Override
    public List<CustomCountryModel> getCountriesFromCsv() throws IOException {
        List<CustomCountryModel> countries = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(countriesFileLocation))){
            String line;
            while((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMETER);
                CustomCountryModel customCountryModel = getCountryFromArray(values);
                countries.add(customCountryModel);
            }
        }
        return countries;
    }

    public CustomCountryModel getCountryFromArray(String[] values) {
        String countryName = values[0];
        String countryCode = values[1];
        String countryImg = values[2];
        CustomCountryModel customCountryModel = new CustomCountryModel(countryName,countryCode,countryImg);
        return customCountryModel;
    }
}

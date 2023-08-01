package com.nikhilaukhaj.aemx.services.impl;

import com.nikhilaukhaj.aemx.models.CustomCountryModel;
import com.nikhilaukhaj.aemx.services.CsvReaderService;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CsvReaderServiceImpl implements CsvReaderService {
    @ConfigProperty(name = "countries.csv.location", defaultValue = "/test.csv")
    String countriesCsvFileLocation;
    private final String COMMA_DELIMETER = ",";

    @Override
    public List<CustomCountryModel> getCountriesFromCsv() throws IOException {
        List<CustomCountryModel> countries = new ArrayList<>();
        InputStream inputStream = CsvReaderServiceImpl.class.getResourceAsStream(countriesCsvFileLocation);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
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

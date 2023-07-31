package com.nikhilaukhaj.aemx.services;

import com.nikhilaukhaj.aemx.models.CustomCountryModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CsvReaderService {
    List<CustomCountryModel> getCountriesFromCsv() throws IOException;
}

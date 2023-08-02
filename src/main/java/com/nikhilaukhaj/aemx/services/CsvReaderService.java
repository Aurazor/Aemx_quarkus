package com.nikhilaukhaj.aemx.services;

import com.nikhilaukhaj.aemx.exceptions.InvalidCsvException;
import com.nikhilaukhaj.aemx.models.CustomCountryModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CsvReaderService {
    List<CustomCountryModel> getCountriesFromCsv(File csvFile) throws IOException, InvalidCsvException;
}

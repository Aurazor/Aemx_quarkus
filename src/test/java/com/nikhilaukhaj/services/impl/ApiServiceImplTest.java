package com.nikhilaukhaj.services.impl;

import com.nikhilaukhaj.aemx.models.CustomCountryModel;
import com.nikhilaukhaj.aemx.repositories.CountryModelRespository;
import com.nikhilaukhaj.aemx.services.impl.ApiServiceImpl;
import javax.ws.rs.core.Response;

import io.quarkus.mongodb.panache.PanacheQuery;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.wildfly.common.Assert.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ApiServiceImplTest {

    @InjectMocks
    ApiServiceImpl sut;

    @Mock
    CountryModelRespository respository;

    @Test
    @DisplayName("Test if get country returns correct response")
    public void testGetCountry_success(){
        lenient().when(respository.listAll()).thenReturn(getCountriesList());
        Response response = sut.getCountries();

        List<CustomCountryModel> customCountryModelList = (List<CustomCountryModel>) response.getEntity();;

        assertAll(
                () -> assertNotNull(customCountryModelList),
                () -> assertEquals("France", customCountryModelList.get(0).getName()),
                () -> assertEquals("33", customCountryModelList.get(0).getCode()),
                () -> assertEquals("france.png", customCountryModelList.get(0).getImage()),
                () -> assertEquals("England", customCountryModelList.get(1).getName())
        );
    }

    @Test
    @DisplayName("Test if add country returns correct response")
    public void testAddCountry_success(){
       lenient().when(respository.listAll()).thenReturn(getCountriesList());
       Response response = sut.addCountry(getCustomCountryModel());
       verify(respository).persist(any(CustomCountryModel.class));

       List<CustomCountryModel> customCountryModelList = (List<CustomCountryModel>) response.getEntity();
       CustomCountryModel customCountryModel =  customCountryModelList.stream().filter(country -> {
           return country.getName().equalsIgnoreCase("france");
       }).findAny().orElse(null);

        assertAll(
                () -> assertNotNull(customCountryModel),
                () -> assertEquals("France", customCountryModel.getName()),
                () -> assertEquals("33", customCountryModel.getCode()),
                () -> assertEquals("france.png", customCountryModel.getImage())
        );
    }

    @Test
    @DisplayName("Test if update country returns correct response")
    public void testUpdateCountry_Success(){
        lenient().when(respository.listAll()).thenReturn(getCountriesList());
        PanacheQuery<CustomCountryModel> mockPanacheQuery = mock(PanacheQuery.class);
        when(respository.find(anyString(),  any(Object.class))).thenReturn(mockPanacheQuery);
        when(mockPanacheQuery.firstResult()).thenReturn(getCustomCountryModelWithId());
        Response response = sut.updateCountry(getCustomCountryModel());
        verify(respository).update(any(CustomCountryModel.class));
        List<CustomCountryModel> customCountryModelList = (List<CustomCountryModel>) response.getEntity();
        CustomCountryModel customCountryModel =  customCountryModelList.stream().filter(country -> {
            return country.getName().equalsIgnoreCase("france");
        }).findAny().orElse(null);
        assertAll(
                () -> assertNotNull(customCountryModel),
                () -> assertEquals("France", customCountryModel.getName()),
                () -> assertEquals("33", customCountryModel.getCode()),
                () -> assertEquals("france.png", customCountryModel.getImage())
        );
    }

    @Test
    @DisplayName("Test if remove country returns correct response")
    public void testRemoveCountry_Success(){
        PanacheQuery<CustomCountryModel> mockPanacheQuery = mock(PanacheQuery.class);
        when(respository.find(anyString(),  any(Object.class))).thenReturn(mockPanacheQuery);
        when(mockPanacheQuery.firstResult()).thenReturn(getCustomCountryModelWithId());

        Response response = sut.deleteCountry("france");
        verify(respository).delete(any(CustomCountryModel.class));
        lenient().when(respository.listAll()).thenReturn(getIncompleteCountriesList());

        List<CustomCountryModel> customCountryModelList = (List<CustomCountryModel>) response.getEntity();
        CustomCountryModel customCountryModel =  customCountryModelList.stream().filter(country -> {
            return country.getName().equalsIgnoreCase("france");
        }).findAny().orElse(null);
        assertNull(customCountryModel);
    }

    public CustomCountryModel getCustomCountryModelWithId(){
        CustomCountryModel customCountryModel = new CustomCountryModel("France", "33", "france.png");
        ObjectId objectId = new ObjectId("64c77bc9228945750ada99dd");
        customCountryModel.setId(objectId);
        return customCountryModel;
    }
    public CustomCountryModel getCustomCountryModel() {
        CustomCountryModel customCountryModel = new CustomCountryModel("France", "33", "france.png");
        return customCountryModel;
    }

    public List<CustomCountryModel> getCountriesList() {
        CustomCountryModel customCountryModel1 = new CustomCountryModel("France", "33", "france.png");
        CustomCountryModel customCountryModel2 = new CustomCountryModel("England", "34", "england.png");
        CustomCountryModel customCountryModel3 = new CustomCountryModel("Mauritius", "35", "mauritius.png");
        CustomCountryModel customCountryModel4 = new CustomCountryModel("United States", "36", "us.png");
        return List.of(customCountryModel1, customCountryModel2,customCountryModel3,customCountryModel4);
    }

    public List<CustomCountryModel> getIncompleteCountriesList() {
        CustomCountryModel customCountryModel2 = new CustomCountryModel("England", "34", "england.png");
        CustomCountryModel customCountryModel3 = new CustomCountryModel("Mauritius", "35", "mauritius.png");
        CustomCountryModel customCountryModel4 = new CustomCountryModel("United States", "36", "us.png");
        return List.of( customCountryModel2,customCountryModel3,customCountryModel4);
    }




}

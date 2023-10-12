package com.example.demo.webApi;

import com.example.demo.entities.City;
import com.example.demo.services.CityService;
import com.example.demo.services.request.AddCityRequest;
import com.example.demo.services.request.UpdateCityRequest;
import com.example.demo.services.response.CityResponse;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.util.Assert;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CityControllerTest {
    CityController cityController;
    CityService cityService;

    @BeforeEach
    void setUp(){
        cityService = Mockito.mock(CityService.class);
        cityController = new CityController(cityService);
    }

    @Test
    @Order(1)
    void itShouldGetAllCities(){
        CityResponse response = CityResponse.builder()
                .id(1)
                .name("test")
                .zipCode("1")
                .build();

        CityResponse response2 = CityResponse.builder()
                .id(2)
                .name("tes2")
                .zipCode("2")
                .build();
        List<CityResponse> responses = List.of(response, response2);

        when(cityService.getAllCities()).thenReturn(responses);

        List<CityResponse> actual = cityController.getAllCities();
        Mockito.verify(cityService).getAllCities();
        System.out.println(responses);
        System.out.println(actual);

        assertEquals(responses, actual);

    }
    @Test
    @Order(2)
    void itShouldGetCityById(){

        long id = 2;
        CityResponse expectedResponse = CityResponse.builder()
                .id(2)
                .name("test")
                .zipCode("1")
                .build();

        when(cityService.getCityById(id)).thenReturn(expectedResponse);

        CityResponse actual = cityController.getById(id); //bozmak istersek buarada id ile bozariz

        Mockito.verify(cityService).getCityById(id);
        System.out.print(actual);
        assertEquals(expectedResponse, actual);

    }
    @Test
    @Order(3)
    void itShouldCreateCity(){
        AddCityRequest request = AddCityRequest.builder()
                        .name("test")
                                .zipCode("61").build();


        CityResponse response = CityResponse.builder()
                .id(1)
                .name("test")
                .zipCode("61")
                .build();

        when(cityService.addCity(request)).thenReturn(response);

        CityResponse actual = cityController.add(request);
        verify(cityService).addCity(request);
        System.out.println(actual);
        assertEquals(response, actual);

    }

    @Test
    @Order(4)
    void itShouldUpdateCity(){
        long  id = 1;
        UpdateCityRequest request = UpdateCityRequest.builder()
                .id(id)
                .name("test")
                .zipCode("61").build();


        CityResponse response = CityResponse.builder()
                .id(id)
                .name("test")
                .zipCode("61")
                .build();

        when(cityService.updateCity(request, id)).thenReturn(response);

        CityResponse actual = cityController.update(request, id);
        verify(cityService).updateCity(request, id);
        System.out.println(actual);
        assertEquals(response.getId(), actual.getId());

    }



}
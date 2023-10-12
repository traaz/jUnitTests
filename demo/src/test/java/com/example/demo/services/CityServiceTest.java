package com.example.demo.services;

import com.example.demo.dataAccess.CityRepository;
import com.example.demo.entities.City;
import com.example.demo.services.request.AddCityRequest;
import com.example.demo.services.request.UpdateCityRequest;
import com.example.demo.services.response.CityResponse;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CityServiceTest {

    CityService cityService;
    CityRepository repository;

    @BeforeEach
    void setUp(){
        repository = Mockito.mock(CityRepository.class);
        cityService = new CityService(repository);
    }
    @Test
    @Order(1)
    void whenGetAllCities(){
        City city = City.builder()
                .id(1)
                .name("test")
                .zipCode("1")
                .build();

        City city2 = City.builder()
                .id(2)
                .name("tes2")
                .zipCode("2")
                .build();

        List<City> cityResponses = List.of(city, city2);

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

        when(repository.findAll()).thenReturn(cityResponses);

        List<CityResponse> actual = cityService.getAllCities();

        assertEquals(responses, actual);
        System.out.println(actual);

    }

    @Test
    @Order(2)
    void whenCityId_responseCity(){
        long id = 1;
        City city = City.builder()
                .id(id)
                .name("test")
                .zipCode("5")
                .build();

        CityResponse response = CityResponse.builder()
                .id(id)
                .name("test")
                .zipCode("5")
                .build();

        when(repository.findById(id)).thenReturn(Optional.of(city));

        CityResponse actual = cityService.getCityById(id);

        assertEquals(response, actual);
        System.out.println(actual);


    }

    @Test
    @Order(3)
    void whenCreateCity_responseCityResponse(){
        AddCityRequest request = AddCityRequest.builder()
                .name("test")
                .zipCode("5")
                .build();

        CityResponse response = CityResponse.builder()
                .name(request.getName())
                .zipCode(request.getZipCode())
                .build();

        City city = City.builder()
                .name(request.getName())
                .zipCode(request.getZipCode())
                .build();

        when(repository.save(city)).thenReturn(city);

        CityResponse actual = cityService.addCity(request);

        assertEquals(response, actual);

    }

    @Test
    @Order(4)
    void itShouldUpdateCity(){
        long id = 1;
        City city = City.builder()
                .id(id)
                .name("test")
                .zipCode("3")
                .build();

        UpdateCityRequest request = UpdateCityRequest.builder()
                .id(id)
                .name("test2")
                .zipCode("6")
                .build();

        CityResponse response = CityResponse.builder()
                .id(id)
                .name(request.getName())
                .zipCode(request.getZipCode())
                .build();

        City cityRepo = City.builder()
                .id(id)
                .name(request.getName())
                .zipCode(request.getZipCode())
                .build();

        when(repository.findById(id)).thenReturn(Optional.of(city));
        when(repository.save(cityRepo)).thenReturn(cityRepo);

        CityResponse actual = cityService.updateCity(request, id);
        assertEquals(response, actual);


    }





}
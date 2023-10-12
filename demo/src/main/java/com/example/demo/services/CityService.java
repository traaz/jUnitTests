package com.example.demo.services;

import com.example.demo.dataAccess.CityRepository;

import com.example.demo.entities.City;

import com.example.demo.services.request.AddCityRequest;
import com.example.demo.services.request.UpdateCityRequest;
import com.example.demo.services.response.CityResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityService {
    private CityRepository cityRepository;

    public List<CityResponse> getAllCities(){
            List<City> cities = cityRepository.findAll();
            List<CityResponse> responses = cities.stream()
                    .map(model -> CityResponse.builder()
                            .name(model.getName())
                            .id(model.getId())
                            .zipCode(model.getZipCode())
                            .build())
                    .toList();
            return responses;
    }

    public CityResponse addCity(AddCityRequest addCityRequest){
        City city = City.builder()
                .name(addCityRequest.getName())
                .zipCode(addCityRequest.getZipCode())
                .build();
        cityRepository.save(city);

        return CityResponse.builder()
                .name(city.getName())
                .id(city.getId())
                .zipCode(city.getZipCode())
        .build();
    }

    public CityResponse getCityById(long id){
        City city = cityRepository.findById(id).get();
        return CityResponse.builder()
                .name(city.getName())
                .id(city.getId())
                .zipCode(city.getZipCode())
                .build();
    }
    public void deleteCity(long id){
        cityRepository.deleteById(id);
    }
    public CityResponse updateCity(UpdateCityRequest updateCityRequest, long id){
        City city = cityRepository.findById(id).get();
        city = City.builder()
                .id(updateCityRequest.getId())
                .name(updateCityRequest.getName())
                .zipCode(updateCityRequest.getZipCode())
                .build();
        cityRepository.save(city);
        return CityResponse.builder()
                .name(city.getName())
                .id(city.getId())
                .zipCode(city.getZipCode())
                .build();

    }

}

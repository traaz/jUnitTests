package com.example.demo.webApi;

import com.example.demo.services.CityService;
import com.example.demo.services.request.AddCityRequest;
import com.example.demo.services.request.UpdateCityRequest;
import com.example.demo.services.response.CityResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {
    private CityService cityService;

    @GetMapping("/getAll")
    public List<CityResponse> getAllCities(){
        return cityService.getAllCities();
    }
    @GetMapping("/get/{id}")
    public CityResponse getById(@PathVariable long id){
        return cityService.getCityById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        cityService.deleteCity(id);
    }
    @PostMapping()
    public CityResponse add(@RequestBody AddCityRequest addCityRequest){
        return cityService.addCity(addCityRequest);
    }
    @PutMapping("/{id}")
    public CityResponse update(@RequestBody UpdateCityRequest updateCityRequest, @PathVariable long id){
        return cityService.updateCity(updateCityRequest, id);
    }


}

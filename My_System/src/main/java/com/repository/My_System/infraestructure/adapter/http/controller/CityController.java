package com.repository.My_System.infraestructure.adapter.http.controller;

import com.repository.My_System.domain.ports.in.CrearCityInPort;
import com.repository.My_System.domain.ports.in.DeleteCityInPort;
import com.repository.My_System.domain.ports.in.FindAllCityInPort;
import com.repository.My_System.domain.ports.in.FindCityInPort;
import com.repository.My_System.infraestructure.dto.http.request.CityRequest;
import com.repository.My_System.infraestructure.dto.http.response.CityResponse;
import com.repository.My_System.infraestructure.mapper.CityMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CityController {

    private final CityMapper cityMapper;
    private final CrearCityInPort crearCityInPort;
    private final FindCityInPort findCityInPort;
    private final FindAllCityInPort findAllCityInPort;
    private final DeleteCityInPort deleteCityInPort;




    @Operation(description = "List of cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of cities successfully obtained"),
            @ApiResponse(responseCode = "500", description = "Unexpected error in server.")
    })
    @GetMapping
    public ResponseEntity<List<CityResponse>> findAll(){
        log.info("The controller successfully activated the Get All Cities function");
        var response = findAllCityInPort.findAll().stream().map(cityMapper::domainToResponse).toList();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Created city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created city"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Unexpected error in server.")
    })
    @PostMapping
    public ResponseEntity<CityResponse> crear(@RequestBody CityRequest request){
        log.info("Successfully created city");
        var response = cityMapper.domainToResponse(crearCityInPort.createCity(cityMapper.requestToDomain(request)));
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Get city by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "500", description = "Unexpected error in server.")
    })
    @GetMapping("{id}")
    public ResponseEntity<CityResponse> getForId(@PathVariable Long id){
        log.info("The controller successfully activated the Get cities by ID function");
        var response = cityMapper.domainToResponse(findCityInPort.findCity(id));
        return ResponseEntity.ok(response);

    }

    @Operation(description = "Delete city by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No content"),
            @ApiResponse(responseCode = "500", description = "Unexpected error in server.")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        log.info("Delete by id city was triggered successfully by Controller");
        deleteCityInPort.deleteCity(id);
        return ResponseEntity.noContent().build();

    }



}

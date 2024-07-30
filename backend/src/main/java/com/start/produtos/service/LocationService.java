package com.start.produtos.service;

import com.start.produtos.model.LocationModel;
import com.start.produtos.model.ResponseModel;
import com.start.produtos.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class LocationService {

    @Autowired
    private LocationRepository lr;

    @Autowired
    private ResponseModel rm;

    public ResponseEntity<?> search(String city) {
        if (city == null || city.isEmpty()) {
            rm.setMessage("Cidade inválida");
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }else{
            LocationModel location = lr.findByCity(city);
            if (location == null) {
                rm.setMessage("Cidade não encontrada");
                return new ResponseEntity<>(rm, HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(location, HttpStatus.OK);
            }
        }

    }
}

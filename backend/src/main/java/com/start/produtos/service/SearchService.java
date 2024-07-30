package com.start.produtos.service;

import com.start.produtos.model.LocationModel;
import com.start.produtos.model.ResponseModel;
import com.start.produtos.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class SearchService {

    @Autowired
    private LocationRepository lr;

    public ResponseEntity<?> search(String keyWord, String searchParam){
        if (isValidKeyWord(keyWord)) {
            return findLocationByKeyWord(keyWord, searchParam);
        } else {
            return buildErrorResponse("Cidade inválida", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isValidKeyWord(String keyWord){
        return keyWord != null && !keyWord.isEmpty();
    }

    private ResponseEntity<?> findLocationByKeyWord(String keyWord, String  searchParam){
        LocationModel location;

        switch (searchParam) {
            case "city" -> location = lr.findByCity(keyWord);
            case "state" -> location = lr.findByState(keyWord);
            case "country" -> location = lr.findByCountry(keyWord);
            case "cep" -> location = lr.findByCep(keyWord);
            default -> {
                return buildErrorResponse("Parâmetro de pesquisa inválido", HttpStatus.BAD_REQUEST);
            }
        }

        if (location == null ) {
            return buildErrorResponse("Cidade não encontrada", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(location, HttpStatus.OK);
        }
    }

    private ResponseEntity<ResponseModel> buildErrorResponse(String message, HttpStatus status){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage(message);
        return new ResponseEntity<>(responseModel, status);
    }
}

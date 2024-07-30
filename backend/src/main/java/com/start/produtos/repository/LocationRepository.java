package com.start.produtos.repository;

import com.start.produtos.model.LocationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<LocationModel, Long> {
    LocationModel findByCity(String city);
    LocationModel findByState(String state);
    LocationModel findByCountry(String country);
    LocationModel findByCep(String cep);
}

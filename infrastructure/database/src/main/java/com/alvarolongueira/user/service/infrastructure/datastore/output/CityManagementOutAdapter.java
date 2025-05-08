// package com.alvarolongueira.user.service.infrastructure.datastore.output;
//
// import com.alvarolongueira.user.service.domain.entity.City;
// import com.alvarolongueira.user.service.domain.entity.factory.Id;
// import com.alvarolongueira.user.service.domain.entity.factory.UserFactory;
// import com.alvarolongueira.user.service.infrastructure.datastore.jpa.CityDTO;
// import com.alvarolongueira.user.service.infrastructure.datastore.jpa.CityRepository;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
//
// import java.util.Optional;
//
// @Component
// public class CityManagementOutAdapter implements CityManagementOutputPort {
//
//    @Autowired CityRepository cityRepository;
//
//    @Override
//    public Optional<City> retrieveCity(Id id) {
//        var cityDTO = cityRepository.findById(id.toString());
//        return cityDTO.map(
//                dto -> UserFactory.createCity(id.toString(), dto.getName(), dto.getState()));
//    }
//
//    @Override
//    public Optional<City> removeCity(Id id) {
//        var city = this.retrieveCity(id);
//        cityRepository.deleteById(id.toString());
//        return city;
//    }
//
//    @Override
//    public City saveCity(City city) {
//        CityDTO cityDTO = toDTO(city);
//        cityRepository.save(cityDTO);
//        return city;
//    }
//
//    private CityDTO toDTO(City city) {
//        return CityDTO.builder()
//                .id(city.getId().toString())
//                .state(city.getState().name())
//                .name(city.getName())
//                .build();
//    }
// }

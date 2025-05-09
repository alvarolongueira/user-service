// package com.alvarolongueira.user.service.rest.api.adapters.input;
//
// import static org.hamcrest.CoreMatchers.is;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.doReturn;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
// import com.alvarolongueira.user.service.domain.entity.factory.UserFactory;
// import com.alvarolongueira.user.service.domain.vo.State;
// import com.alvarolongueira.user.service.rest.api.adapters.input.request.CityRequest;
// import com.alvarolongueira.user.service.rest.api.utils.JsonUtils;
//
// import lombok.SneakyThrows;
//
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.context.annotation.Import;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.bean.override.mockito.MockitoBean;
// import org.springframework.test.web.servlet.MockMvc;
//
// @AutoConfigureMockMvc
// @WebMvcTest
// @Import(CityManagementInAdapter.class)
// class CityManagementInAdapterTest {
//
//    @Autowired MockMvc mockMvc;
//    @MockitoBean CityManagementUseCase cityManagementUseCase;
//
//    @SneakyThrows
//    @Test
//    void shouldReturnCityWhenCreateCityWithSuccess() {
//        var request = new CityRequest("São Paulo", "SP");
//        var city = UserFactory.createCity(request.name(), State.valueOf(request.state()));
//        doReturn(city).when(cityManagementUseCase).createCity(anyString(), any(State.class));
//
//        mockMvc.perform(
//                        post("/api")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(JsonUtils.toJson(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(city.getName())));
//    }
// }

package com.flexidorm.artsch.controllerTest;

import com.flexidorm.artsch.rental_management.application.controllers.RentalController;
import com.flexidorm.artsch.rental_management.application.dto.request.RegisterRentalRequestDto;
import com.flexidorm.artsch.rental_management.application.dto.response.RegisterRentalResponseDto;
import com.flexidorm.artsch.rental_management.application.services.IRentalService;
import com.flexidorm.artsch.shared.model.dto.response.ApiResponse;
import com.flexidorm.artsch.shared.model.enums.EStatus;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Disabled;

@Disabled("For deployment")
@SpringBootTest
@AutoConfigureMockMvc
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private RentalController rentalController;

    @Mock
    private IRentalService rentalService;

    @Test
    void testRegisterRental() throws Exception {
        // Define a RegisterRentalRequestDto
        RegisterRentalRequestDto request = new RegisterRentalRequestDto();

        // Define a RegisterRentalResponseDto
        RegisterRentalResponseDto response = new RegisterRentalResponseDto();

        // Define una instancia de ApiResponse que contiene RegisterRentalResponseDto
                ApiResponse<RegisterRentalResponseDto> apiResponse = new ApiResponse<>("Rental was successfully registered", EStatus.SUCCESS, response);

        // Mock el servicio para que devuelva el ApiResponse
                when(rentalService.registerRental(request)).thenReturn(apiResponse);


        // Perform a POST request to the controller endpoint
        ResultActions resultActions;
        resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rental/registerRental")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "date":"2024-07-02",
                                    "email":"pepe1@gmail.com",
                                    "totalPrice":150,
                                    "hourFinal":"09:00",
                                    "hourInit":"14:00",
                                    "imageUrl":"https://www.thespruce.com/thmb/iMt63n8NGCojUETr6.jpg",
                                    "observation":"aaaa",
                                    "phone":"999888777",
                                    "room":1,
                                    "student":1
                                }"""))
                .andExpect(status().isCreated());
    }
    
    @Test
    void testGetRentalsByStudentId() throws Exception {
        // Definir el ID de estudiante para la prueba
        String studentId = "1"; // Ajusta el valor del ID de estudiante según tus necesidades

        // Definir una lista de RegisterRentalResponseDto que se espera como resultado
        List<RegisterRentalResponseDto> rentalList = new ArrayList<>();
        // Agregar elementos a la lista según tus necesidades

        // Crear un ApiResponse que contiene la lista de RegisterRentalResponseDto
        ApiResponse<List<RegisterRentalResponseDto>> apiResponse = new ApiResponse<>("Rentals retrieved successfully", EStatus.SUCCESS, rentalList);

        // Mockear el servicio para que devuelva el ApiResponse
        when(rentalService.getRentalsByStudentId(studentId)).thenReturn(apiResponse);

        // Realizar una solicitud GET al controlador endpoint
        ResultActions resultActions;
        resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rental/getRentalsByStudentId/{student}", studentId))
                .andExpect(status().isOk());
    }

}

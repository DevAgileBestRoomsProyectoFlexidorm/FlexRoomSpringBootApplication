package com.flexidorm.artsch.application.services;

import com.flexidorm.artsch.rental_management.application.dto.response.RegisterRentalResponseDto;
import com.flexidorm.artsch.rental_management.application.dto.response.RegisterRoomResponseDto;
import com.flexidorm.artsch.rental_management.domain.entities.Room;
import com.flexidorm.artsch.security_management.application.dto.response.ArrenderResponseDto;
import com.flexidorm.artsch.security_management.domain.entities.User;
import com.flexidorm.artsch.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface IAdminService {

    ApiResponse<List<ArrenderResponseDto>> getAllUser();

    ApiResponse<List<RegisterRoomResponseDto>>getAllRooms();

    ApiResponse<List<RegisterRentalResponseDto>>getAllRental();
    ApiResponse<List<ArrenderResponseDto>> deleteUserById(int userId, boolean status);
    ApiResponse<List<RegisterRoomResponseDto>> deleteRoomById(int roomId, boolean status);
}

package com.flexidorm.artsch.application.controllers;

import com.flexidorm.artsch.application.services.IAdminService;
import com.flexidorm.artsch.rental_management.application.dto.response.RegisterRentalResponseDto;
import com.flexidorm.artsch.rental_management.application.dto.response.RegisterRoomResponseDto;
import com.flexidorm.artsch.rental_management.domain.entities.Room;
import com.flexidorm.artsch.security_management.application.dto.response.ArrenderResponseDto;
import com.flexidorm.artsch.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.flexidorm.artsch.security_management.domain.entities.User;
import java.util.List;

@Tag(name = "Admin")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "Get users")
        @GetMapping("/getUsers")
    public ResponseEntity<ApiResponse<List<ArrenderResponseDto>>> getUsersAll(){
        var res = adminService.getAllUser();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get rooms")
    @GetMapping("/getRooms")
    public ResponseEntity<ApiResponse<List<RegisterRoomResponseDto>>> getRoomsAll(){
        var res = adminService.getAllRooms();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get rental")
    @GetMapping("/getRental")
    public ResponseEntity<ApiResponse<List<RegisterRentalResponseDto>>> getRentalAll(){
        var res = adminService.getAllRental();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Activate and Desactivate account")
    @PostMapping("/acUnAccount/{userId}/{status}")
    public ResponseEntity<ApiResponse<List<ArrenderResponseDto>>> updateAccountAll(@PathVariable int userId, @PathVariable boolean status){
        var res = adminService.deleteUserById(userId, status);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Activate and Desactivate room")
    @PostMapping("/acUnRooms/{roomId}/{status}")
    public ResponseEntity<ApiResponse<List<RegisterRoomResponseDto>>> deleteRoomById(@PathVariable int roomId, @PathVariable boolean status){
        var res = adminService.deleteRoomById(roomId, status);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

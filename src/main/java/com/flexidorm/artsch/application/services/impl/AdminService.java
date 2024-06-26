package com.flexidorm.artsch.application.services.impl;

import com.flexidorm.artsch.application.services.IAdminService;
import com.flexidorm.artsch.rental_management.application.dto.response.RegisterRentalResponseDto;
import com.flexidorm.artsch.rental_management.application.dto.response.RegisterRoomResponseDto;
import com.flexidorm.artsch.rental_management.domain.entities.Reservation;
import com.flexidorm.artsch.rental_management.domain.entities.Room;
import com.flexidorm.artsch.rental_management.infrastructure.repositories.IRentalRepository;
import com.flexidorm.artsch.rental_management.infrastructure.repositories.IRoomRepository;
import com.flexidorm.artsch.security_management.application.dto.response.ArrenderResponseDto;
import com.flexidorm.artsch.security_management.domain.entities.User;
import com.flexidorm.artsch.security_management.infrastructure.repositories.IUserRepository;
import com.flexidorm.artsch.shared.model.dto.response.ApiResponse;
import com.flexidorm.artsch.shared.model.enums.EStatus;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService implements IAdminService {

    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final IRoomRepository iRoomRepository;
    private final IRentalRepository iRentalRepository;

    public AdminService(IUserRepository userRepository, ModelMapper modelMapper,
                        IRoomRepository iRoomRepository,
                        IRentalRepository iRentalRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.iRoomRepository = iRoomRepository;
        this.iRentalRepository = iRentalRepository;
    }

    @Override
    public ApiResponse<List<ArrenderResponseDto>> getAllUser() {

        List<User> users=userRepository.findAllArrendersAndStudent();


        List<ArrenderResponseDto> arrenderResponseDto = users.stream()
                .map(user -> modelMapper.map(user, ArrenderResponseDto.class))
                .toList();
        return new ApiResponse<>("Rentals were successfully obtained", EStatus.SUCCESS, arrenderResponseDto);
    }


    @Override
    public ApiResponse<List<RegisterRoomResponseDto>> getAllRooms() {
        List<Room> rooms=iRoomRepository.findAll();

        List<RegisterRoomResponseDto> registerRoomResponseDto = rooms.stream()
                .map(room -> modelMapper.map(room, RegisterRoomResponseDto.class))
                .toList();
        return new ApiResponse<>("Rooms were successfully obtained", EStatus.SUCCESS, registerRoomResponseDto);
    }

    @Override
    public ApiResponse<List<RegisterRentalResponseDto>> getAllRental() {

        List<Reservation> reservations = iRentalRepository.findAll();

        List<RegisterRentalResponseDto> registerRentalResponseDtos= reservations.stream()
                .map(reservation ->modelMapper.map(reservation,RegisterRentalResponseDto.class)).toList();

        return new ApiResponse<>("Rental were succesfully obtained", EStatus.SUCCESS, registerRentalResponseDtos);
    }

    @Override
    public ApiResponse<List<ArrenderResponseDto>> deleteUserById(int userId, boolean status) {
        userRepository.updateIsVerifiedForArrenderOrStudent(userId, status);
        Optional<User> users= userRepository.findUserById(userId);

        List<ArrenderResponseDto> arrenderResponseDto = users.stream()
                .map(user -> modelMapper.map(user, ArrenderResponseDto.class)).toList();
        return new ApiResponse<>("Rentals were successfully obtained", EStatus.SUCCESS, arrenderResponseDto);
    }

    @Override
    public ApiResponse<List<RegisterRoomResponseDto>> deleteRoomById(int roomId, boolean status) {
        iRoomRepository.updateIsVerifiedForRoom(roomId, status);
        Optional<Room> rooms= iRoomRepository.findRoomsById(roomId);

        List<RegisterRoomResponseDto> registerRoomResponseDto = rooms.stream()
                .map(room -> modelMapper.map(room, RegisterRoomResponseDto.class)).toList();
        return new ApiResponse<>("Rentals were successfully obtained", EStatus.SUCCESS, registerRoomResponseDto);
    }
}

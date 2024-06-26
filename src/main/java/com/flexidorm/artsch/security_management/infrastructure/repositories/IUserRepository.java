package com.flexidorm.artsch.security_management.infrastructure.repositories;

import com.flexidorm.artsch.security_management.application.dto.response.ArrenderResponseDto;
import com.flexidorm.artsch.security_management.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    /**
     * Verifica si existe un usuario con el email dado
     * @param email Email
     * @return Un booleano
     */
    boolean existsByEmail(String email);

    /**
     * Verifica si existe un usuario con el número de celular dado
     * @param phoneNumber El número de celular
     * @return boolean
     */
    boolean existsByPhoneNumber(String phoneNumber);

    /**
     * Verifica si existe un usuario con el nombre de usuario dado
     * @param username Nombre de usuario
     * @return Un booleano
     */
    boolean existsByUsername(String username);

    /**
     * Obtiene un usuario con el email dado
     * @param email Email
     * @return Un usuario
     */
    Optional<User> findByEmail(String email);


    /**
     * Verifica si existe un usuario con el id de arrender dado
     * @param arrenderId El id de arrender
     * @return boolean
     */
    boolean existsByUserId(Long arrenderId);

    /**
     * Obtiene un usuario con el id de arrender dado
     * @param arrenderId El id de arrender
     * @return Un usuario
     */
    Optional<User> findByUserId(Long arrenderId);

    @Query("SELECT u FROM User u WHERE TYPE(u) IN (Arrender, Student)")
    List<User> findAllArrendersAndStudent();

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isActive = :status WHERE u.userId = :userId ")
    void updateIsVerifiedForArrenderOrStudent(@Param("userId") int userId, @Param("status") int status);

    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    Optional<User> findUserById(@Param("userId") int userId);



}

package com.mcb.dto.mapper;

import com.mcb.dto.UserDTO;
import com.mcb.model.User;


public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getLastname(),
                user.getFirstname(),
                user.getBrithday(),
                user.getSurnameBrithday(),
                user.getNicNumber(),
                user.getFiles()
        );
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setLastname(userDTO.getLastname());
        user.setFirstname(userDTO.getFirstname());
        user.setBrithday(userDTO.getBrithday());
        user.setSurnameBrithday(userDTO.getSurnameBrithday());
        user.setNicNumber(userDTO.getNicNumber());
        user.setFiles(userDTO.getFiles());

        return user;
    }

    public static User upDate(User user, UserDTO userDto) {

        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }

        if (userDto.getLastname() != null) {
            user.setLastname(userDto.getLastname());
        }

        if (userDto.getFirstname() != null) {
            user.setFirstname(userDto.getFirstname());
        }

        if (userDto.getBrithday() != null) {
            user.setBrithday(userDto.getBrithday());
        }

        if (userDto.getSurnameBrithday() != null) {
            user.setSurnameBrithday(userDto.getSurnameBrithday());
        }

        if (userDto.getNicNumber() != null) {
            user.setNicNumber(userDto.getNicNumber());
        }

        return user;
    }
}

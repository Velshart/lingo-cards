package me.mmtr.lingocards.service.interfaces;

import me.mmtr.lingocards.data.User;
import me.mmtr.lingocards.data.dto.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDto);
    User findUserByUsername(String username);

    List<UserDTO> findAllUsers();
}


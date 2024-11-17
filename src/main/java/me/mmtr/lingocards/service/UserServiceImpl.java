package me.mmtr.lingocards.service;

import me.mmtr.lingocards.data.Role;
import me.mmtr.lingocards.data.User;
import me.mmtr.lingocards.data.dto.UserDTO;
import me.mmtr.lingocards.repository.RoleRepository;
import me.mmtr.lingocards.repository.UserRepository;
import me.mmtr.lingocards.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("USER");
        if (role == null) {
            role = checkIfRoleExistsOrCreate();
        }

        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::mapToUserDto)
                .toList();
    }

    private UserDTO mapToUserDto(User user) {
        UserDTO userDto = new UserDTO();
        String[] str = user.getUsername().split(" ");
        userDto.setUsername(str[0]);
        return userDto;
    }

    private Role checkIfRoleExistsOrCreate() {
        Role role = new Role();
        role.setName("USER");
        return roleRepository.save(role);
    }
}

package jstorra.backend.services;

import jstorra.backend.exceptions.UserDuplicateException;
import jstorra.backend.exceptions.InvalidUserException;
import jstorra.backend.models.User;
import jstorra.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void userValidation(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getEncryptedPass().equalsIgnoreCase(password)) {
            throw new InvalidUserException("Las credenciales ingresadas son incorrectas.");
        }
    }

    public Map<Object, Object> registerUser(User user) {
        try {
            userRepository.save(user);
            return new LinkedHashMap<>() {{
                put("message", "El usuario ha sido registrado.");
            }};
        } catch (Exception e) {
            throw new UserDuplicateException("Nombre de usuario ya existe.");
        }
    }
}

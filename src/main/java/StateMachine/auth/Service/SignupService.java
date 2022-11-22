package StateMachine.auth.Service;

import StateMachine.MailNotificationService;
import StateMachine.User.Entities.Role;
import StateMachine.User.Entities.User;
import StateMachine.User.Repository.RoleRepository;
import StateMachine.User.Repository.UserRepository;
import StateMachine.User.Roles;
import StateMachine.auth.Entities.SignupActivactionDTO;
import StateMachine.auth.Entities.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MailNotificationService mailNotificationService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User signup(SignupDTO signupDTO) throws Exception {
        return this.signup(signupDTO, Roles.REGISTERED);
    }

    public User signup(SignupDTO signupDTO, String role) throws Exception {
        User userInDB = userRepository.findByEmail(signupDTO.getEmail());
        if(userInDB != null) throw new Exception("User already Exists");
        User user = new User();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setSurname(signupDTO.getSurname());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());
        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName(role.toUpperCase());
        if(!userRole.isPresent()) throw new Exception("Cannot set role");
        roles.add(userRole.get());
        user.setRoles(roles);

        mailNotificationService.sendActivationEmail(user);
        return userRepository.save(user);
    }

    public User activate(SignupActivactionDTO signupActivactionDTO) throws Exception {
        User user = userRepository.findByActivationCode(signupActivactionDTO.getActivationCode());
        if(user == null) throw new Exception("User Not found");
        user.setActive(true);
        user.setActivationCode(null);
        return userRepository.save(user);
    }
}

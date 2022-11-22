package StateMachine.auth.Controller;

import StateMachine.User.Entities.Role;
import StateMachine.User.Entities.User;
import StateMachine.User.Repository.RoleRepository;
import StateMachine.auth.Entities.SignupActivactionDTO;
import StateMachine.auth.Entities.SignupDTO;
import StateMachine.auth.Service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping("/signup")
    public User signup(@RequestBody SignupDTO signupDTO) throws Exception {
        return signupService.signup(signupDTO);
    }

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/signup/{role}")
    public User signup(@RequestBody SignupDTO signupDTO, @PathVariable String role) throws Exception {
        return signupService.signup(signupDTO, role);
    }

    @PostMapping("/signup/activation")
    public User signup(@RequestBody SignupActivactionDTO signupActivactionDTO) throws Exception {
        return signupService.activate(signupActivactionDTO);
    }

    @PostMapping("/roles") //for populate the role's table, else i can't create the user
    public Role createRoles(@RequestBody Role role){
        return roleRepository.save(role);
    }
}

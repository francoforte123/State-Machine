package StateMachine.auth.Controller;

import StateMachine.auth.Entities.RequestPasswordDTO;
import StateMachine.auth.Entities.ResetPasswordDTO;
import StateMachine.auth.Service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/password")
public class PasswordResetController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/request")
    public void passwordRequest(@RequestBody RequestPasswordDTO requestPasswordDTO) throws Exception{
        try {
            passwordService.request(requestPasswordDTO);
        }catch (Exception e){

        }

    }

    @PostMapping("/reset")
    public void passwordRestore(@RequestBody ResetPasswordDTO resetPasswordDTO) throws Exception{
        passwordService.restore(resetPasswordDTO);

    }
}

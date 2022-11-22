package StateMachine.auth.Controller;

import StateMachine.auth.Entities.LoginDTO;
import StateMachine.auth.Entities.LoginRTO;
import StateMachine.auth.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public LoginRTO login(@RequestBody LoginDTO loginDTO) throws Exception{
        LoginRTO loginRto = loginService.login(loginDTO);
        if(loginRto == null) throw new Exception("Cannot login");
        return loginRto;
    }
}

package StateMachine.auth.Service;

import StateMachine.MailNotificationService;
import StateMachine.User.Entities.User;
import StateMachine.User.Repository.UserRepository;
import StateMachine.auth.Entities.RequestPasswordDTO;
import StateMachine.auth.Entities.ResetPasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailNotificationService mailNotificationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User request(RequestPasswordDTO requestPasswordDTO) throws Exception{
        User userFromDB = userRepository.findByEmail(requestPasswordDTO.getEmail());
        if(userFromDB == null) throw new Exception("Cannot find user");
        userFromDB.setPasswordResetCode(UUID.randomUUID().toString());
        mailNotificationService.sendPasswordResetMail(userFromDB);
        return userRepository.save(userFromDB);

    }
    public User restore(ResetPasswordDTO resetPasswordDTO) throws Exception{
        User userFromDB = userRepository.findByPasswordResetCode(resetPasswordDTO.getResetPasswordCode());
        if(userFromDB == null) throw new Exception("Cannot find user");
        userFromDB.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
        userFromDB.setPasswordResetCode(null);

        //I am activating the user
        userFromDB.setActive(true);
        userFromDB.setActivationCode(null);

        return userRepository.save(userFromDB);
    }
}

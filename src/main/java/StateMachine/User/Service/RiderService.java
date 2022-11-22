package StateMachine.User.Service;

import StateMachine.User.Entities.User;
import StateMachine.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RiderService {

    @Autowired
    private UserRepository userRepository;

    public User pickRider(){
        if(userRepository.pickRider().isPresent()){
            return userRepository.pickRider().get();
        }else{
            return userRepository.findAll(PageRequest.of(0,1)).toList().get(0);
        }
    }
}

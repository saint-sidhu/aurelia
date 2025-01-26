package dev.overlord.aurelia.serviceImpl;

import dev.overlord.aurelia.entity.UserDetailsEntity;
import dev.overlord.aurelia.repository.UserDetailsRepo;
import dev.overlord.aurelia.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    private UserDetailsRepo userDetailsRepo;

    public String getUserDetails(String userName) {
        UserDetailsEntity userDetailsEntity = userDetailsRepo.findByUserName(userName);

        if (userDetailsEntity != null) {
            return "";
        } else {
            UserDetailsEntity newUser = new UserDetailsEntity();
            newUser.setUserName(userName);
            userDetailsRepo.saveAndFlush(newUser);
        }
        return "A new instance of lowly creature has been registered!";
    }
}

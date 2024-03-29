package ch.zhaw.gpi.twitterreview;

import java.util.Optional;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.camunda.bpm.engine.delegate.JavaDelegate;
//import org.camunda.bpm.engine.impl.persistence.entity.UserEntity;

/**
 * GetUserInformationDelegate
 */
@Named("getUserInformationAdapter")
public class GetUserInformationDelegate implements JavaDelegate {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception{

        String userName = (String) execution.getVariable("anfrageStellenderBenutzer");
        String fullName;


        Optional<ch.zhaw.gpi.twitterreview.UserEntity> user = userRepository.findById(userName);

        if(user.isPresent()){
            fullName = user.get().getFirstName() + " " + user.get().getLastName();

        }
        else{
            fullName = "Mr. X";
        }

        execution.setVariable("vollerName", fullName);
        

    }
    
}


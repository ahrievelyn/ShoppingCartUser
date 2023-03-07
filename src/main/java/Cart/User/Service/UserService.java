package Cart.User.Service;

import Cart.User.Model.User;
import jakarta.transaction.InvalidTransactionException;

import java.util.HashMap;

public interface UserService {

    public User userRegistration(User u);
    public boolean userAuthentication(String emailId, String password);
    public User userProfileManagement(User u, Integer userId) throws InvalidTransactionException;
    public void resetPassword(Integer userId, String password);
    public void DeactivateOrBan(Integer userId, String status) throws InvalidTransactionException;
    //public User permissionManagement(Integer userId, HashMap<String,Boolean> userInput);
}

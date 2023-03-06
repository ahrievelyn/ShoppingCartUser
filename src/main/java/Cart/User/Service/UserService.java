package Cart.User.Service;

import Cart.User.Model.User;

public interface UserService {

    public User userRegistration(User u);
    public boolean userAuthentication(String emailId, String password);
}

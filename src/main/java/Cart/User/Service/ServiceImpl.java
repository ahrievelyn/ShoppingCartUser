package Cart.User.Service;

import Cart.User.Model.User;

import Cart.User.Repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements UserService{
    @Autowired
    UserRepository ur;

    @Override
    public User userRegistration(User newUser) {
        //User u has the item returned by findByEmail. If email exists dont add
        User u = ur.findByEmail(newUser.getEmail());
        if(u==null)
        {
            ur.save(newUser);
            return newUser;
        }
        else
        {
            throw new EntityExistsException("email Id already exists");
        }
    }

    @Override
    public boolean userAuthentication(String email, String password)
    {
        User u = ur.findByEmail(email);
        return u.getPassword().equals(password);
    }
}

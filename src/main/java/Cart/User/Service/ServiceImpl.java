package Cart.User.Service;

import Cart.User.Exceptions.ItemNotFoundException;
import Cart.User.Model.User;

import Cart.User.Repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.InvalidTransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Character.toUpperCase;

@Service
public class ServiceImpl implements UserService {
    @Autowired
    UserRepository ur;

    @Override
    public User userRegistration(User newUser) {
        //User u has the item returned by findByEmail. If email exists dont add
        User u = ur.findByEmail(newUser.getEmail());
        if (u == null) {
            ur.save(newUser);
            return newUser;
        } else {
            throw new EntityExistsException("email Id already exists");
        }
    }

    @Override
    public boolean userAuthentication(String email, String password) {
        User u = ur.findByEmail(email);
        return u.getPassword().equals(password);
    }

    @Override
    public User userProfileManagement(User updatedUser, Integer userId) throws InvalidTransactionException {
        User oldUser = ur.findById(userId).orElseThrow(() -> new ItemNotFoundException("No user exists with given id"));
        oldUser.setName(Objects.isNull(updatedUser.getName()) ? oldUser.getName() : updatedUser.getName());
        //oldUser.setEmail(Objects.isNull(updatedUser.getEmail())?oldUser.getEmail():updatedUser.getEmail());
        if (!Objects.isNull(updatedUser.getEmail())) {
            User u = ur.findByEmail(updatedUser.getEmail());
            if (u == null) {
                oldUser.setEmail(updatedUser.getEmail());
                ur.save(oldUser);
            } else {
                throw new EntityExistsException("email address already used by someone else");
            }
        }
        oldUser.setAddress(Objects.isNull(updatedUser.getAddress()) ? oldUser.getAddress() : updatedUser.getAddress());
        oldUser.setPassword(Objects.isNull(updatedUser.getPassword()) ? oldUser.getPassword() : updatedUser.getPassword());
        oldUser.setPhoneNumber(Objects.isNull(updatedUser.getPhoneNumber()) ? oldUser.getPhoneNumber() : updatedUser.getPhoneNumber());
        oldUser.setDob(Objects.isNull(updatedUser.getDob()) ? oldUser.getDob() : updatedUser.getDob());
        if (!Objects.isNull(updatedUser.getGender())) {
            switch (updatedUser.getGender()) {
                case 'm', 'f', 'o', 'M', 'F', 'O':
                    oldUser.setGender(toUpperCase(updatedUser.getGender()));
                default:
                    throw new InvalidTransactionException("Gender type not valid");
            }
        }
        //oldUser.setAccountStatus(Objects.isNull(updatedUser.getAccountStatus())?oldUser.getAccountStatus():updatedUser.getAccountStatus());
        if (!Objects.isNull(updatedUser.getAccountStatus())) {
            switch (updatedUser.getAccountStatus()) {
                case "a","b","i","A","B","I":
                    oldUser.setAccountStatus(updatedUser.getAccountStatus());
                default:
                    throw new InvalidTransactionException("No such account status");
            }
        }
        oldUser.setLastLogin(Objects.isNull(updatedUser.getLastLogin()) ? oldUser.getLastLogin() : updatedUser.getLastLogin());
        /*if(oldUser.getPermissions()!=null) {
            permissionManagement(userId, oldUser.getPermissions());
        }*/
        ur.save(oldUser);
        return oldUser;
    }

    @Override
    public void resetPassword(Integer userId, String password) {
        User oldUser = ur.findById(userId).orElseThrow(() -> new ItemNotFoundException("No user exists with given id"));
        oldUser.setPassword(password);
        ur.save(oldUser);
    }
    @Override
    public void DeactivateOrBan(Integer userId, String status) throws InvalidTransactionException {
        User oldUser = ur.findById(userId).orElseThrow(() -> new ItemNotFoundException("No user exists with given id"));
        switch (status) {
            case "a", "b", "i", "A", "B", "I" -> {
                oldUser.setAccountStatus(status);
                ur.save(oldUser);
            }
        }
    }


  /*  public User permissionManagement(Integer userId, HashMap<String,Boolean> userInput) {
        User oldUser = ur.findById(userId).orElseThrow(() -> new ItemNotFoundException("No user exists with given id"));
        for (Map.Entry data : userInput.entrySet()) {
            //each data value i will update
            if(oldUser.getPermissions().containsKey(data.getKey()))
            {
                HashMap<String, Boolean> perm = oldUser.getPermissions();
                perm.replace((String) data.getKey(), (Boolean) data.getValue());
            }
            else {
                throw new ItemNotFoundException("Cannot update permission to something that doesn't exist");
            }
        }
        ur.save(oldUser);
        return oldUser;
    }

   */


}



package Cart.User.Controller;

import Cart.User.Exceptions.ItemNotFoundException;
import Cart.User.Model.User;
import Cart.User.Service.ServiceImpl;
import Cart.User.Service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.InvalidTransactionException;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    UserService us;
    @PostMapping("/users/add")
    public ResponseEntity<Object> userRegistration(@RequestBody User u)
    {
        try {
            User resp = us.userRegistration(u);
            return new ResponseEntity<>(u, HttpStatus.CREATED);
        }
        catch (EntityExistsException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("users/{email}/{password}")
    public ResponseEntity<Object> userAuthentication(@PathVariable("email") String email,@PathVariable("password") String password)
    {
        boolean res = us.userAuthentication(email,password);
        if(res)
        {
            return new ResponseEntity<>("USER LOGGED IN SUCCESSFULLY",HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>("USER LOGIN FAILED",HttpStatus.ACCEPTED);
        }
    }
    @PutMapping("users/id/{id}")
    public ResponseEntity<Object> userProfileManagement(@RequestBody User u, @PathVariable(name="id") Integer userId)
    {
        try {
            User resp = us.userProfileManagement(u,userId);
            return new ResponseEntity<>(u, HttpStatus.CREATED);
        }
        catch (ItemNotFoundException | EntityExistsException | InvalidTransactionException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("users/resetPassword/id/{id}")
    public ResponseEntity<Object> userProfileManagement(@PathVariable(name="id") Integer userId, @RequestBody String password)
    {
        try {
            us.resetPassword(userId,password);
            return new ResponseEntity<>(userId, HttpStatus.OK);
        }
        catch (ItemNotFoundException  e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("users/status/{id}")
    public ResponseEntity<Object> DeactivateOrBan(@PathVariable(name="id") Integer userId, @RequestBody String status)
    {
        try {
            us.DeactivateOrBan(userId,status);
            return new ResponseEntity<>(userId, HttpStatus.OK);
        }
        catch (InvalidTransactionException  e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}

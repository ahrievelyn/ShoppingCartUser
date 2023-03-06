package Cart.User.Controller;

import Cart.User.Model.User;
import Cart.User.Service.ServiceImpl;
import Cart.User.Service.UserService;
import jakarta.persistence.EntityExistsException;
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
        Boolean res = us.userAuthentication(email,password);
        if(res==true)
        {
            return new ResponseEntity<>("USER LOGIN SUCCESSFULL",HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>("USER LOGIN FAILED",HttpStatus.ACCEPTED);
        }
    }
}

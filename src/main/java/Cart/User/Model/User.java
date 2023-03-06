package Cart.User.Model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "Name")
    private String name;

    @Column(name="emailId")
    private String email;
    @Column(name="Address")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public char getPermission() {
        return permission;
    }

    public void setPermission(char permission) {
        this.permission = permission;
    }

    @Column(name = "Password")
    private String password;
    @Column(name = "PhoneNumber")
    private long phoneNumber;
    @Column(name = "dob")
    private Date dob;
    @Column(name="gender")
    private char gender;
    @Column(name="AccountStatus")
    private boolean accountStatus;
    @Column(name="LastLogin")
    private Date lastLogin;
    @Column(name = "Permission")
    private char permission;
}
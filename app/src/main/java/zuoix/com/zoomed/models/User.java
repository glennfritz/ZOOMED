package zuoix.com.zoomed.models;

import java.util.ArrayList;
import java.util.List;

public class User {

    String firstname;
    String lastname;
    String username;
    String password;
    String location;
    String email;
    String phone_number;
    List<TrackableDevice> devices;

    public User(String firstname, String lastname, String username, String password, String location, String email, String phone_number) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.location = location;
        this.email = email;
        this.phone_number = phone_number;
        this.devices = new ArrayList<>();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public List<TrackableDevice> getDevices() {
        return devices;
    }

    public void addDevice(TrackableDevice device) {
        devices.add(device);
    }

    public static List<User> setup(){
        List<User> userList = new ArrayList<>();
        //Creating demo users
        User mangi = new User("Mangi", "Elisha", "mangik", "123456", "Bonaberi Douala", "mangik@gmail.com", "664653211");
        User elvis = new User("Tchinda", "Elvis Tanku", "elvis", "654321", "Dirty South, Molyko Buea", "elvistchinda@yahoo.com", "672084140");
        //Adding user to list of users in system
        userList.add(mangi);
        userList.add(elvis);
        //Creating devices
        TrackableDevice toyota2004 = new TrackableDevice("Car", "Toyota 2004", 2, 672084140);
        TrackableDevice hyundiaVx = new TrackableDevice("Bike", "Hyundia Vx", 1, 672084140);
        mangi.addDevice(toyota2004);
        elvis.addDevice(hyundiaVx);
        return userList;
    }

}

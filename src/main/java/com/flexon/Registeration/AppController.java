package com.flexon.Registeration;
import java.sql.ResultSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AppController {

    @PostMapping("/new_user")
     public static String newUser(@RequestBody UserProfile user) {

       return UserProfile.addUser(user);
    }

    @GetMapping("/view_user/{email}")
    @ResponseBody
    public static String viewUser (@PathVariable("email") String email) {

        return UserProfile.viewUser(email);
    }

    @DeleteMapping("/delete_user/{email}")
    @ResponseBody
    public static String deleteUser (@PathVariable("email") String email) {

        return UserProfile.deleteUser(email);
    }

    @PutMapping("/update_user")
    @ResponseBody
    public static String updateUser (@RequestBody UserProfile user) {

        return UserProfile.updateUser(user);
    }

}

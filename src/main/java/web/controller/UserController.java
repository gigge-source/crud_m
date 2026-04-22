package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    public User getUser(Long id) {
        return null;
    }

    public void saveUser(User user) {

    }

    public void updateUser(User user) {

    }

    @PostMapping("/add")
    public String createUser(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("age") Long age,
            @RequestParam("email") String email
            ) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setAge((long) age);
        user.setEmail(email);

        userService.saveUser(user);

        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        User user = userService.getUser(id);

        user.setName(HtmlUtils.htmlUnescape(user.getName()));
        user.setSurname(HtmlUtils.htmlUnescape(user.getSurname()));
        user.setEmail(HtmlUtils.htmlUnescape(user.getEmail()));

        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/update")
    public String updateUser(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("age") int age,
            @RequestParam("email") String email
    ) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setAge((long) age);
        user.setEmail(email);

        userService.updateUser(user);
        return "redirect:/users";
    }

}

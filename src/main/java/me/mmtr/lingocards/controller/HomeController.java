package me.mmtr.lingocards.controller;

import me.mmtr.lingocards.service.interfaces.CardService;
import me.mmtr.lingocards.service.interfaces.CardSetService;
import me.mmtr.lingocards.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final CardService cardService;
    private final CardSetService cardSetService;
    private final UserService userService;

    public HomeController(CardService cardService, CardSetService cardSetService, UserService userService) {
        this.cardService = cardService;
        this.cardSetService = cardSetService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        model.addAttribute("cardsets", cardSetService.getAll().stream()
                .filter(cardSet -> cardSet.getUser().getUsername().equals(principal.getName()))
                .toList());
        return "home";
    }
}

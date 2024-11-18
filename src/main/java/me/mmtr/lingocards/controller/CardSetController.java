package me.mmtr.lingocards.controller;

import me.mmtr.lingocards.data.CardSet;
import me.mmtr.lingocards.service.interfaces.CardSetService;
import me.mmtr.lingocards.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/cardset")
public class CardSetController {

    private final CardSetService cardSetService;
    private final UserService userService;

    public CardSetController(CardSetService cardSetService, UserService userService) {
        this.cardSetService = cardSetService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String add(@ModelAttribute CardSet cardSet, Model model) {

        model.addAttribute("cardSet", cardSet);
        return "cardset";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute CardSet cardSet, Principal principal) {
        cardSet.setUser(userService.findUserByUsername(principal.getName()));
        cardSetService.saveOrUpdate(cardSet);
        return "redirect:/home";
    }

    @GetMapping("/cards/{id}")
    public String cards(@PathVariable Long id, Model model, Principal principal) {
        CardSet cardSet = cardSetService.getById(id).orElseThrow();
        model.addAttribute("cardSet", cardSet);
        return "cardset-cards";
    }

    @PostMapping("/cards/{id}")
    public String addCard(@PathVariable Long id, @ModelAttribute CardSet cardSet, Model model, Principal principal) {
        cardSet.setUser(userService.findUserByUsername(principal.getName()));
        cardSetService.saveOrUpdate(cardSet);
        return "redirect:/cardset/cards/" + cardSet.getId();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Optional<CardSet> cardSetOptional = cardSetService.getById(id);
        if (cardSetOptional.isEmpty()) {
            return "redirect:/home";
        }
        model.addAttribute("cardSet", cardSetOptional.get());
        return "cardset";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute CardSet cardSet, Principal principal) {
        CardSet exisiting = cardSetService.getById(cardSet.getId()).orElseThrow();

        exisiting.setName(cardSet.getName());
        exisiting.setUser(userService.findUserByUsername(principal.getName()));
        cardSetService.saveOrUpdate(exisiting);
        return "redirect:/home";
    }
}

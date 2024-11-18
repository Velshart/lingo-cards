package me.mmtr.lingocards.controller;

import me.mmtr.lingocards.data.Card;
import me.mmtr.lingocards.service.interfaces.CardService;
import me.mmtr.lingocards.service.interfaces.CardSetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;
    private final CardSetService cardSetService;

    public CardController(CardService cardService, CardSetService cardSetService) {
        this.cardService = cardService;
        this.cardSetService = cardSetService;
    }

    @GetMapping("/add")
    public String add(@ModelAttribute Card card, Model model) {
        model.addAttribute("card", card);

        return "card";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Card card, @RequestParam Long cardSetId) {
        card.setCardSet(cardSetService.getById(cardSetId).orElseThrow());

        cardService.saveOrUpdate(card);
        return "redirect:/home";
        //TODO: add card set id to this redirection
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Optional<Card> noteOptional = cardService.getById(id);
        if (noteOptional.isEmpty()) {
            return "redirect:/home";
        }
        model.addAttribute("card", noteOptional.get());
        return "card";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute Card card, @PathVariable Long id, @RequestParam Long cardSetId) {
        card.setCardSet(cardSetService.getById(cardSetId).orElseThrow());
        cardService.saveOrUpdate(card);
        return "redirect:/home";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        cardService.delete(id);
        return "redirect:/home";
    }


}

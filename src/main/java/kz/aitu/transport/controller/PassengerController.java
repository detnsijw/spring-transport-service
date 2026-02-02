package kz.aitu.transport.controller;

import kz.aitu.transport.model.Passenger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import kz.aitu.transport.service.PassengerService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/passengers")
public class PassengerController {
    private final PassengerService service = new PassengerService();

    @GetMapping
    public String getAllPassengers(Model model) throws SQLException {
        List<Passenger> passengers = service.getAllPassengers();
        model.addAttribute("passengers", passengers);
        return "passengers";
    }

    @PostMapping("/add")
    public String addPassenger(@RequestParam int cardId,
                           @RequestParam String name,
                           @RequestParam double balance
    ) throws SQLException {
        Passenger passenger = new Passenger(name, cardId, balance);
        service.addPassenger(passenger);
        return "redirect:/passengers";
    }

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable int id) throws SQLException {
        return service.getPassengerById(id);
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable int id) {
        service.deletePassengerById(id);
        return "redirect:/passengers";
    }
}

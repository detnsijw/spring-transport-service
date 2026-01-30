package controller;

import model.Passenger;
import org.springframework.web.bind.annotation.*;
import service.PassengerService;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService service = new PassengerService();

    @PostMapping
    public String register(@RequestBody Passenger p) throws SQLException {
        service.registerPassenger(p);
        return "Passenger registered";
    }

    @GetMapping("/{id}")
    public Passenger get(@PathVariable int id) throws SQLException {
        return service.getPassengerById(id);
    }
}

package kz.aitu.transport.controller;

import kz.aitu.transport.model.Passenger;
import org.springframework.web.bind.annotation.*;
import kz.aitu.transport.service.PassengerService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    private final PassengerService service = new PassengerService();

    @GetMapping
    public List<Passenger> getAllPassengers() throws SQLException {
        return service.getAllPassengers();
    }

    @PostMapping
    public String register(@RequestBody Passenger p) throws SQLException {
        service.registerPassenger(p);
        return "Passenger registered";
    }

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable int id) throws SQLException {
        return service.getPassengerById(id);
    }
}

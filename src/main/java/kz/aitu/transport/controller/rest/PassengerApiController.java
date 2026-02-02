package kz.aitu.transport.controller.rest;

import kz.aitu.transport.model.Passenger;
import kz.aitu.transport.service.PassengerService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerApiController {
    private final PassengerService service = new PassengerService();

    @GetMapping
    public List<Passenger> getAllPassengers() throws SQLException {
        return service.getAllPassengers();
    }

    @PostMapping
    public String add(@RequestBody Passenger p) throws SQLException {
        service.addPassenger(p);
        return "Passenger registered";
    }

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable int id) throws SQLException {
        return service.getPassengerById(id);
    }
}
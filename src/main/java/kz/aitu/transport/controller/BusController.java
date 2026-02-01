package kz.aitu.transport.controller;

import kz.aitu.transport.model.Bus;
import org.springframework.web.bind.annotation.*;
import kz.aitu.transport.service.BusService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    private final BusService service = new BusService();

    @GetMapping
    public List<Bus> getAllBuses() throws SQLException {
        return service.getAllBuses();
    }

    @GetMapping("/{id}")
    public Bus getBusById(@PathVariable int id) throws SQLException {
        return service.getBusById(id);
    }

    @PostMapping
    public String addBus(@RequestBody Bus bus) throws SQLException {
        service.addBus(bus);
        return "Bus added";
    }
}

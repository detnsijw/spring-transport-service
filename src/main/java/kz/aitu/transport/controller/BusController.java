package controller;

import model.Bus;
import org.springframework.web.bind.annotation.*;
import service.BusService;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    private final BusService service = new BusService();

    @GetMapping("/{id}")
    public Bus getBus(@PathVariable int id) throws SQLException {
        return service.getBusById(id);
    }

    @PostMapping
    public String addBus(@RequestBody Bus bus) throws SQLException {
        service.addBus(bus);
        return "Bus added";
    }
}

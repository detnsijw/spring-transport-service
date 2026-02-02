package kz.aitu.transport.controller;

import kz.aitu.transport.model.Bus;
import kz.aitu.transport.service.TransportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import kz.aitu.transport.service.BusService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/buses")
public class BusController {

    private final BusService service = new BusService();
    private final TransportService transportService = new TransportService();

    @GetMapping
    public String getAllBuses(Model model) throws SQLException {
        List<Bus> buses = service.getAllBuses();
        model.addAttribute("buses", buses);
        return "buses";
    }

    @GetMapping("/{id}")
    public Bus getBusById(@PathVariable int id) throws SQLException {
        return service.getBusById(id);
    }

    @PostMapping("/add")
    public String addBus(@RequestParam int id,
                         @RequestParam String routeName,
                         @RequestParam int capacity) throws SQLException {
        Bus bus = new Bus(id, routeName, capacity);
        service.addBus(bus);
        return "redirect:/buses";
    }

    @GetMapping("/delete/{id}")
    public String deleteBusById(@PathVariable int id) throws SQLException {
        service.deleteBus(id);
        return "redirect:/buses";
    }

    @PostMapping("/ride")
    public String ride(
            @RequestParam int busId,
            @RequestParam int passengerId,
            @RequestParam double fare
    ) throws SQLException {
        transportService.rideBus(busId, passengerId, fare);
        return "redirect:/buses";
    }
}

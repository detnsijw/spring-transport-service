package kz.aitu.transport.controller;

import kz.aitu.transport.model.Lrt;
import kz.aitu.transport.service.TransportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import kz.aitu.transport.service.LrtService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/lrts")
public class LrtController {
    private final LrtService service = new LrtService();
    private final TransportService transportService = new TransportService();

    @GetMapping
    public String getAllLrts(Model model) throws SQLException {
        List<Lrt> lrts = service.getAllLrts();
        model.addAttribute("lrts", lrts);
        return "lrts";
    }

    @GetMapping("/{id}")
    public Lrt getLrt(@PathVariable int id) throws SQLException {
        return service.getLrtById(id);
    }

    @PostMapping("/add")
    public String addLrt(@RequestParam int id,
                         @RequestParam String routeName,
                         @RequestParam int capacity) throws SQLException {
        Lrt lrt = new Lrt(id, routeName, capacity);
        service.addLrt(lrt);
        return "redirect:/lrts";
    }

    @GetMapping("/delete/{id}")
    public String deleteLrtById(@PathVariable int id) throws SQLException {
        service.deleteLrt(id);
        return "redirect:/lrts";
    }

    @PostMapping("/ride")
    public String ride(
            @RequestParam int lrtId,
            @RequestParam int passengerId,
            @RequestParam double fare
    ) throws SQLException {
        transportService.rideLrt(lrtId, passengerId, fare);
        return "redirect:/lrts";
    }
}

package kz.aitu.transport.controller;

import kz.aitu.transport.model.Lrt;
import org.springframework.web.bind.annotation.*;
import kz.aitu.transport.service.LrtService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/lrts")
public class LrtController {
    private final LrtService service = new LrtService();

    @GetMapping
    public List<Lrt> getAllLrts() throws SQLException {
        return service.getAllLrts();
    }

    @GetMapping("/{id}")
    public Lrt getLrt(@PathVariable int id) throws SQLException {
        return service.getLrtById(id);
    }

    @PostMapping
    public String addLrt(@RequestBody Lrt lrt) throws SQLException {
        service.addLrt(lrt);
        return "Lrt added";
    }
}

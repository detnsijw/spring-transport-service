package controller;

import model.Lrt;
import org.springframework.web.bind.annotation.*;
import service.LrtService;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/lrts")
public class LrtController {

    private final LrtService service = new LrtService();

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

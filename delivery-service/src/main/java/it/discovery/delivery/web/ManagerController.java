package it.discovery.delivery.web;

import it.discovery.delivery.domain.Manager;
import it.discovery.delivery.persistence.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("managers")
public class ManagerController {

    private final ManagerRepository managerRepository;

    @GetMapping
    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    @PostMapping
    public void save(@RequestBody Manager manager) {
        managerRepository.save(manager);
    }

}

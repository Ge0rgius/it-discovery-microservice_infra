package it.discovery.book.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LibraryController {

    @Value("${training}")
    private String trainingName;

    @GetMapping("training")
    public String getTrainingName() {
        return trainingName;
    }
}

package github.sh1rsh1n.seminar_3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.sh1rsh1n.seminar_3.services.DataProcessingService;

@RestController
@RequestMapping("/task")
public class TaskController {
    
    @Autowired
    private final DataProcessingService data;

    
}

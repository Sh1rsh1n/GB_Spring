package github.sh1rsh1n.seminar_5.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.sh1rsh1n.seminar_5.service.TaskService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService service;

    
}

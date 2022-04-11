package com.example.springwebsecurityc51.controller;

import com.example.springwebsecurityc51.converter.OperationDTOConverter;
import com.example.springwebsecurityc51.dto.OperationDTO;
import com.example.springwebsecurityc51.entity.Operation;
import com.example.springwebsecurityc51.entity.User;
import com.example.springwebsecurityc51.repository.OperationRepository;
import com.example.springwebsecurityc51.repository.UserRepository;
import com.example.springwebsecurityc51.service.OperationService;
import com.example.springwebsecurityc51.service.UserService;
import com.example.springwebsecurityc51.service.СalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/calc")
public class CalculatorController {

    private final OperationService operationService;

    private final СalculatorService сalculatorService;

    private final OperationDTOConverter operationDTOConverter;

    private final OperationRepository operationRepository;

    private final UserService userService;

    private final UserRepository userRepository;

    public CalculatorController(OperationService operationService, OperationRepository operationRepository, OperationDTOConverter operationDTOConverter, СalculatorService сalculatorService, UserService userService, UserRepository userRepository) {
        this.operationService = operationService;
        this.operationRepository = operationRepository;
        this.operationDTOConverter = operationDTOConverter;
        this.сalculatorService = сalculatorService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String calc(@ModelAttribute("calcOperation") Operation operation) {
        return "calculator/calc";
    }

    @PostMapping
    public String result(@Valid @ModelAttribute("calcOperation") OperationDTO operationDTO,
                         BindingResult bindingResult, Model model) {

        User user = userRepository.findByUsername(userService.getCurrentUsername()).get();
        Operation operation = operationDTOConverter.operationDTOtoOperation(operationDTO);
        operation.setResult(сalculatorService.getResult(operation));
        operation.setUser(user);
        operationService.save(operation);

        model.addAttribute("msgResult", operation.getResult());

        return "calculator/calc";
    }

    @GetMapping("/history")
    public String history(Model model) {

        model.addAttribute("userHistory", operationRepository.findAllByUser(userService.getCurrentUser()));

        return "calculator/history";
    }
}

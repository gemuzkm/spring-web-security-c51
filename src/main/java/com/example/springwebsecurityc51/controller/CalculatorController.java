package com.example.springwebsecurityc51.controller;

import com.example.springwebsecurityc51.converter.OperationDTOConverter;
import com.example.springwebsecurityc51.dto.OperationDTO;
import com.example.springwebsecurityc51.entity.Operation;
import com.example.springwebsecurityc51.entity.User;
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

    private static final String PATH_CALCULATOR = "calculator/calc";
    public static final String PATH_HISTORY = "calculator/history";

    private final OperationService operationService;
    private final СalculatorService сalculatorService;
    private final OperationDTOConverter operationDTOConverter;
    private final UserService userService;

    public CalculatorController(OperationService operationService,
                                OperationDTOConverter operationDTOConverter,
                                СalculatorService сalculatorService,
                                UserService userService) {
        this.operationService = operationService;
        this.operationDTOConverter = operationDTOConverter;
        this.сalculatorService = сalculatorService;
        this.userService = userService;
    }

    @GetMapping
    public String calc(@ModelAttribute("calcOperation") Operation operation) {
        return PATH_CALCULATOR;
    }

    @PostMapping
    public String result(@Valid @ModelAttribute("calcOperation") OperationDTO operationDTO,
                         BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return PATH_CALCULATOR;
        }

        User user = userService.findByUsername(userService.getCurrentUsername()).get();
        Operation operation = operationDTOConverter.operationDTOtoOperation(operationDTO);
        operation.setResult(сalculatorService.getResult(operation));
        operation.setUser(user);
        operationService.save(operation);

        model.addAttribute("msgResult", operation.getResult());

        return PATH_CALCULATOR;
    }

    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("userHistory", operationService.findAllByUser());

        return PATH_HISTORY;
    }
}

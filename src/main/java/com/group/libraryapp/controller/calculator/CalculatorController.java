package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @GetMapping("/add") // GET /add
    public int AddTwoNumbers(CalculatorAddRequest request){
        return request.getNum1() + request.getNum2();
    }

    @PostMapping("/multiply") // 아래에 오는 함수를 HTTP 메소드가 POST 이고,

    public int MulTwoNumbers(@RequestBody CalculatorAddRequest request){
        return request.getNum1() * request.getNum2();
    }
}

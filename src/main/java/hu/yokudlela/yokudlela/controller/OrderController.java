package hu.yokudlela.yokudlela.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("order")
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class OrderController {

}

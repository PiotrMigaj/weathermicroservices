package pl.migibud.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/message")
class MessageController {

    @Value("${spring.boot.message}")
    private String message;

    @GetMapping
    ResponseEntity<String> getMessage(){
        return ResponseEntity.ok(message);
    }
}

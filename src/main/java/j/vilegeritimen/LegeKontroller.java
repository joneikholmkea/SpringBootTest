package j.vilegeritimen;

import j.vilegeritimen.security.model.User;
import j.vilegeritimen.security.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LegeKontroller {

    private IUserService userService;

    public LegeKontroller(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sjov")
    public ResponseEntity<Map<String,String>> sjov(){
        Map<String,String> map = new HashMap<>();
        map.put("message", "metoden /sjov kaldt");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/addUser")
    public ResponseEntity<Map<String,String>> addUser(@RequestBody User user){
        Map<String,String> map = new HashMap<>();
        map.put("message", "du kan godt");
        if(user.getUsername().length()>0) {
            userService.save(user);
            return ResponseEntity.ok(map);
        }
        else {
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
}

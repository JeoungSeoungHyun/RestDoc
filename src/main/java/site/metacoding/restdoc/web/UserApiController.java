package site.metacoding.restdoc.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.metacoding.restdoc.domain.User;

// save -> post
// deleteById -> delete
// updateById -> put
// findById -> get
// findAll -> get
@RequestMapping("/api") // 주소 앞에 api가 붙게 해주는 설정
@RestController
public class UserApiController {

    @PostMapping("/user")
    public ResponseEntity<?> save(@RequestBody User user) {
        // userService.회원가입(user)
        User userEntity = User.builder()
                .id(1)
                .username("ssar")
                .password("1234")
                .email("ssar@nate.com")
                .build();

        return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findById() {
        // userService.유저찾기(userId)
        User userEntity = User.builder()
                .id(1)
                .username("ssar")
                .password("1234")
                .email("ssar@nate.com")
                .build();
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> findAll() {
        // userService.전체유저찾기
        User userEntity = User.builder()
                .id(1)
                .username("ssar")
                .password("1234")
                .email("ssar@nate.com")
                .build();
        User userEntity2 = User.builder()
                .id(1)
                .username("ssar")
                .password("1234")
                .email("ssar@nate.com")
                .build();
        List<User> list = new ArrayList<>();
        list.add(userEntity);
        list.add(userEntity2);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

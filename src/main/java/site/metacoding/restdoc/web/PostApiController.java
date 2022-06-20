package site.metacoding.restdoc.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.metacoding.restdoc.domain.Post;

@RequestMapping("/api") // 주소 앞에 api가 붙게 해주는 설정
@RestController
public class PostApiController {

    // 게시글 쓰기
    @PostMapping("/post")
    public ResponseEntity<?> write(@RequestBody Post post) {
        Post postEntity = Post.builder()
                .id(1)
                .title("제목1")
                .content("내용1")
                .build();

        return new ResponseEntity<>(postEntity, HttpStatus.CREATED);
    }

    // 게시글 상세보기
    @GetMapping("/post/{postId}")
    public ResponseEntity<?> findById(@PathVariable Integer postId) {
        Post postEntity = Post.builder()
                .id(1)
                .title("제목1")
                .content("내용1")
                .build();
        return new ResponseEntity<>(postEntity, HttpStatus.OK);
    }

    // 게시글 전체보기
    @GetMapping("/posts")
    public ResponseEntity<?> findAll() {
        Post postEntity = Post.builder()
                .id(1)
                .title("제목1")
                .content("내용1")
                .build();
        Post postEntity2 = Post.builder()
                .id(2)
                .title("제목2")
                .content("내용2")
                .build();
        List<Post> list = new ArrayList<>();
        list.add(postEntity);
        list.add(postEntity2);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

package site.metacoding.restdoc.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.restdoc.AbstractControllerTest;
import site.metacoding.restdoc.domain.Post;
import site.metacoding.restdoc.util.keystore.MediaTypeImpl;

@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@SpringBootTest
public class PostApiControllerTest extends AbstractControllerTest {

    @Test
    public void save_test() throws Exception {
        // given
        String content = new ObjectMapper().writeValueAsString(
                Post.builder()
                        .id(1)
                        .title("제목1")
                        .content("내용1")
                        .build());

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/post")
                .content(content)
                .contentType(MediaTypeImpl.APPLICATION_JSON_UTF8));

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("제목1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("내용1"))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }

    @Test
    public void find_one_test() throws Exception {
        // given
        Integer postId = 1;

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/post/" + postId));

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("제목1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("내용1"))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }

    @Test
    public void find_all_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/posts"));

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].title").value("제목1"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].content").value("내용1"))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].title").value("제목2"))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].content").value("내용2"))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }
}

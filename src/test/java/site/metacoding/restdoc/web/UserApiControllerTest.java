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
import site.metacoding.restdoc.domain.User;
import site.metacoding.restdoc.util.keystore.MediaTypeImpl;

// @AutoConfigureMockMvc // autowired가 가능하게 해주는 어노테이션? // extends 한 거에 있기 때문에 필요가 없어진다.
// @ExtendWith({SpringExtension.class,RestDocumentationExtension.class})가 아래 두개
// 어노테이션을 합친것이다.
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@SpringBootTest
public class UserApiControllerTest extends AbstractControllerTest {

        // extends하게 되면 필요가 없어진다.
        // @Autowired
        // private MockMvc mockMvc;

        @Test
        public void save_test() throws Exception {
                // given
                String content = new ObjectMapper().writeValueAsString(
                                User.builder()
                                                .username("ssar")
                                                .password("1234")
                                                .email("ssar@nate.com")
                                                .build());

                // when
                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                                .post("/api/user")
                                .content(content)
                                .contentType(MediaTypeImpl.APPLICATION_JSON_UTF8));

                // then
                resultActions
                                .andExpect(MockMvcResultMatchers.status().isCreated())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("ssar"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("1234"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("ssar@nate.com"))
                                .andDo(MockMvcResultHandlers.print())
                                // .andDo(MockMvcRestDocumentation.document("/post/api/user"));
                                .andDo(document);
        }

        @Test
        public void find_one_test() throws Exception {
                // given
                Integer userId = 1;

                // when
                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                                .get("/api/user/" + userId));

                // then
                resultActions
                                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("ssar"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("1234"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("ssar@nate.com"))
                                .andDo(MockMvcResultHandlers.print())
                                // .andDo(MockMvcRestDocumentation.document("/get/api/user"));
                                .andDo(document);
        }

        @Test
        public void find_all_test() throws Exception {
                // given

                // when
                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                                .get("/api/users"));
                // then
                resultActions
                                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(1))
                                .andExpect(MockMvcResultMatchers.jsonPath("[0].username").value("ssar"))
                                .andExpect(MockMvcResultMatchers.jsonPath("[0].password").value("1234"))
                                .andExpect(MockMvcResultMatchers.jsonPath("[0].email").value("ssar@nate.com"))
                                .andExpect(MockMvcResultMatchers.jsonPath("[1].id").value(1))
                                .andExpect(MockMvcResultMatchers.jsonPath("[1].username").value("ssar"))
                                .andExpect(MockMvcResultMatchers.jsonPath("[1].password").value("1234"))
                                .andExpect(MockMvcResultMatchers.jsonPath("[1].email").value("ssar@nate.com"))
                                .andDo(MockMvcResultHandlers.print())
                                // .andDo(MockMvcRestDocumentation.document("/get/api/users"));
                                .andDo(document);
        }
}

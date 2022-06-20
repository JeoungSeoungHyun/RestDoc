package site.metacoding.restdoc;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

// 폴더를 자동으로 생성되게 해주기 위해 생성한 추상클래스

@ExtendWith({ SpringExtension.class, RestDocumentationExtension.class })
public abstract class AbstractControllerTest {
        protected MockMvc mockMvc;

        protected RestDocumentationResultHandler document;

        @BeforeEach
        private void setup(WebApplicationContext webApplicationContext,
                        RestDocumentationContextProvider restDocumentation) {

                this.document = MockMvcRestDocumentation.document("{class-name}/{method-name}",
                                // 이쁘게 나오게 해주는 코드
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));

                mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                                // 시큐리티 설정
                                // .apply(SecurityMockMvcConfigurers.springSecurity())
                                .alwaysDo(document)
                                .build();

        }
}
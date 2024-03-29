package study.test.practice.test.testdouble.fake;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import study.test.practice.domain.user.entity.User;
import study.test.practice.test.configuration.configuration.AbstractTestConfiguration;
import study.test.practice.web.user.presentation.UserController;
import study.test.practice.web.user.presentation.dto.request.UserSignupRequest;
import study.test.practice.web.user.presentation.dto.response.UserSignupResponse;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class FakeUserControllerAbstractTest extends AbstractTestConfiguration {

    @Mock
    private FakeUserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Fake로 의존성을 대체할 수 있다.")
    void FAKE_의존성_대체_테스트() throws Exception {
        // given
        UserSignupRequest request = new UserSignupRequest("devjun");
        User newUser = new User("devjun");
        User result = new User(1L, "devjun");
        UserSignupResponse response = new UserSignupResponse(result);

        doReturn(result)
                .when(userService).save(newUser);

        // when
        ResultActions resultActions = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("userId", response.getUserId()).exists())
                .andDo(print());
    }
}

package ai.cloud.lab.transcript;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.PrintingResultHandler;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { SpeechWebhook.class })
class SpeechWebhookTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldHandleValidationToken() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/speech-webhook")
                .param("validationToken", "test-validation-token");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("test-validation-token"));
    }
}

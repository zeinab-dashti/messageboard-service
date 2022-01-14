package api;

import com.fsecure.messageboard.MessageboardApplication;
import com.fsecure.messageboard.persistence.MessageRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static util.TestUtil.*;
import static util.Fixtures.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = MessageboardApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class CreateMessageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MessageRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    private final String createMessageEndpoint = "/messageboard/message";

    @Test
    public void givenValidMessage_whenCreateMessage_thenStatus201() throws Exception {
        mvc.perform(post(createMessageEndpoint)
                .content(toJSON(validMessageOne()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(toJSON(validMessageOne())));
    }

    @Test
    public void givenTooShortTitleMessage_whenCreateMessage_thenStatus400() throws Exception {
        mvc.perform(post(createMessageEndpoint)
                .content(toJSON(messageTitleTooShort()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(hasErrors("Please enter at least 3 and at most 15 characters for title.")));
    }

    @Test
    public void givenTooLongTitleMessage_whenCreateMessage_thenStatus400() throws Exception {
        mvc.perform(post(createMessageEndpoint)
                .content(toJSON(messageTitleTooLong()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(hasErrors("Please enter at least 3 and at most 15 characters for title.")));
    }

    @Test
    public void givenInvalidURLMessage_whenCreateMessage_thenStatus400() throws Exception {
         mvc.perform(post(createMessageEndpoint)
                .content(toJSON(messageInvalidURL()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(hasErrors("URL is not valid.")));
    }

    @Test
    public void givenTwoInvalidItems_whenCreateMessage_thenStatus400AndReturnTwoErrors() throws Exception {
         mvc.perform(post(createMessageEndpoint)
                .content(toJSON(messageInvalid2Items()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(hasErrors(
                        "Please enter at least 3 and at most 300 characters for content.",
                        "Please enter at least 3 and at most 30 characters for sender.")));
    }
}

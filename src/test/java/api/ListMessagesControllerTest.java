package api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.hasSize;

import com.fsecure.messageboard.MessageboardApplication;
import com.fsecure.messageboard.api.dto.MessageV1;
import com.fsecure.messageboard.api.dto.MessageV2;
import com.fsecure.messageboard.exception.InvalidVersionException;
import com.fsecure.messageboard.exception.UnsupportedMediaTypeException;
import com.fsecure.messageboard.model.MessageEntity;
import com.fsecure.messageboard.persistence.MessageRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.is;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static util.TestUtil.*;
import static util.Fixtures.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MessageboardApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class ListMessagesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MessageRepository repository;

    @Autowired
    private ModelMapper messageMapper;

    private final String listMessagesEndpoint = "/messageboard/message";

    private List<MessageV1> listMessagesVersion1 = new ArrayList<>();
    private List<MessageV2> listMessagesVersion2 = new ArrayList<>();

    @Before
    public void setup() {
        Arrays.asList(validMessageOne(), validMessageTwo())
                .forEach(message -> {
                    repository.save(messageMapper.map(message, MessageEntity.class));
                    listMessagesVersion1.add(fromV2(message));
                    listMessagesVersion2.add(message);
                });
    }

    @After
    public void resetDb() {
        repository.deleteAll();
    }

    @AfterAll
    public static void tearDown() {}

    @Test
    public void givenAcceptJSONVersionV1_whenListMessages_thenStatus200JSON() throws Exception {
        mvc.perform(get(listMessagesEndpoint).param("version", "v1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(toJSON(listMessagesVersion1)));
    }

    @Test
    public void givenAcceptXMLVersionV1_whenListMessages_thenStatus200JSON() throws Exception {
        mvc.perform(get(listMessagesEndpoint).param("version", "v1")
                .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(toJSON(listMessagesVersion1)));
    }

    @Test
    public void givenAcceptUnsupportedTypeVersionV1_whenListMessages_thenStatus415() throws Exception {
        mvc.perform(get(listMessagesEndpoint).param("version", "v1")
                .accept(MediaType.APPLICATION_ATOM_XML))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedMediaTypeException))
                .andExpect(result -> assertEquals(
                        result.getResolvedException().getMessage(),
                        "Accept header media type not supported"));
    }

    @Test
    public void givenAcceptJSONVersionV2_whenListMessages_thenStatus200JSON() throws Exception {
        mvc.perform(get(listMessagesEndpoint).param("version", "v2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(toJSON(listMessagesVersion2)));

    }

    @Test
    public void givenAcceptXMLVersionV2_whenListMessages_thenStatus200XML() throws Exception {
        mvc.perform(get(listMessagesEndpoint).param("version", "v2")
                .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(content().xml(toXML(listMessagesVersion2)));
    }

    @Test
    public void givenAcceptUnsupportedTypeVersionV2_whenListMessages_thenStatus415() throws Exception {
        mvc.perform(get(listMessagesEndpoint).param("version", "v2")
                .accept(MediaType.APPLICATION_ATOM_XML))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedMediaTypeException))
                .andExpect(result -> assertEquals(
                        result.getResolvedException().getMessage(),
                        "Accept header media type not supported"));
    }

    @Test
    public void givenInvalidVersion_whenListMessages_thenStatus400() throws Exception {
        mvc.perform(get(listMessagesEndpoint).param("version", "v3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidVersionException))
                .andExpect(result -> assertEquals(
                        result.getResolvedException().getMessage(),
                        "Only v1 or v2 is valid"));
    }
}

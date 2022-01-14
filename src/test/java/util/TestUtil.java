package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsecure.messageboard.api.dto.MessageV1;
import com.fsecure.messageboard.api.dto.MessageV2;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtil {

    public static String hasErrors(String... errors) throws JsonProcessingException {
        Map errorsMap = new HashMap();
        errorsMap.put("errors", errors);
        return toJSON(errorsMap);
    }

    public static MessageV1 fromV2(MessageV2 messageV2) {
        MessageV1 messageV1 = new MessageV1();
        messageV1.setTitle(messageV2.getTitle());
        messageV1.setContent(messageV2.getContent());
        messageV1.setSender(messageV2.getSender());

        return messageV1;
    }

    public static String toJSON(Object object) throws JsonProcessingException {
        return new ObjectMapper()
                .writer()
                .withDefaultPrettyPrinter()
                .writeValueAsString(object);
    }

    public static String toXML(List<MessageV2> messages) throws JAXBException {

        String xmlString = "<ArrayList>";
        for(MessageV2 message : messages) {
            xmlString+="<item>";
            xmlString+=String.format("<title>%s</title>", message.getTitle());
            xmlString+=String.format("<content>%s</content>", message.getContent());
            xmlString+=String.format("<sender>%s</sender>", message.getSender());
            xmlString+=String.format("<url>%s</url>", message.getUrl());
            xmlString+="</item>";
        }
        xmlString+="</ArrayList>";
        return xmlString;
    }
}

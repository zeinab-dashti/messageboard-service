package util;

import com.fsecure.messageboard.api.dto.MessageV1;
import com.fsecure.messageboard.api.dto.MessageV2;

import java.util.Arrays;
import java.util.List;

public class Fixtures {

    public static MessageV2 validMessageOne(){
        MessageV2 messageV2 = new MessageV2();
        messageV2.setSender("F-Secure employee");
        messageV2.setContent("My awesome message content");
        messageV2.setTitle("My title");
        messageV2.setUrl("http://www.f-secure.com");

        return messageV2;
    }

    public static MessageV2 validMessageTwo(){
        MessageV2 messageV2 = new MessageV2();
        messageV2.setSender("F-Secure employee - two");
        messageV2.setContent("My awesome message content - two");
        messageV2.setTitle("My title");
        messageV2.setUrl("https://stackoverflow.com");

        return messageV2;
    }

    public static MessageV2 messageInvalidURL(){
        MessageV2 messageV2 = new MessageV2();
        messageV2.setSender("F-Secure employee");
        messageV2.setContent("My awesome message content");
        messageV2.setTitle("My title");
        messageV2.setUrl("abc.defg");

        return messageV2;
    }

    public static MessageV2 messageNullTitle(){
        MessageV2 messageV2 = new MessageV2();
        messageV2.setSender("F-Secure employee");
        messageV2.setContent("My awesome message content");
        messageV2.setUrl("http://www.f-secure.com");

        return messageV2;
    }

    public static MessageV2 messageTitleTooShort(){
        MessageV2 messageV2 = new MessageV2();
        messageV2.setSender("F-Secure employee");
        messageV2.setContent("My awesome message content");
        messageV2.setTitle("A");
        messageV2.setUrl("http://www.f-secure.com");

        return messageV2;
    }

    public static MessageV2 messageTitleTooLong(){
        MessageV2 messageV2 = new MessageV2();
        messageV2.setSender("F-Secure employee");
        messageV2.setContent("My awesome message content");
        messageV2.setTitle("My awesome title");
        messageV2.setUrl("http://www.f-secure.com");

        return messageV2;
    }

    public static MessageV2 messageInvalid2Items(){
        MessageV2 messageV2 = new MessageV2();
        messageV2.setSender("me");
        messageV2.setContent("My");
        messageV2.setTitle("My title");
        messageV2.setUrl("http://www.f-secure.com");

        return messageV2;
    }
}

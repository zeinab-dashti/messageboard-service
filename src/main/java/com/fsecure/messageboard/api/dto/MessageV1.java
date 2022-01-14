package com.fsecure.messageboard.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class MessageV1 implements Serializable {

    @Size(min = 3, max = 15, message = "{validation.message.title.length}")
    @ApiModelProperty(notes = "Message title")
    private String title;

    @Size(min = 3, max = 300, message = "{validation.message.content.length}")
    @ApiModelProperty(notes = "Message content")
    private String content;

    @Size(min = 3, max = 30, message = "{validation.message.sender.length}")
    @ApiModelProperty(notes = "Message sender")
    private String sender;

}

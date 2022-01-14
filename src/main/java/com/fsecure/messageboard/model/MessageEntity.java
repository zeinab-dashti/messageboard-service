package com.fsecure.messageboard.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TBL_MESSAGE")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String content;

    @NonNull
    private String sender;

    @NonNull
    private String url;

    public MessageEntity(Long id, String title, String content, String sender) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sender = sender;
    }

    public MessageEntity(String title, String content, String sender, String url) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.url = url;
    }
}

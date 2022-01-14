package com.fsecure.messageboard.persistence;

import com.fsecure.messageboard.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    @Query("SELECT new MessageEntity(m.id, m.title, m.content, m.sender) FROM MessageEntity m")
    List<MessageEntity> findAllMessagesV1();
}

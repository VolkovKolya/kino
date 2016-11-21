package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.dao.MessageDao;
import ru.kpfu.itis.group501.volkov.entities.Message;

import java.util.List;

/**
 * Created by volkov on 18.11.2016.
 */
public class MessageService implements MessageServiceInterface {

    private MessageDao messageDao = null;
    private Error error = null;
    public MessageService() {
        this.messageDao = new MessageDao();
    }
    @Override
    public void add(String text, String user_id, String from_user_id, String data) {
        messageDao.addMessage(new Message(text, user_id,from_user_id, data));
    }

    @Override
    public void delete(String id) {
        messageDao.deleteMessage(id);
    }

    @Override
    public List<Message> findId(String user_id, String from_user_id) {
        return messageDao.findMessageId(user_id,from_user_id);
    }

    public List<Message> findOneId(String user_id) {
        return messageDao.findOneId(user_id);
    }

    @Override
    public Error getError() {
        return null;
    }
}

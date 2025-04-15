package com.chatapp.Springboot_webapp;

public class ChatMessagePojo {
    private MessageType messageType;
    private String content;
    private String sender;

    //  enum is a special class that represents the group of the constant variables
    public enum MessageType{

        // these are the constant varialbles

        CHAT,
        JOIN,
        LEAVE
    }

    public MessageType getMessageType(MessageType leave) {
        return messageType;
    }

    public void setType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
}

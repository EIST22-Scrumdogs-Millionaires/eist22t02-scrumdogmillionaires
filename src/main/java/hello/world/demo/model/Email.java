package hello.world.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Email {

    private String to;

    private String subject;

    private String text;

    private LocalDate sendDate;

    private LocalTime sendTime;

    public Email() {
    }

    public Email(String to, String subject, String text, LocalDate sendDate, LocalTime sendTime) {
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.sendDate = sendDate;
        this.sendTime = sendTime;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public LocalTime getSendTime() {
        return sendTime;
    }
}

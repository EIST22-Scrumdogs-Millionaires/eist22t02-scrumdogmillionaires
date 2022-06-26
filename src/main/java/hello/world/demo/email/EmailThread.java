package hello.world.demo.email;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import hello.world.demo.Data;
import hello.world.demo.restaurant.Email;

public class EmailThread extends Thread {

    private static List<Email> toSend = Data.getEmails();

    private static final int UPDATE_TIME = 1_000;

    public static void addEmail(Email e) {
        toSend.add(e);
        Data.saveEmails(toSend);
    }

    @Override
    public void run() {
        while (true) {
            List<Email> toRemove = new ArrayList<>();

            toSend.stream().filter(x -> {
                        LocalDateTime combined = LocalDateTime.of(x.getSendDate(),x.getSendTime());
                        return (ChronoUnit.HOURS.between(combined,LocalDateTime.now())<= 12);
                    }).limit(2)
                    .forEach(x -> {
                        EmailServiceImpl.sendMail(x.getTo(), x.getSubject(), x.getText());
                        toRemove.add(x);
                    });
            toSend.removeAll(toRemove);
            Data.saveEmails(toSend);
            try {
                Thread.sleep(UPDATE_TIME);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

}

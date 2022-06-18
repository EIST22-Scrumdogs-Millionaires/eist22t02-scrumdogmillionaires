package hello.world.demo.email;

import java.util.ArrayList;
import java.util.List;

import hello.world.demo.restaurant.Email;

public class EmailThread extends Thread {

    private static List<Email> toSend = new ArrayList<>();

    private static final int UPDATE_TIME = 1_000;

    public static void addEmail(Email e) {
        toSend.add(e);
    }

    @Override
    public void run() {
        while (true) {
            List<Email> toRemove = new ArrayList<>();

            toSend.stream().forEach(x->EmailServiceImpl.sendMail(x.getTo(), x.getSubject(), x.getText()));

            toSend.removeAll(toRemove);
            try {
                Thread.sleep(UPDATE_TIME);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }

    }

}

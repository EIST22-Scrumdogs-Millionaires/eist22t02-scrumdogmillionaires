package hello.world.demo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Util {

        public static List<LocalTime> getLocalTimeList(LocalTime mon, LocalTime tue, LocalTime wen, LocalTime thu,
                        LocalTime fri, LocalTime sat, LocalTime sun) {
                List<LocalTime> times = new ArrayList<>();
                times.add(mon);
                times.add(tue);
                times.add(wen);
                times.add(thu);
                times.add(fri);
                times.add(sat);
                times.add(sun);
                return times;
        }


        //Parameter übergeben
        public static String reservationMail(){
                return null;
        }

}

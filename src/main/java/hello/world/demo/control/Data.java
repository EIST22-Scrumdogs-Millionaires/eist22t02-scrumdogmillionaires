package hello.world.demo.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import hello.world.demo.model.Email;
import hello.world.demo.model.Restaurant;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;

public class Data {

        private static final String RESTAURANTS_PATH = "src/main/java/hello/world/demo/data/restaurants.json";
        private static final String EMAIL_PATH = "src/main/java/hello/world/demo/data/email.json";
        private static final String RESERVATION_ID_PATH = "src/main/java/hello/world/demo/data/reservation_id.json";

        public static List<Restaurant> getRestaurants() {
                List<Restaurant> ret = new ArrayList<>();
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                try {
                        ret = mapper.readValue(new File(RESTAURANTS_PATH), new TypeReference<List<Restaurant>>() {
                        });
                } catch (IOException e) {
                        e.printStackTrace();
                }

                return ret;
        }

        public static void saveRestaurants(List<Restaurant> toSave) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                try {
                        mapper.writeValue(new File(RESTAURANTS_PATH), toSave);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public static List<Email> getEmails() {
                List<Email> ret = new ArrayList<>();
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                try {
                        ret = mapper.readValue(new File(EMAIL_PATH), new TypeReference<List<Email>>() {
                        });
                } catch (IOException e) {
                        e.printStackTrace();
                }

                return ret;
        }

        public static void saveEmails(List<Email> toSave) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                try {
                        mapper.writeValue(new File(EMAIL_PATH), toSave);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public static int getReservationId() {
                int ret = 0;
                ObjectMapper mapper = new ObjectMapper();
                try {
                        ret = mapper.readValue(new File(RESERVATION_ID_PATH), Integer.class);

                } catch (IOException e) {
                        e.printStackTrace();
                }
                return ret;
        }

        public static void saveReservationId(Integer toSave) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                        mapper.writeValue(new File(RESERVATION_ID_PATH), toSave);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

}

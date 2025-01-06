package models;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AddBookModel {

    public String addBookBody(String userId) {
         String body2 = "{\n" +
                "  \"userId\": \"" + userId + "\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \"9781449325862\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        return body2;
    }
}

package jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonCreatorExample {
    public static void main(String[] args) throws IOException {
        String json = "{\"id\":1,\"theName\":\"My bean\"}";
        BeanWithCreator bean = new ObjectMapper()
                .reader(BeanWithCreator.class)
                .readValue(json);
        // TODO: Adnotacje pozwalają na deserializację jsona na obiekt, którego zmienne mają inne nazwy
        System.out.println(bean.id);
        System.out.println(bean.name);
    }
}

class BeanWithCreator {
    public int id;
    public String name;

    @JsonCreator
    public BeanWithCreator(
            @JsonProperty("id") int id,
            @JsonProperty("theName") String name) {
        this.id = id;
        this.name = name;
    }
}

package jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonGetterExample {
    public static void main(String[] args) throws JsonProcessingException {
        MyBean bean = new MyBean(1, "My bean");

        String result = new ObjectMapper().writeValueAsString(bean);

        System.out.println(result);
    }
}

class MyBean {
    public int id;
    private String name;

    public MyBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonGetter("nameExample")
    public String getTheName() {
        return name;
    }
}
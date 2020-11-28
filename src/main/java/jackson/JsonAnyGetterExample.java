package jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonAnyGetterExample {
    public static void main(String[] args) throws JsonProcessingException {
        ExtendableBean bean = new ExtendableBean("Bean 1");
        bean.add("atr1", "val1");
        bean.add("atr2", "val2");

        String result = new ObjectMapper().writeValueAsString(bean);
        System.out.println(result);
    }
}

class ExtendableBean {
    public String name;
    private Map<String, String> properties;

    public ExtendableBean(String name) {
        this.name = name;
        properties = new HashMap<String, String>();
    }

    public void add(String key, String value){
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }
}
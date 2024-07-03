package org.json.objectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DeserializeJavaObject {
/*
deserialize JSON content into a Java object
 */
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper=new ObjectMapper();
        JacksonObjectMapper car=mapper.readValue(new File("./car.json"),JacksonObjectMapper.class);
        System.out.println(car.getColor());
        System.out.println(car.getType());
    }
}

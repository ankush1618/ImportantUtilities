package org.json.objectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class SerializeJavaObject {

/*
Java Object to JSON
Let’s see a first example of serializing a Java object into
JSON using the writeValue method of the ObjectMapper class:
 */

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        JacksonObjectMapper car=new JacksonObjectMapper("Grey","Bentley");
        mapper.writeValue(new File("./car.json"),car);

        String strVal=mapper.writeValueAsString(car);
        System.out.println(strVal);
    }
}
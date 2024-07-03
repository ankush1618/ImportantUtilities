package org.json.objectMapper;

public class JacksonObjectMapper {

    private String color;
    private String type;

    public JacksonObjectMapper(){

    }
    public JacksonObjectMapper(String color,String type){
        this.color=color;
        this.type=type;
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color=color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "JacksonObjectMapper{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

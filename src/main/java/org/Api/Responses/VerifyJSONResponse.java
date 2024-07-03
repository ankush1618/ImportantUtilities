package org.Api.Responses;



import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class VerifyJSONResponse {

    public static void main(String[] args) {
        String jsonResponse = "{\n" +
                "  \"posts\": [\n" +
                "    { \"id\": \"1\", \"title\": \"a title\", \"views\": 100 },\n" +
                "    { \"id\": \"2\", \"title\": \"another title\", \"views\": 200 }\n" +
                "  ],\n" +
                "  \"comments\": [\n" +
                "    { \"id\": \"1\", \"text\": \"a comment about post 1\", \"postId\": \"1\" },\n" +
                "    { \"id\": \"2\", \"text\": \"another comment about post 1\", \"postId\": \"1\" }\n" +
                "  ],\n" +
                "  \"profile\": {\n" +
                "    \"name\": \"typicode\"\n" +
                "  }\n" +
                "}";

        // Parse JSON response
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonResponse).getAsJsonObject();

        // Verify "posts" array
        JsonArray postsArray = jsonObject.getAsJsonArray("posts");
        System.out.println("Number of posts: " + postsArray.size());
        System.out.println("Verify posts: " + postsArray);
        System.out.println("Verify posts Id: " + postsArray.get(0).getAsJsonObject().get("id"));
        System.out.println("Verify posts Id: " + postsArray.get(1).getAsJsonObject().get("id"));

        // Add more assertions as needed for each post

        // Verify "comments" array
        JsonArray commentsArray = jsonObject.getAsJsonArray("comments");
        System.out.println("Number of comments: " + commentsArray.size());
        // Add more assertions as needed for each comment

        // Verify "profile" object
        JsonObject profileObject = jsonObject.getAsJsonObject("profile");
        System.out.println("Profile name: " + profileObject.get("name").getAsString());
        // Add more assertions as needed for the profile
    }
}


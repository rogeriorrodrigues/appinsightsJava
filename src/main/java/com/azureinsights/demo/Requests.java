package com.azureinsights.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class Requests {
    public static void headrequest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(URI.create("http://webcode.me"))
                .method("HEAD", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<Void> response = client.send(request,
                HttpResponse.BodyHandlers.discarding());

        HttpHeaders headers = response.headers();

        headers.map().forEach((key, values) -> {
            System.out.printf("%s: %s%n", key, values);
        });

    }
    public static void getRequest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://webcode.me"))
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    public static void PostRequest() throws IOException, InterruptedException {
        var values = new HashMap<String, String>() {{
            put("name", "John Doe");
            put ("occupation", "gardener");
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    public static void getRequestSW() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://swapi.dev/api/films/1/"))
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        System.out.println("Star Wars Movie:" + response.body());
    }

}

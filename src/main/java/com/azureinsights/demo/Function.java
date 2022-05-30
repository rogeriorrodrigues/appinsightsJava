package com.azureinsights.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Function {

    public static void funcapp() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://azfuncapp.azurewebsites.net/api/HttpTrigger?code=l/G827SOU/rMMzHwo8arIqka1ttqx/zE2VjaQt1xS9Jv0ICbMxuhSQ=="))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}

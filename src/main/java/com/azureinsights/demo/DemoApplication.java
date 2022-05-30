package com.azureinsights.demo;

import com.microsoft.applicationinsights.TelemetryClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;

@SpringBootApplication
public class DemoApplication {

    private static String subscriptionKey = "d66602cd458f42b1bccdf6a4fffbe8c5";
    private static String endpoint = "https://customvisiongeov-prediction.cognitiveservices.azure.com/customvision/v3.0/Prediction/b1a9d088-c7e3-4825-bc29-81060296feb2/classify/iterations/Iteration1%20-%20Published/url";

    private static final String uriBase = endpoint;
    private static final String imageToAnalyze = "https://i0.wp.com/3rlab.com.br/wp-content/uploads/2020/10/Ferrugem-do-cafeeiro.jpg.png";

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(DemoApplication.class, args);

        long startTime = System.currentTimeMillis();

        TelemetryClient telemetryClient = new TelemetryClient();
        telemetryClient.trackEvent("The Demo application has Started");
        telemetryClient.flush();

        Requests.headrequest();
        Requests.getRequest();
        Requests.PostRequest();


        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            URIBuilder builder = new URIBuilder(uriBase);

            // Request parameters. All of them are optional.
            builder.setParameter("visualFeatures", "Categories,Description,Color");

            // Prepare the URI for the REST API method.
            URI uri = builder.build();
            HttpPost request1 = new HttpPost(uri);

            // Request headers.
            request1.setHeader("Content-Type", "application/json");
            request1.setHeader("Prediction-Key", subscriptionKey);

            // Request body.
            StringEntity requestEntity =
                    new StringEntity("{\"url\":\"" + imageToAnalyze + "\"}");
            request1.setEntity(requestEntity);

            // Call the REST API method and get the response entity.
            HttpResponse response1 = httpClient.execute(request1);
            HttpEntity entity = response1.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
                System.out.println("REST Response:\n");
                System.out.println(json.toString(2));
            }
        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
        }

        //Function.funcapp();
        Requests.getRequestSW();

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);

        telemetryClient.trackMetric("CustomTimeExecution", elapsedTime);
        telemetryClient.flush();

        telemetryClient.trackEvent("Execution finished");
        telemetryClient.flush();

    }

}

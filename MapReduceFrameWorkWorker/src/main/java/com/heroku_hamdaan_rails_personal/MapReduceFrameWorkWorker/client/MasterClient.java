package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Response.RequestTaskResponse;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.CompletedTaskBody;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MasterClient {
    private static final String BASE_URI = "http://localhost:8080/";

    private final HttpClient httpClient;

    public MasterClient() {
        this.httpClient = HttpClient.newBuilder().build();
    }

    public RequestTaskResponse requestTask() throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(BASE_URI+"task");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).setHeader("Content-Type", "application/json").GET().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return new ObjectMapper().readValue(response.body(), RequestTaskResponse.class);
    }

    public HttpResponse notifyTaskCompleted(CompletedTaskBody requestBody) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(BASE_URI+"task");
        String jsonifiedRequest = new ObjectMapper().writeValueAsString(requestBody);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).setHeader("Content-Type", "application/json").POST(
                HttpRequest.BodyPublishers.ofString(jsonifiedRequest)
        ).build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}

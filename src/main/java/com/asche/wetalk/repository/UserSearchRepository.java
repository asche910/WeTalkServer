package com.asche.wetalk.repository;

import com.asche.wetalk.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public class UserSearchRepository {

    public UserSearchRepository() {
    }

    public void run() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        IndexRequest indexRequest = new IndexRequest("customer");

        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.toString());


    }
}

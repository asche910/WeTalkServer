package com.asche.wetalk;

import com.asche.wetalk.repository.UserSearchRepository;
import org.elasticsearch.action.get.GetResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) throws IOException {
        System.out.println("Test Start:...");

        UserSearchRepository searchRepository = new UserSearchRepository();
        searchRepository.run();
    }
}

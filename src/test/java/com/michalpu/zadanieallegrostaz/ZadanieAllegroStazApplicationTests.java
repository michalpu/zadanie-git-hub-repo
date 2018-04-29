package com.michalpu.zadanieallegrostaz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@ActiveProfiles(profiles = {"integration"})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZadanieAllegroStazApplicationTests {

    RestTemplate restTemplate = new RestTemplate();

    @Value("${gitHub.client.getRepos}")
    String getReposUrl;

    @Rule
    public WireMockRule gitHubClientRule = new WireMockRule(8089);

    public void stubGitHubClient(int statusCode, List<String> repoNames, List<String> repoUpdateDates) {
        List<Repo> repos = new ArrayList<>();
        for (int i = 0; i < repoNames.size(); i++) {
            repos.add(new Repo(repoNames.get(i), repoUpdateDates.get(i)));
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            gitHubClientRule.stubFor(get(urlEqualTo(getReposUrl))
                    .willReturn(aResponse()
                            .withStatus(statusCode)
                            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                            .withBody(
                                    mapper.writeValueAsString(repos)

                            )));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @LocalServerPort
    int port;

    @Test
    public void contextLoads() {
    }

    String localUrl(String path) {
        return "http://localhost:" + port + path;
    }

}

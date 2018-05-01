package com.michalpu.zadanieallegrostaz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GitHubVersionControlClientImpl implements VersionControlClient {
    private final String gitHubGetReposUrl;
    private RestTemplate restTemplate;

    public GitHubVersionControlClientImpl(RestTemplate gitHubRestTemplate, @Value("${gitHub.client.host}") String urlHost,
                                          @Value("${gitHub.client.getRepos}") String reposUrl) {
        this.restTemplate = gitHubRestTemplate;
        this.gitHubGetReposUrl = urlHost + reposUrl;
    }

    @Override
    public Repo[] getRepos() {
        Repo[] repos = restTemplate.getForObject(gitHubGetReposUrl, Repo[].class);
        return repos;
    }
}

package com.michalpu.zadanieallegrostaz;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GitHubVersionControlClientImpl implements VersionControlClient{
    private final String gitHubGetReposUrl;
    private RestTemplate restTemplate = new RestTemplate();

    public GitHubVersionControlClientImpl(final Environment environment) {
        this.gitHubGetReposUrl = environment.getProperty("gitHub.client.host") + environment.getProperty("gitHub.client.getRepos");
    }

    @Override
    public Repo[] getRepos() {
        Repo[] repos = restTemplate.getForObject(gitHubGetReposUrl, Repo[].class);
        return repos;
    }
}

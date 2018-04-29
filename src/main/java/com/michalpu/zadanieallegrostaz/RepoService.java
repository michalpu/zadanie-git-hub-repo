package com.michalpu.zadanieallegrostaz;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RepoService {

    private final VersionControlClient versionControlClient;

    public RepoService(VersionControlClient versionControlClient) {
        this.versionControlClient = versionControlClient;
    }

    public Repo getNewestRepo(){
        Repo[] repos = versionControlClient.getRepos();
        List<Repo> reposList = Arrays.asList(repos);
        reposList.sort(Collections.reverseOrder());
        return  reposList.get(0);
    }

    public List<Repo> getRepos(){
        Repo[] repos = versionControlClient.getRepos();
        List<Repo> reposList = Arrays.asList(repos);
        return reposList;
    }

    public List<Repo> getSortedRepos(){
        Repo[] repos = versionControlClient.getRepos();
        List<Repo> reposList = Arrays.asList(repos);
        reposList.sort(Collections.reverseOrder());
        return  reposList;
    }

}

package com.michalpu.zadanieallegrostaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RepoController {
    private final RepoService repoService;
    private final VersionControlClient versionControlClient;

    public RepoController(RepoService repoService, VersionControlClient versionControlClient) {
        this.repoService = repoService;
        this.versionControlClient = versionControlClient;
    }

    @GetMapping("/repos")
    public List<Repo> getRepos(){
        Repo[] repos = versionControlClient.getRepos();
        return repoService.getRepos(repos);
    }

    @GetMapping("/repos/sorted")
    public List<Repo> getSortedRepos(){
        Repo [] repos = versionControlClient.getRepos();
        return repoService.getSortedRepos(repos);
    }

    @GetMapping("/repos/newest")
    public Repo getNewestRepo(){
        Repo[] repos = versionControlClient.getRepos();
        return repoService.getNewestRepo(repos);
    }
}

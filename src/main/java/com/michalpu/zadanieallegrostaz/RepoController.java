package com.michalpu.zadanieallegrostaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RepoController {
    private final RepoService repoService;

    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping("/repos")
    public List<Repo> getRepos() {
        return repoService.getRepos();
    }

    @GetMapping("/repos/sorted")
    public List<Repo> getSortedRepos() {
        return repoService.getSortedRepos();
    }

    @GetMapping("/repos/newest")
    public Repo getNewestRepo() {
        return repoService.getNewestRepo();
    }

}

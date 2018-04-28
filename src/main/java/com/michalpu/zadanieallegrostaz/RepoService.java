package com.michalpu.zadanieallegrostaz;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RepoService {

    public Repo getNewestRepo(Repo[] repos){
        List<Repo> reposList = Arrays.asList(repos);
        reposList.sort(Collections.reverseOrder());
        return  reposList.get(0);
    }

    public List<Repo> getRepos(Repo[] repos){
        List<Repo> reposList = Arrays.asList(repos);
        return reposList;
    }

    public List<Repo> getSortedRepos(Repo[] repos){
        List<Repo> reposList = Arrays.asList(repos);
        reposList.sort(Collections.reverseOrder());
        return  reposList;
    }

}

package com.michalpu.zadanieallegrostaz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.Assert.*;

public class GitHubVersionControlClientImplTest extends ZadanieAllegroStazApplicationTests {

    @Autowired
    VersionControlClient versionControlClient;

    @Test
    public void shouldReturnArrayOfRepos() {
        //given
        String[] repoNames = {"akubra", "allegro-api", "allegro-tech-labs-iot"};
        String[] repoUpdateDates = {"2018-04-24T21:54:35Z", "2018-04-23T10:39:31Z", "2018-04-14T15:15:17Z"};
        stubGitHubClient(200, Arrays.asList(repoNames), Arrays.asList(repoUpdateDates));

        //when
        Repo[] repos = versionControlClient.getRepos();

        //then
        assertEquals(repos[0].getName(), "akubra");
        assertEquals(repos[1].getName(), "allegro-api");
        assertEquals(repos[2].getName(), "allegro-tech-labs-iot");
        assertEquals(repos[0].getUpdated_at(), "2018-04-24T21:54:35Z");
        assertEquals(repos[1].getUpdated_at(), "2018-04-23T10:39:31Z");
        assertEquals(repos[2].getUpdated_at(), "2018-04-14T15:15:17Z");

    }
}
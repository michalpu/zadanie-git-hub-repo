package com.michalpu.zadanieallegrostaz;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RepoControllerTest extends ZadanieAllegroStazApplicationTests {

    @Test
    public void shouldReturnNewestRepo() {
        //given
        String[] repoNames = {"akubra", "allegro-api", "allegro-tech-labs-iot"};
        String[] repoUpdateDates = {"2018-04-24T21:54:35Z", "2018-04-23T10:39:31Z", "2018-04-14T15:15:17Z"};
        stubGitHubClient(200, Arrays.asList(repoNames), Arrays.asList(repoUpdateDates));

        //when
        Repo forObject = restTemplate.getForObject(localUrl("repos/newest"), Repo.class);

        //then
        assertEquals(forObject.getName(), "akubra");
        assertEquals(forObject.getUpdated_at(), "2018-04-24T21:54:35Z");

    }

    @Test
    public void shouldReturnRepos() {
        //given
        String[] repoNames = {"akubra", "allegro-api", "allegro-tech-labs-iot"};
        String[] repoUpdateDates = {"2018-04-24T21:54:35Z", "2018-04-23T10:39:31Z", "2018-04-14T15:15:17Z"};
        stubGitHubClient(200, Arrays.asList(repoNames), Arrays.asList(repoUpdateDates));

        //when
        Repo[] forObject = restTemplate.getForObject(localUrl("repos"), Repo[].class);

        //then
        assertEquals(forObject[0].getName(), "akubra");
        assertEquals(forObject[0].getUpdated_at(), "2018-04-24T21:54:35Z");
        assertEquals(forObject[1].getName(), "allegro-api");
        assertEquals(forObject[1].getUpdated_at(), "2018-04-23T10:39:31Z");
        assertEquals(forObject[2].getName(), "allegro-tech-labs-iot");
        assertEquals(forObject[2].getUpdated_at(), "2018-04-14T15:15:17Z");

    }

    @Test
    public void shouldReturnSortedRepos() {
        //given
        String[] repoNames = {"allegro-api", "allegro-tech-labs-iot", "akubra"};
        String[] repoUpdateDates = {"2018-04-23T10:39:31Z", "2018-04-14T15:15:17Z", "2018-04-24T21:54:35Z"};
        stubGitHubClient(200, Arrays.asList(repoNames), Arrays.asList(repoUpdateDates));

        //when
        Repo[] forObject = restTemplate.getForObject(localUrl("repos/sorted"), Repo[].class);

        //then
        assertEquals(forObject[0].getName(), "akubra");
        assertEquals(forObject[0].getUpdated_at(), "2018-04-24T21:54:35Z");
        assertEquals(forObject[1].getName(), "allegro-api");
        assertEquals(forObject[1].getUpdated_at(), "2018-04-23T10:39:31Z");
        assertEquals(forObject[2].getName(), "allegro-tech-labs-iot");
        assertEquals(forObject[2].getUpdated_at(), "2018-04-14T15:15:17Z");

    }


}
package academy.devdojo.springboot.client;

import academy.devdojo.springboot.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity
                ("http://localhost:8080/animes/{id}", Anime.class, 11);

        log.info(entity);

        Anime object = new RestTemplate().getForObject
                ("http://localhost:8080/animes/{id}", Anime.class, 11);

        log.info(object);

        Anime[] animes = new RestTemplate().getForObject
                ("http://localhost:8080/animes/all", Anime[].class, 11);

        log.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange
                ("http://localhost:8080/animes/all", HttpMethod.GET, null
                        , new ParameterizedTypeReference<List<Anime>>() {
                });

        log.info(exchange.getBody());

        Anime kingdom = Anime.builder().name("kingdom").build();
        Anime kingdomSaved = new RestTemplate().postForObject("http://localhost:8080/animes", kingdom, Anime.class);

        log.info("saved anime {}", kingdomSaved);

        Anime samuraiChamploo = Anime.builder().name("Samurai Champloo").build();
        ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate().exchange
                ("http://localhost:8080/animes",
                        HttpMethod.POST,
                        new HttpEntity<>(samuraiChamploo, createJsonHeader()),
                        Anime.class);

        log.info("saved anime {}", samuraiChamplooSaved);

        Anime animeToBeUpdated = samuraiChamplooSaved.getBody();
        animeToBeUpdated.setName("Samurai Champloo 2");
        ResponseEntity<Void> samuraiChamplooUpdated = new RestTemplate().exchange
                ("http://localhost:8080/animes/",
                        HttpMethod.PUT,
                        new HttpEntity<>(samuraiChamploo, createJsonHeader()),
                        Void.class);

        log.info("updated anime {}", samuraiChamplooUpdated);

        ResponseEntity<Void> samuraiChamplooDelete = new RestTemplate().exchange
                ("http://localhost:8080/animes/{id}",
                        HttpMethod.DELETE,
                        null,
                        Void.class,
                        38);

        log.info("deleted anime {}", samuraiChamplooDelete);

    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}

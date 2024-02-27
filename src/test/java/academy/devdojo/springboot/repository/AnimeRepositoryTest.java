package academy.devdojo.springboot.repository;

import academy.devdojo.springboot.domain.Anime;
import academy.devdojo.springboot.util.AnimeCreator;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@DisplayName("Tests for Anime Repository")
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persists anime when Successful")
    void save_PersistAnime_WhenSucessful(){
        Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(createAnimeToBeSaved);
        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(createAnimeToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates anime when Successful")
    void save_UpdatesAnime_WhenSucessful(){
        Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(createAnimeToBeSaved);
        savedAnime.setName("Overlord");
        this.animeRepository.save(savedAnime);
        Anime animeUpdated = this.animeRepository.save(savedAnime);
        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(savedAnime.getName());
    }

    @Test
    @DisplayName("Delete removes anime when Successful")
    void delete_RemovesAnime_WhenSucessful(){
        Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(createAnimeToBeSaved);
        this.animeRepository.delete(savedAnime);
        Optional<Anime> animeOptional = this.animeRepository.findById(savedAnime.getId());
        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("Find By Name returns list of anime when Successful")
    void findByName_ReturnsListOfAnime_WhenSucessful(){
        Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(createAnimeToBeSaved);
        String name = savedAnime.getName();
        List<Anime> animes = this.animeRepository.findByName(name);
        this.animeRepository.delete(savedAnime);
        Assertions.assertThat(animes).isNotEmpty().contains(savedAnime);
    }

    @Test
    @DisplayName("Find By Name returns empty list when no anime is found")
    void findByName_ReturnsEmptyList_WhenAnimeIsNotFound(){
        List<Anime> animes = this.animeRepository.findByName("xaxa");
        Assertions.assertThat(animes).isEmpty();

    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when is empty")
    void save_ThrowsConstraintViolationException_WhenNameIsEmpty(){
        Anime anime= new Anime();
//        Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
//                        .isInstanceOf(ConstraintViolationException.class);
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.animeRepository.save(anime))
                .withMessageContaining("The anime name cannot be empty");

    }


}
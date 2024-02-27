package academy.devdojo.springboot.repository;

import java.util.List;

import academy.devdojo.springboot.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

    List<Anime> findByName(String name);
}

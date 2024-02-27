package academy.devdojo.springboot.util;

import academy.devdojo.springboot.dto.AnimeDTO;

public class AnimeDtoCreator {

    public static AnimeDTO createAnimeDTO(){
        return AnimeDTO.builder()
                .name(AnimeCreator.createAnimeToBeSaved().getName())
                .build();
    }

    public static AnimeDTO updateAnimeDTO(){
        return AnimeDTO.builder()
                .id(AnimeCreator.createValidUpdatedAnime().getId())
                .name(AnimeCreator.createValidUpdatedAnime().getName())
                .build();
    }

}

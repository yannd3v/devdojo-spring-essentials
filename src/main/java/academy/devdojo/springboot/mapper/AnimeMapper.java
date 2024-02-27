package academy.devdojo.springboot.mapper;

import academy.devdojo.springboot.domain.Anime;
import academy.devdojo.springboot.dto.AnimeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    public abstract Anime toAnime(AnimeDTO animeDTO);
}

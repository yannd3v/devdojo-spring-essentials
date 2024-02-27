package academy.devdojo.springboot.service;

import java.util.List;

import academy.devdojo.springboot.dto.AnimeDTO;
import academy.devdojo.springboot.exception.BadRequestException;
import academy.devdojo.springboot.mapper.AnimeMapper;
import academy.devdojo.springboot.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import academy.devdojo.springboot.domain.Anime;

@Service
@RequiredArgsConstructor
public class AnimeService {
	
	private final AnimeRepository animeRepository;
	
	public Page<Anime> listAll(Pageable pageable){
		return animeRepository.findAll(pageable);
	}

	public List<Anime> listAllNonPageable() {
		return animeRepository.findAll();
	}

	public List<Anime> findByName(String name){
		return animeRepository.findByName(name);
	}
	
	public Anime findByIdOrThrowBadRequestException(long id){
		
		return animeRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("Anime not Found"));
	}
	@Transactional
	public Anime save(AnimeDTO animeDTO) {
		return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animeDTO));
	}

	@Transactional
	public void delete(long id) {
		animeRepository.delete(findByIdOrThrowBadRequestException(id));
		
	}

	@Transactional
	public void replace(AnimeDTO animeDTO) {
		Anime savedAnime = findByIdOrThrowBadRequestException(animeDTO.id());
		Anime anime = AnimeMapper.INSTANCE.toAnime(animeDTO);
		animeRepository.save(anime);

	}


}

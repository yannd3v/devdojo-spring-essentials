package academy.devdojo.springboot.controller;

import java.time.LocalDateTime;
import java.util.List;

import academy.devdojo.springboot.dto.AnimeDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import academy.devdojo.springboot.domain.Anime;
import academy.devdojo.springboot.service.AnimeService;
import academy.devdojo.springboot.util.DataUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
	
	private final AnimeService animeService;
	

	@GetMapping
	public ResponseEntity<Page<Anime>> list(Pageable pageable){
		return ResponseEntity.ok(animeService.listAll(pageable));
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<Anime>> listAll(){
		return ResponseEntity.ok(animeService.listAllNonPageable());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Anime> findById(@PathVariable long id){
		return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
	}

	@GetMapping(path = "/find")
	public ResponseEntity<List<Anime>> findByName(@RequestParam String name){
		return ResponseEntity.ok(animeService.findByName(name));
	}
	
	
	@PostMapping
	public ResponseEntity<Anime> save(@RequestBody @Valid AnimeDTO anime) {
		return new ResponseEntity<>(animeService.save(anime), HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id){
		animeService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody AnimeDTO anime){
		animeService.replace(anime);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

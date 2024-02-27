package academy.devdojo.springboot.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record AnimeDTO(Long id, @NotEmpty(message = "The anime name cannot be empty") String name)
 { }

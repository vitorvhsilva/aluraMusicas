package alura.musica.desafio.repository;

import alura.musica.desafio.model.Artista;
import alura.musica.desafio.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT m FROM Artista a JOIN a.musicas m ")
    List<Musica> musicasDeTodosArtistas();

    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a = :artista")
    List<Musica> musicasPorArtista(Artista artista);
}

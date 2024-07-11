package alura.musica.desafio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String album;

    @ManyToOne
    private Artista artista;

    public Musica(String nome, String album, Artista artista) {
        this.nome = nome;
        this.album = album;
        this.artista = artista;
    }

    public Musica(Musica musica) {
        this.id = musica.id;
        this.nome = musica.nome;
        this.album = musica.album;
        this.artista = musica.artista;
    }


}

package alura.musica.desafio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas;

    public Artista(String nome, Tipo tipo, List<Musica> musicas) {
        this.nome = nome;
        this.tipo = tipo;
        this.musicas = musicas;
    }

}

package alura.musica.desafio.principal;

import alura.musica.desafio.model.Artista;
import alura.musica.desafio.model.Musica;
import alura.musica.desafio.model.Tipo;
import alura.musica.desafio.repository.ArtistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);

    private ArtistaRepository artistaRepository;

    public Principal(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            var menu = """
                     1 - Cadastrar artistas
                     2 - Cadastrar músicas
                     3 - Listar músicas 
                     4 - Buscar músicas por um artista
                    \s
                     0 - Sair                                \s
                    \s""";

            System.out.println(menu);
            
            System.out.println("Qual opção você deseja?");
            opcao = scanner.nextInt();scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    listarMusicasPorArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
            }
            
        }
    }

    private void cadastrarArtistas() {
        System.out.println("Qual o nome do artista?");
        String nome = scanner.nextLine();

        System.out.println("Qual o tipo do artista? (solo, dupla, banda)");
        String tipo = scanner.nextLine();

        Tipo tipoArtista = Tipo.fromString(tipo);

        ArrayList<Musica> musicas = new ArrayList<>();

        Artista artista = new Artista(nome, tipoArtista, musicas);
        artistaRepository.save(artista);
    }

    private void cadastrarMusicas() {
        System.out.println("Qual o nome do artista?");
        String nome = scanner.nextLine();

        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(nome);

        if (artista.isEmpty()) {
            System.out.println("Artista não encontrado!");
            return;
        }

        System.out.println("Qual o nome da música?");
        String nomeMusica = scanner.nextLine();

        System.out.println("Qual o nome do album?");
        String nomeAlbum = scanner.nextLine();

        Artista artistaPego = artista.get();

        Musica musica = new Musica(nomeMusica, nomeAlbum, artistaPego);

        artistaPego.getMusicas().add(musica);

        artistaRepository.save(artistaPego);
    }

    private void listarMusicas() {
        List<Artista> artistas = artistaRepository.findAll();

        if (artistas.isEmpty()) {
            System.out.println("Nenhum artista encontrado!");
            return;
        }

        List<Musica> musicas = artistaRepository.musicasDeTodosArtistas();

        musicas.forEach(m ->
                System.out.println("Nome: " + m.getNome() + ", Album: " + m.getAlbum() + ", Artista: " + m.getArtista().getNome()));
    }

    private void listarMusicasPorArtista() {
        System.out.println("Qual o nome do artista?");
        String nome = scanner.nextLine();

        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(nome);

        if (artista.isEmpty()) {
            System.out.println("Artista não encontrado!");
            return;
        }

        List<Musica> musicas = artistaRepository.musicasPorArtista(artista.get());

        musicas.forEach(m ->
                System.out.println("Nome: " + m.getNome() + ", Album: " + m.getAlbum() + ", Artista: " + m.getArtista().getNome()));
    }
}

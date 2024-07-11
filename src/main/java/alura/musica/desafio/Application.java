package alura.musica.desafio;

import alura.musica.desafio.principal.Principal;
import alura.musica.desafio.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private ArtistaRepository artistaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(artistaRepository);
		principal.exibirMenu();
	}
}

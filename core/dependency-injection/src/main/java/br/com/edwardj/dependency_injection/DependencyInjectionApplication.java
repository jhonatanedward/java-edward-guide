package br.com.edwardj.dependency_injection;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.List;

@SpringBootApplication
public class DependencyInjectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DependencyInjectionApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(MigracaoUsuario migracaoUsuario) {
		return args -> {
			migracaoUsuario.migrar();
		};
	}
}

interface Reader<T> {
	List<T> read();
}

interface Writer<T> {
	void write(List<T> itens);
}

@Component
class MigracaoUsuario {

	Writer<User> writer;
	Reader<User> reader;

	public MigracaoUsuario(Reader<User> reader, Writer<User> writer) {
		this.reader = reader;
		this.writer = writer;
	}
	void migrar(){
		List<User> users = reader.read();
		writer.write(users);
	}
}

record User(String email, String userName, String password) {

}

@Component
class ReaderFile implements Reader<User> {
	public List<User> read() {
		System.out.println("Lendo usuários do arquivo...");
		return List.of(new User("email", "username", "password"));
	}
}

@Component
class BdWriter implements Writer<User>{
	public void write(List<User> users) {
		System.out.println("Escrevendo usuários no banco...");
		System.out.println(users);
	}
}
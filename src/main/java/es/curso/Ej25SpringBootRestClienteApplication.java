package es.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import es.curso.entity.Videojuego;
import es.curso.service.VideojuegoProxyService;

@SpringBootApplication
public class Ej25SpringBootRestClienteApplication implements CommandLineRunner {
	
	@Autowired
	private VideojuegoProxyService videoService;
	
	@Autowired
	private ApplicationContext context;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(Ej25SpringBootRestClienteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Videojuego v2 = new Videojuego();
		v2.setAuthor("dww");
		v2.setCompania("232");
		v2.setImagen("232");
		v2.setNombre("ccw2");
		v2.setNota(2);
		v2.setPrice(1);
		
		System.out.println(videoService.alta(v2));
		
		videoService.getVideojuego(1);
		
		v2.setId(2);
		
		if (videoService.modificar(v2)) {
			System.out.println("Modificado");
		} else {
			System.out.println("No modificado");
		}
		
		System.out.println(videoService.findAllVideojuegos());
		
		pararAplicacion();
		
	}
	
	public void pararAplicacion() {
		
		SpringApplication.exit(context, () -> 0);
	}

}

package es.curso.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.curso.entity.Videojuego;
import es.curso.service.VideojuegoProxyService;

@Service
public class VideojuegoProxyServiceImpl implements VideojuegoProxyService {

	public static final String URL = "http://localhost:8080/api/videojuegos/";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Videojuego getVideojuego(int id) {

		try {

			ResponseEntity<Videojuego> re = restTemplate.getForEntity(URL + id, Videojuego.class);			
			return re.getBody();

		} catch (HttpClientErrorException e) {
			System.out.println(e.getResponseBodyAsString());
			return null;
		}

	}

	@Override
	public Videojuego alta(Videojuego v) {
		
		try {

			ResponseEntity<Videojuego> re = restTemplate.postForEntity(URL, v, Videojuego.class);
			return re.getBody();

		} catch (HttpClientErrorException e) {
			System.out.println(e.getResponseBodyAsString());
			return null;
		}
	}

	@Override
	public boolean modificar(Videojuego v) {
		try {
			
			restTemplate.put(URL + v.getId(), v, Videojuego.class);
			return true;
			
		} catch (HttpClientErrorException e) {
			
			System.out.println(e.getResponseBodyAsString());
			return false;
			
		}
	}

	@Override
	public boolean borrar(int id) {
		try {
			restTemplate.delete(URL + id);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println(e.getResponseBodyAsString());
		    return false;
		}
	}

	@Override
	public List<Videojuego> findAllVideojuegos() {
		try {
			ResponseEntity<Videojuego[]> response =
					  restTemplate.getForEntity(URL, Videojuego[].class);
			Videojuego[] arrayVideojuegos = response.getBody();
			return Arrays.asList(arrayVideojuegos);
		} catch (HttpClientErrorException e) {
			System.out.println("listar -> Error al obtener la lista de personas");
		    System.out.println("listar -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}

}

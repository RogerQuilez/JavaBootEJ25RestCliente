package es.curso.service;

import java.util.List;

import es.curso.entity.Videojuego;

public interface VideojuegoProxyService {

	public Videojuego getVideojuego(int id);
	public Videojuego alta(Videojuego v);
	public boolean modificar(Videojuego v);
	public boolean borrar(int id);
	public List<Videojuego> findAllVideojuegos();
	
}

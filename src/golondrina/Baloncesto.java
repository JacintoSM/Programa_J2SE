package golondrina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Baloncesto implements Juego {

	public HashMap<String, Integer> procesarPartido(List<String> datos) {

		HashMap<String, Integer> puntuacion = new HashMap<>();
		HashMap<String, Integer> equipoPuntos = new HashMap();
		HashMap<String, String> equipoJugadores = new HashMap();

		Iterator<String> nombreIterator = datos.iterator();		
					
		while (nombreIterator.hasNext()) {
			String jugador = nombreIterator.next();
			StringTokenizer st = new StringTokenizer(jugador, ";");
			String nombre = st.nextToken();
			String apodo = st.nextToken();
			String dorsal = st.nextToken();
			String equipo = st.nextToken();
			String posicion = st.nextToken();
			String puntos = st.nextToken();
			String rebotes = st.nextToken();
			String asistencias = st.nextToken();

			int puntosTot = puntuacion(equipoPuntos, equipo, posicion, puntos, rebotes, asistencias);
			
			equipoJugadores.put(apodo,equipo); //Jugador y equipo

			puntuacion.put(apodo, puntosTot); //Jugador y total

		}//fin while

		// Calculos de los 10 puntos
		int puntosEquipoGanador = 0;
		String equipoGanador = null;
		int auxA=0;
		int auxB=0;
		//System.out.println(equipoPuntos);
		
		
		for (String equipo : equipoPuntos.keySet()) {
				
			if (equipoPuntos.get(equipo) > puntosEquipoGanador) {
				equipoGanador = equipo;
			}
			
		}		
				
		for (String apodo : equipoJugadores.keySet()) {
			String jugador = null;
			
			if (equipoJugadores.get(apodo).equals(equipoGanador)) {
			
				//System.out.println(apodo);	//apodos ganadores		
				int puntosJugadorTotales = 0;
				puntosJugadorTotales = puntuacion.get(apodo) + 10;
				puntuacion.put(apodo,puntosJugadorTotales);
			}	
				
		}		
				
		return puntuacion;
	}

	private int puntuacion(HashMap<String, Integer> equipoPuntos, String equipo, String posicion, String puntos,
			String rebotes, String asistencias) {
		int puntosTot = 0;
		if (posicion.equals("D")) {
			puntosTot = Integer.parseInt(puntos) * 2 + Integer.parseInt(rebotes) * 3
					+ Integer.parseInt(asistencias) * 1;
		}
			
		if (posicion.equals("A")) {
			puntosTot = Integer.parseInt(puntos) * 2 + Integer.parseInt(rebotes) * 2
					+ Integer.parseInt(asistencias) * 2;
		} 
			
		if (posicion.equals("C")) {
			puntosTot = Integer.parseInt(puntos) * 2 + Integer.parseInt(rebotes) * 1
					+ Integer.parseInt(asistencias) * 3;
		}

		if (equipoPuntos.containsKey(equipo)) {
			equipoPuntos.put(equipo, Integer.parseInt(puntos) + equipoPuntos.get(equipo)); 
		} else {
			equipoPuntos.put(equipo, Integer.parseInt(puntos)); //Crear
		}
		return puntosTot;
	}

	int calcular(int n) {
		int total = n;
		return total;
	}

}

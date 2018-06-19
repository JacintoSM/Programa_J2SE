package golondrina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Balonmano implements Juego {
	
	public HashMap<String, Integer> procesarPartido(List<String> datos) {

		HashMap<String, Integer> puntuacion = new HashMap<>();
		HashMap<String, Integer> equipoPuntos = new HashMap();
		HashMap<String, String> equipoJugadores = new HashMap();

		Iterator<String> nombreIterator = datos.iterator();		
		
		while(nombreIterator.hasNext()){				
			String jugador = nombreIterator.next();
			StringTokenizer st = new StringTokenizer(jugador,";");
			String nombre = st.nextToken();
			String apodo = st.nextToken();
			String dorsal = st.nextToken();
			String equipo = st.nextToken();
			String posicion = st.nextToken();
			String golesMarcados = st.nextToken();
			String golesRecibidos = st.nextToken();		
			
			int puntosTot = puntacionBalonmano(equipoPuntos, equipo, posicion, golesMarcados, golesRecibidos);
			
			equipoJugadores.put(apodo,equipo); //Jugador y equipo
			
			puntuacion.put(apodo, puntosTot); //Jugador y total
						
		}	//Fin While	
	
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

	private int puntacionBalonmano(HashMap<String, Integer> equipoPuntos, String equipo, String posicion,
			String golesMarcados, String golesRecibidos) {
		int puntosTot=0;
		if(posicion.equals("P")) {
			puntosTot = 50+Integer.parseInt(golesMarcados)*5-Integer.parseInt(golesRecibidos)*2;
		}else if(posicion.equals("J")) { 
			puntosTot = 20+Integer.parseInt(golesMarcados)*1-Integer.parseInt(golesRecibidos)*1;
		}
		
		if (equipoPuntos.containsKey(equipo)) {
			equipoPuntos.put(equipo, Integer.parseInt(golesMarcados) + equipoPuntos.get(equipo)); 
		} else {
			equipoPuntos.put(equipo, Integer.parseInt(golesMarcados)); //Crear
		}
		return puntosTot;
	}

			int calcular(int n) {
				int total = n;
				return total;
			}

		}

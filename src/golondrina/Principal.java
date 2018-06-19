package golondrina;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class Principal {

	static HashMap<String, Integer> clasificacionGlobalJugadores = new HashMap<>();
	
	//Declaramos variables 
	public static boolean salir = false;
	public static String texto;
	public static String archivo;
	public static int contador; 
	public static String deporte;
	
	public static void main(String[] args) throws IOException {
		
			while (!salir) {
			
			//Sacamos menu para elegir opcion
			texto = JOptionPane.showInputDialog("Opciones: \n" + "1.- Añadir fichero \n" + 
							"2.- Listar ganador\n"+ "3.- Salir \n");
			
			//Valoramos la opcion elegida		
			switch (texto) {
		    	case "1":		    		
		    		// Pedimos nombre del fichero
		    		archivo = JOptionPane.showInputDialog("Introducir nombre fichero: ");
		    		//Añadir fichero 
		    		File fichero = new File(archivo);
		    		if (fichero.exists()) {	
		    			
		    			
		    			ArrayList<String> contenidoFichero = leerFichero(fichero);
		    			HashMap<String, Integer> parcialJugadores = procesarFichero(contenidoFichero);  
		    			      
		    			
		    			for(String jugador : parcialJugadores.keySet()) {
		    				
		    				//System.out.println(jugador);
		    				int puntosParcial = parcialJugadores.get(jugador);
		    				
		    				//System.out.println(puntosParcial);
		    				
	    					if(clasificacionGlobalJugadores.containsKey(jugador)) {
	    						
	    						int puntosTotales = clasificacionGlobalJugadores.get(jugador);
	    						puntosTotales = puntosTotales + puntosParcial;
	    						
	    						clasificacionGlobalJugadores.put(jugador, puntosTotales);		    						
	    								    						
	    					}else {
	    						clasificacionGlobalJugadores.put(jugador, puntosParcial);
	    					}
		    					
		    				
		    			}		    			
		    			
		    			JOptionPane.showMessageDialog(null, "Fichero añadido");
		    		}else {
		    			JOptionPane.showMessageDialog(null, "Fichero no existe");
		    		}	    			
		  					  			
		            break;
		            
		    	case "2":
		    				    			
		    		Object maxEntry = Collections.max(clasificacionGlobalJugadores.entrySet(), Map.Entry.comparingByValue()).getKey();      
		    		
		    		JOptionPane.showMessageDialog(null, "El mayor es  = " + maxEntry+" su valoracion es "+clasificacionGlobalJugadores.get(maxEntry));

		    		break;
		           
		    	case "3":
		    		//Salimos del menu
		    		JOptionPane.showMessageDialog(null, "Adios");
		    		salir = true;		           	
		    		break;
		          
		    	default:
		    		//En caso de error
		    		System.out.println("Opcion erronea" );
		    		break;
		      }
		}				
		
		// Sumar el parcial a la clasificación global
		
	}

	private static int get(String claves) {
		// TODO Auto-generated method stub
		return 0;
	}

	static ArrayList<String> leerFichero(File fichero) throws IOException {
		
		// Cargamos el contenido del fichero en un arraylist
		ArrayList<String> contenidoFichero = new ArrayList<>();
		
			BufferedReader br = null;
			try {
				FileReader fr = new FileReader(fichero);
				br = new BufferedReader(fr);
				
				String linea;
				contador = 1;
				while ((linea=br.readLine())!=null) {
					
					contenidoFichero.add(linea);					
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finally {
				// TODO: handle finally clause
				br.close();
			}
				
			
		return contenidoFichero;
		
	}
	
		
	static HashMap<String, Integer> procesarFichero(ArrayList<String> ArrayFichero) {

		// Datos de jugadores
		HashMap<String, Integer> jugadores = null;
				
		if ("BALONCESTO".equals(ArrayFichero.get(0))) {
			Baloncesto objetoBaloncesto = new Baloncesto();
			jugadores = objetoBaloncesto.procesarPartido(ArrayFichero.subList(1, ArrayFichero.size()));
		}
		if ("BALONMANO".equals(ArrayFichero.get(0))) {			
			Balonmano objetoBalonmano = new Balonmano();
			jugadores = objetoBalonmano.procesarPartido(ArrayFichero.subList(1, ArrayFichero.size()));
		}		
		
		
		return jugadores;
	}

}

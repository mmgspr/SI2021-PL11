package giis.demo.tkrun;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

public class GestionColas {
	
	private static ArrayList<Integer> socios;
	private static ArrayList<String> clientes;
	private static FileOutputStream fos = null;
	private static ObjectOutputStream oos= null;
	private static FileInputStream fis= null;
	private static ObjectInputStream ois = null;
	private static FileOutputStream fosAux = null;
	private static ObjectOutputStream oosAux= null;
	private static FileInputStream fisAux= null;
	private static ObjectInputStream oisAux = null;
	private static String ruta;
	private static String rutaTmp;
    private static File file, tmp;
	
	public static int inicializa() {
		//colas
		socios = new ArrayList<Integer>();
		clientes = new ArrayList<String>();
		//fichero
		try {
			ruta = "src/main/resources/listas.txt";
			ruta = "src/main/resources/tmp.txt";
	        file = new File(ruta);
	        tmp = new File(rutaTmp);
	        // Si el archivo no existe es creado
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        if (!tmp.exists()) {
	            tmp.createNewFile();
	        }
			//controladores
			fos = new FileOutputStream(file);
	        oos = new ObjectOutputStream(fos); 
	        fis = new FileInputStream(file);
	        ois = new ObjectInputStream(fis);
	        fosAux = new FileOutputStream(tmp);
	        oosAux = new ObjectOutputStream(fosAux); 
	        fisAux = new FileInputStream(tmp);
	        oisAux = new ObjectInputStream(fisAux);
		}catch(Exception e) {
			System.out.println("Error al inicializar las colas o el fichero.");
			return -1;
		}
		
		return 0;
	}
	
	
	private static void serializa(ObjectOutputStream oosArg, ArrayList<Integer> socios,ArrayList<String> clientes, int idActividad) {
//		try {
//			 String ruta = "src/main/resources/listas";
//	         String contenido = "Actividad: "+idActividad;
//	         File file = new File(ruta);
//	         // Si el archivo no existe es creado
//	         if (!file.exists()) {
//	             file.createNewFile();
//	         }
//	         FileWriter fw = new FileWriter(file);
//	         BufferedWriter bw = new BufferedWriter(fw);
//	         bw.write(contenido);
//			    bw.close();
	         
//	         FileOutputStream fos = new FileOutputStream(file);
//	         ObjectOutputStream oos = new ObjectOutputStream(fos);        
			   
		     try {
				oosArg.writeInt(idActividad);
				oosArg.writeObject(socios);
				oosArg.writeObject(clientes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
//			 oos.close();
//			 fos.close();
		    
//	    } catch (Exception e1) {
//	        e1.printStackTrace();
//	    }
	}
	
	private static ArrayList<?>[] desserializa(int idActividad) {
		ArrayList[] retorno = null;
		int actividad=-1;
		ArrayList<Integer> socios = null;
		ArrayList<String> clientes = null;
		try {
			//ois.readLine();
			while(actividad != idActividad) {
				actividad = ois.readInt();
				socios = (ArrayList<Integer>)ois.readObject();
				clientes = (ArrayList<String>)ois.readObject();	
			}
		}
		//excepcion de fin de fichero
		catch(EOFException e) {
			System.out.println("No hay cola para la actividad seleccionada");
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//crear el array para retornar
		retorno = new ArrayList[2];
		retorno[0] = socios;
		retorno[1] = clientes;
		
		//prints para ver si se está haciendo bien
		System.out.println(actividad);
		for(int i=0;i<retorno[0].size();i++) {
			System.out.println(retorno[0].get(i));	
		}
		for(int i=0;i<retorno[1].size();i++) {
			System.out.println(retorno[1].get(i));	
		}
			
		return retorno;
	}
	
	public static void borrarFichero() {
		try {
			//cerrar los anteriores
			oos.close();
			fos.close();
			ois.close();
			fis.close();
			//crear nuevos	
			fos = new FileOutputStream(file);
		    oos = new ObjectOutputStream(fos); 
		    fis = new FileInputStream(file);
		    ois = new ObjectInputStream(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	//esta funcion copia el fichero entero menos una actividad en concreto para poder modificarla
	//se copia en un fichero temporal aparte
	public static void copiarFichero(int idActividad) {
		int actividad;
		ArrayList<Integer> sociosAux;
		ArrayList<String> clientesAux;
		try {
			while(true) {
				//leer
				actividad = oisAux.readInt();
				sociosAux = (ArrayList<Integer>)oisAux.readObject();
				clientesAux = (ArrayList<String>)oisAux.readObject();
				//escribir en el nuevo (EL DE LA ACTIVIDAD QUE NOS PASAN NO)
				if(actividad != idActividad) {
					serializa(oosAux,sociosAux,clientesAux,actividad);
				}
			}
		}catch(EOFException e){
			
		}catch(Exception e) {
			
		}
		
	}
	
	private static void eliminaActividad(int idActividad) {
		copiarFichero(idActividad);
		borrarFichero();
		try {
			Files.copy(tmp.toPath(), file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static int anadeSocio(int idSocio, int idActividad) {
		ArrayList<?>[] colas = desserializa(idActividad);
		//no hay cola para esa actividad, luego hay que crearla
		if(colas == null) {
			socios = new ArrayList<Integer>();
			clientes = new ArrayList<String>();
		}
		else {
			socios = (ArrayList<Integer>) colas[0];
			clientes = (ArrayList<String>) colas[1];
			
		}
		//añadir al socio a la cola
		socios.add(idSocio);
		//borrar del fichero las lineas antiguas(guardar sin lineas, borrar entero y volver a copiar)
		eliminaActividad(idActividad);
		//escribir en el fichero las nuevas colas con el socio añadido
		serializa(oos,socios,clientes,idActividad);
		
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public static int anadeCliente(String tlf, int idActividad) {
		ArrayList<?>[] colas = desserializa(idActividad);
		if(colas == null) {
			socios = new ArrayList<Integer>();
			clientes = new ArrayList<String>();
		}
		else {
			socios = (ArrayList<Integer>) colas[0];
			clientes = (ArrayList<String>) colas[1];
		}
		//añadir al socio a la cola
		clientes.add(tlf);
		//borrar del fichero las lineas antiguas(guardar sin lineas, borrar entero y volver a copiar)
		eliminaActividad(idActividad);
		//escribir en el fichero las nuevas colas con el socio añadido
		serializa(oos,socios,clientes,idActividad);
		
		return 0;
	}
	
	public static void main(String[] args) {
		
//        FileWriter fw = new FileWriter(file);
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write(contenido);
//		    bw.close();		
		
		ArrayList<Integer> socios = new ArrayList<Integer>();
		socios.add(1);
		socios.add(4);
		ArrayList<String> clientes = new ArrayList<String>();
		clientes.add("hola");
		clientes.add("second");
		clientes.add("tercero");
		
		ArrayList<Integer> socios2 = new ArrayList<Integer>();
		socios2.add(2);
		socios2.add(3);
		ArrayList<String> clientes2 = new ArrayList<String>();
		clientes2.add("hola2");
		clientes2.add("second2");
		clientes2.add("tercero2");
		
		serializa(oos, socios2, clientes2, 5);
		serializa(oos, socios, clientes, 3);
		
		anadeSocio(10,5);
		anadeCliente("603441826",3);
		anadeSocio(2,7);
		
		System.out.println("hecho");
		
		 
		 try {
			oos.close();
			fos.close();
			ois.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}

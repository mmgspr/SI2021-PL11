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

public class GestionColas {
	
	public static int crearCola(int idActividad) {
		
		ArrayList<Integer> socios = new ArrayList<Integer>();
		ArrayList<String> clientes = new ArrayList<String>();
		
		return 1;
	}
	
	private static void serializa(ObjectOutputStream oos ,ArrayList<Integer> socios,ArrayList<String> clientes, int idActividad) {
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
				oos.writeInt(idActividad);
				oos.writeObject(socios);
				oos.writeObject(clientes);
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
	
	private static ArrayList<?>[] desserializa(ObjectInputStream ois , int idActividad) {
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
	
	public static void main(String[] args) {
		FileOutputStream fos = null;
		ObjectOutputStream oos= null;
		FileInputStream fis= null;
		ObjectInputStream ois = null;
		try {
		String ruta = "src/main/resources/listas.txt";
        File file = new File(ruta);
        // Si el archivo no existe es creado
        if (!file.exists()) {
            file.createNewFile();
        }
//        FileWriter fw = new FileWriter(file);
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write(contenido);
//		    bw.close();
        
        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos); 
        fis = new FileInputStream(file);
        ois = new ObjectInputStream(fis);
		} catch (Exception e1) {
	        e1.printStackTrace();
	    }
		
		
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
		
		serializa( oos, socios2, clientes2, 5);
		serializa( oos, socios, clientes, 3);
		desserializa(ois,2);
		System.out.println("hecho");
		
		 
		 try {
			oos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}

package giis.demo.tkrun;

import java.io.BufferedWriter;
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
	
	private static void serializa(FileOutputStream fos, ObjectOutputStream oos ,ArrayList<?> l, int idActividad) {
//		try {
//			 String ruta = "src/main/resources/listas";
	         String contenido = "Actividad: "+idActividad+"\n";
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
				oos.writeObject(contenido);
				oos.writeObject(l);
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
	
	private static ArrayList desserializa(FileInputStream fis, ObjectInputStream ois , int idActividad) {
		return null;
	}
	
	public static void main(String[] args) {
		FileOutputStream fos = null;
		ObjectOutputStream oos= null;
		try {
		String ruta = "src/main/resources/listas";
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
		} catch (Exception e1) {
	        e1.printStackTrace();
	    }
		
		
		ArrayList<String> l = new ArrayList<String>();
		l.add("eeey");
		l.add("segundo elemento");
		ArrayList<String> l1 = new ArrayList<String>();
		l1.add("hola");
		l1.add("second");
		serializa(fos, oos, l, 3);
		serializa(fos, oos, l1,5);
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

package giis.demo.tkrun;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class GestionColas {
	private static ArrayList<ArrayList<ArrayList<Integer>>> superLista ;
	private static ArrayList<Integer> socios;
	private static ArrayList<Integer> clientes;
	
	private static FileOutputStream fos = null;
	private static ObjectOutputStream oos= null;
	private static FileInputStream fis= null;
	private static ObjectInputStream ois = null;
	/*private static FileOutputStream fosAux = null;
	private static ObjectOutputStream oosAux= null;
	private static FileInputStream fisAux= null;
	private static ObjectInputStream oisAux = null;*/
	private static String ruta;
	private static String rutaTmp;
    private static File file, tmp;
    
    
	
	/*public static int inicializa() {
		//colas
		socios = new ArrayList<Integer>();
		clientes = new ArrayList<String>();
		//fichero
		try {
			ruta = "src/main/resources/listas.txt";
			rutaTmp = "src/main/resources/tmp.txt";
			
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
	       
		}catch(Exception e) {
			System.out.println("Error al inicializar las colas o el fichero.");
			return -1;
		}
		
		return 0;
	}*/
	
	
	/*private static void serializa(ObjectOutputStream oosArg, ArrayList<Integer> socios,ArrayList<String> clientes, int idActividad) {
		try {
			 String ruta = "src/main/resources/listas.csv";
	         String contenido = "Actividad: "+idActividad;
	         File file = new File(ruta);
	         // Si el archivo no existe es creado
	         if (!file.exists()) {
	             file.createNewFile();
	         }
	         FileWriter fw = new FileWriter(file);
	         BufferedWriter bw = new BufferedWriter(fw);
	         bw.write(contenido);
	         for(int i = 0;i<socios.size();i++) {
	        	 bw.write(socios.get(i));
	         }
	         for(int i = 0;i<clientes.size();i++) {
	        	 bw.write(clientes.get(i));
	         }
			    bw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	         
	                
			   
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
	}*/
	
	/*private static ArrayList<?>[] desserializa(int idActividad) {
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
	}*/
	
	/*public static void borrarFichero() {
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
	}*/
	
	/*@SuppressWarnings("unchecked")
	//esta funcion copia el fichero entero menos una actividad en concreto para poder modificarla
	//se copia en un fichero temporal aparte
	public static void copiarFichero(int idActividad) {
		int actividad;
		ArrayList<Integer> sociosAux;
		ArrayList<String> clientesAux;
			
		try {
			fosAux = new FileOutputStream(tmp);
			oosAux = new ObjectOutputStream(fosAux); 
	        fisAux = new FileInputStream(tmp);
	        oisAux = new ObjectInputStream(fisAux);
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
			try {
				oosAux.close();
				oisAux.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}catch(Exception e) {
			System.out.println("Error al copiar el fichero.");
		}
		
	}*/
	
	/*private static void eliminaActividad(int idActividad) {
		copiarFichero(idActividad);
		borrarFichero();
		try {
			//Files.copy(tmp.toPath(), file.toPath(),StandardCopyOption.REPLACE_EXISTING);
			int c;
			while ((c = fis.read()) != -1) {
                fosAux.write(c);
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/*@SuppressWarnings("unchecked")
	public static int anadeSocio(int idSocio, int idActividad) {
		//ArrayList<?>[] colas = desserializa(idActividad);
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
	}*/
	
	/*public static void ver() {
		ArrayList<Integer> s;
		ArrayList<String> c;	
        
		System.out.println("Funcion ver para visualizar el fichero entero");
		try {
		while(true) {
			System.out.println(ois.readInt());
			s = (ArrayList<Integer>)ois.readObject();
			c = (ArrayList<String>)ois.readObject();
			for(int i = 0; i<s.size(); i++) System.out.println(s.get(i));
			for(int i = 0; i<c.size(); i++) System.out.println(c.get(i));
		}
		}catch(EOFException e) {
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Acaba la funcion ver");
		
	}*/
    
    
    
    @SuppressWarnings("unchecked")
	public static int inicializa() {
    	try {
    		ruta = "src/main/resources/listas.txt";
        	File file = new File(ruta);
    		
            // Si el archivo no existe es creado
            if (file.exists()) {
            	fis = new FileInputStream(file);
    	        ois = new ObjectInputStream(fis);
            	superLista = (ArrayList<ArrayList<ArrayList<Integer>>>) ois.readObject();
            	ois.close();
    			fis.close();
            }
            else {
            	superLista = new ArrayList<ArrayList<ArrayList<Integer>>>();
            }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return 0;
    }
    
    public static int serializa() {
    	try {
    		ruta = "src/main/resources/listas.txt";
        	File file = new File(ruta);
        	fos = new FileOutputStream(file);
	        oos = new ObjectOutputStream(fos); 
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            oos.writeObject(superLista);
            oos.close();
			fos.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return 0;
    }
    
    public static int anadeSocio(int idSocio, int idActividad) {
    	boolean encontrado= false;
    	ArrayList<ArrayList<Integer>> anadir;
    	ArrayList<Integer> anadir2;
    	ArrayList<Integer> anadir3;
    	ArrayList<Integer> anadir4;
    	for(int i = 0;i<superLista.size();i++) {
    		if(superLista.get(i).get(0).get(0) == idActividad) {
    			superLista.get(i).get(1).add(idSocio);
    			encontrado = true;
    			break;
    		}
    	}
    	if (!encontrado) {
    		anadir2 = new ArrayList<Integer>();
    		anadir2.add(idActividad);
    		anadir3 = new ArrayList<Integer>();
    		anadir3.add(idSocio);
    		anadir4 = new ArrayList<Integer>();
    		anadir = new ArrayList<ArrayList<Integer>>();
    		anadir.add(anadir2);
    		anadir.add(anadir3);
    		anadir.add(anadir4);
    		superLista.add(anadir);
    	}
    	  	
        
    	return 0;
    }
    
    public static int anadeCliente(int tlf, int idActividad) {
    	boolean encontrado= false;
    	ArrayList<ArrayList<Integer>> anadir;
    	ArrayList<Integer> anadir2;
    	ArrayList<Integer> anadir3;
    	ArrayList<Integer> anadir4;
    	for(int i = 0;i<superLista.size();i++) {
    		if(superLista.get(i).get(0).get(0) == idActividad) {
    			superLista.get(i).get(2).add(tlf);
    			encontrado = true;
    			break;
    		}
    	}
    	if (!encontrado) {
    		anadir2 = new ArrayList<Integer>();
    		anadir2.add(idActividad);
    		anadir3 = new ArrayList<Integer>();
    		anadir4 = new ArrayList<Integer>();
    		anadir4.add(tlf);
    		anadir = new ArrayList<ArrayList<Integer>>();
    		anadir.add(anadir2);
    		anadir.add(anadir3);
    		anadir.add(anadir4);
    		superLista.add(anadir);
    	}
    	
        
    	return 0;
    }
    
    public static int posicion(String actividad, int socio) {
    	int retorno = -1;
    
    	for(ArrayList<ArrayList<Integer>> i : superLista ) {
    		if(Integer.toString(i.get(0).get(0)).equals(actividad)) {
    			for (Integer j : i.get(1)) {
    				retorno ++;
    				if (j == socio) return retorno ;
    			}
    		}
    	}
    	return retorno;
    }
    
    public static void ver() {
    	for (ArrayList<ArrayList<Integer>> i : superLista) {
    		System.out.println("Actividad " + i.get(0).get(0));
    		System.out.print("Socios: ");
    		for(Integer j : i.get(1)) {
    			System.out.print(j + ", ");
    		}
    		System.out.print("Clientes: ");
    		for(Integer j : i.get(2)) {
    			System.out.print(j + ", ");
    		}
    		System.out.println("");
    	}
    }
    
	public static void main(String[] args) throws IOException {
		
//        FileWriter fw = new FileWriter(file);
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write(contenido);
//		    bw.close();		
		/*inicializa();
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
		ver();
		
		System.out.println("hecho");
		
		 
		 try {
			oos.close();
			fos.close();
			ois.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
//		ruta = "src/main/resources/listas.txt";
//		File file = new File(ruta);
//        // Si el archivo no existe es creado
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//		try {
//			fos = new FileOutputStream(file);
//	        oos = new ObjectOutputStream(fos); 
//	        fis = new FileInputStream(file);
//	        ois = new ObjectInputStream(fis);
//		}catch(Exception e) {
//			System.out.println("tus muertos");
//		}
		inicializa();
		//superLista = new ArrayList<ArrayList<ArrayList<Integer>>>();
		anadeSocio(2,3);
		anadeSocio(1,3);
		anadeSocio(1,2);
//		anadeCliente(673548725,4);
//		anadeCliente(603441826,4);
//		anadeCliente(673548725,6);
//		anadeCliente(673548725,8);
//		anadeSocio(2,1);
		/*System.out.println(superLista.get(0).get(0).get(0));
		System.out.println(superLista.get(0).get(1).get(0) +" "+ superLista.get(0).get(1).get(1));
		System.out.println(superLista.get(0).get(2).get(0));
		System.out.println(superLista.get(0).get(2).get(1));*/
		ver();
		serializa();
		
		 
	}

}

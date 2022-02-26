package giis.proyecto.Modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.commons.dbutils.DbUtils;

import giis.proyecto.Modelo.ApplicationException;
import giis.proyecto.Modelo.DbUtil;

public class Database extends DbUtil{
	//Localizacion de ficheros de configuracion y carga de bases de datos
	private static final String APP_PROPERTIES = "src/main/resources/application.properties";
	private static final String SQL_SCHEMA = "src/main/resources/schema.sql";
	private static final String SQL_LOAD = "src/main/resources/data.sql";
	//parametros de la base de datos leidos de application.properties (base de datos local sin usuario/password)
	private String driver;
	private String url;
	private static boolean databaseCreated=false;
	
	/**
	 * Crea una instancia, leyendo los parametros de driver y url de application.properties
	 */
	public Database() {
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream(APP_PROPERTIES));
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
		driver=prop.getProperty("datasource.driver");
		url=prop.getProperty("datasource.url");
		if (driver==null || url==null)
			throw new ApplicationException("Configuracion de driver y/o url no encontrada en application.properties");
		DbUtils.loadDriver(driver);
	}
	public String getUrl() {
		return url;
	}
	/** 
	 * Creacion de una base de datos limpia a partir del script schema.sql en src/main/properties
	 * (si onlyOnce=true solo ejecutara el script la primera vez
	 */
	public void createDatabase(boolean onlyOnce) {
		//actua como singleton si onlyOnce=true: solo la primera vez que se instancia para mejorar rendimiento en pruebas
		try {
			if (!databaseCreated || !onlyOnce) { 
			executeScript(SQL_SCHEMA);
			databaseCreated=true; //NOSONAR
			JOptionPane.showMessageDialog(null,"Base de datos creada con Exito", "Correcto", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		catch (Exception e) {
			System.out.print(e.getMessage());
			JOptionPane.showMessageDialog(null,"Error al crear la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
			
			
			
		
		
	}
	/** 
	 * Carga de datos iniciales a partir del script data.sql en src/main/properties
	 * (si onlyOnce=true solo ejecutara el script la primera vez
	 */
	public void loadDatabase() {
		try {
			executeScript(SQL_LOAD);
			JOptionPane.showMessageDialog(null,"Datos iniciales cargados con exito", "Correcto", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e) {
			System.out.print(e.getMessage());
			JOptionPane.showMessageDialog(null,"Error al cargar los datos iniciales en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
}





DELETE FROM reservas;
DELETE FROM inscripciones;
DELETE FROM actividades;
DELETE FROM periodos_inscripcion;
DELETE FROM instalaciones;
DELETE FROM socios;
DELETE FROM clientes;

INSERT INTO socios(id_socio, nombre, fecha_nacimiento, dni) VALUES 

(1, 'David', '2001-12-21' , '12345678C'), 

(2, 'Matias', '2005-6-24', '87654321F'), 

(3, 'Juan', '1972-7-15', '13254768D'); 

INSERT INTO clientes(id_cliente, nombre, fecha_nacimiento) VALUES 

(1,'Alvaro', '2001-12-21' ), 

(2,'Daniel', '2005-6-24'), 

(3,'Roberto', '1972-7-15'); 
INSERT INTO instalaciones(id_instalacion, nombre, deporte, precio) VALUES 

(1,'Pista de tenis 1', 'tenis', 7), 

(2,'Pista de tenis 2', 'tenis', 5.3), 

(3, 'Pista de padel 1', 'padel', 10.50); 
INSERT INTO reservas(id_reserva, persona, instalacion, fecha, fecha_reserva) VALUES 

(1,1, 1, '2022-2-23 17:15:10', '2022-2-24 17:00:00'), 

(2,2, 2,'2022-2-25 18:50:41', '2022-2-27 20:00:00'), 

(3,3, 3,'2022-2-26 20:05:23', '2022-2-27 19:00:00'); 
INSERT INTO periodos_inscripcion(id_periodo_inscripcion, fecha_ini_socio, fecha_fin_socio, fecha_fin_no_socio) VALUES 

(1,'2022-2-24', '2022-3-24', '2022-3-30'), 

(2,'2022-2-27', '2022-3-30', '2022-4-30'), 

(3,'2022-3-17', '2022-3-30', '2022-4-30'); 
INSERT INTO actividades(id_actividad, nombre, descripcion, aforo, precio_socio, precio_no_socio, fecha_ini, fecha_fin, deporte, instalacion, periodo_inscripcion) VALUES 

(1,'Torneo Padel I', 'Primer torneo de padel.' , '32', '8.50', '12.00', '2022-2-24', '2022-3-3', 'padel', '1', '1'), 

(2,'Torneo Tenis I', 'Primer torneo de tenis.' , '24', '10.00', '15.00', '2022-2-27', '2022-3-10', 'tenis', '2', '2'), 

(3,'Torneo Futbol I', 'Primer torneo de futbol.' , '100', '6.50', '10.00', '2022-3-17', '2022-3-27', 'futbol', '3', '3'); 

INSERT INTO inscripciones(id_inscripcion, persona, actividad, fecha) VALUES 

(1,1, 1, '2022-2-23 17:15:10'), 

(2,2, 2,'2022-2-25 18:50:41'), 

(3,3, 3,'2022-2-26 20:05:23');


DELETE FROM socios;
DELETE FROM instalaciones;
DELETE FROM reservas;
DELETE FROM clientes;
DELETE FROM actividades;
DELETE FROM periodos_inscripcion;
DELETE FROM inscripciones;

INSERT INTO socios(nombre, fecha_nacimiento, dni) VALUES 

('David', '2001-12-21' , '12345678C'), 

('Matias', '2005-6-24', '87654321F'), 

('Juan', '1972-7-15', '13254768D'); 

INSERT INTO clientes(nombre, fecha_nacimiento, id_dni_cliente) VALUES 

('Alvaro', '2001-12-21' , '18275678C'), 

('Daniel', '2005-6-24', '99654321F'), 

('Roberto', '1972-7-15', '21654768D'); 
INSERT INTO instalaciones(nombre, deporte, precio) VALUES 

('Pista de tenis 1', 'tenis', 7), 

('Pista de tenis 2', 'tenis', 5.3), 

('Pista de padel 1', 'padel', 10.50); 
INSERT INTO reservas(socio, instalacion, fecha, fecha_reserva) VALUES 

(1, 1, '2022-2-23 17:15:10', '2022-2-24 17:00:00'), 

(2, 2,'2022-2-25 18:50:41', '2022-2-27 20:00:00'), 

(3, 3,'2022-2-26 20:05:23', '2022-2-27 19:00:00'); 
INSERT INTO actividades(nombre, descripcion, aforo, precio_socio, precio_no_socio, fecha_ini, fecha_fin, deporte, instalaciÃ³n, periodo_inscripcion) VALUES 

('Torneo Padel I', 'Primer torneo de padel.' , '32', '8.50', '12.00', '2022-2-24', '2022-3-3', 'padel', '1', '1'), 

('Torneo Tenis I', 'Primer torneo de tenis.' , '24', '10.00', '15.00', '2022-2-27', '2022-3-10', 'tenis', '2', '2'), 

('Torneo Futbol I', 'Primer torneo de futbol.' , '100', '6.50', '10.00', '2022-3-17', '2022-3-27', 'futbol', '3', '3'); 
INSERT INTO periodos_inscripcion(fecha_ini_socio, fecha_fin_socio, fecha_fin_no_socio) VALUES 

('2022-2-24', '2022-3-24', '2022-3-30'), 

('2022-2-27', '2022-3-30', '2022-4-30'), 

('2022-3-17', '2022-3-30', '2022-4-30'); 
INSERT INTO inscripciones(persona, actividad, fecha) VALUES 

('12345678C', 1, '2022-2-23 17:15:10'), 

('12345678C', 2,'2022-2-25 18:50:41'), 

('13254768D', 3,'2022-2-26 20:05:23');


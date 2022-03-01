DELETE FROM sesiones;
DELETE FROM reservas;
DELETE FROM inscripciones;
DELETE FROM actividades;
DELETE FROM periodos_inscripcion;
DELETE FROM instalaciones;
DELETE FROM clientes;

-- INSERT INTO socios(id_socio, nombre, fecha_nacimiento, dni) VALUES 

-- (1, 'David', '2001-12-21' , '12345678C'), 

-- (2, 'Matias', '2005-6-24', '87654321F'), 

-- (3, 'Juan', '1972-7-15', '13254768D'); 

INSERT INTO clientes(dni, id_socio, nombre, fecha_nacimiento) VALUES 

('58438791C',1,'Alvaro', '2001-12-21' ), 

('54487543A',NULL,'Daniel', '2005-6-24'), 

('46739273H',2,'Roberto', '1972-7-15'), 

('52347843G',NULL,'Martín', '2006-6-24');
INSERT INTO instalaciones(id_instalacion, nombre, deporte, precio) VALUES 

(1,'Pista de tenis 1', 'Tenis', 7), 

(2,'Pista de tenis 2', 'Tenis', 5.3), 

(3, 'Pista de padel 1', 'Padel', 10.50),

(4, 'Pista de futbol 1', 'Futbol', 3.50);  
INSERT INTO reservas(id_reserva, persona, instalacion, fecha, fecha_reserva, precio,actividad) VALUES 

(1,NULL, 3, '2022-2-23', '2022-3-25 17:00:00', '6.25' ,1), 

(2,2, 2,'2022-2-25', '2022-3-27 20:00:00', '4.5' ,0), 

(3,2, 3,'2022-2-26', '2022-3-24 19:00:00', '3.2' ,0),
 
(4,NULL, 4,'2022-2-26', '2022-3-24 15:00:00', '3.2' ,3),

(5,NULL, 1, '2022-2-23', '2022-3-25 17:00:00', '6.25' ,2); 
INSERT INTO periodos_inscripcion(id_periodo_inscripcion, nombre, descripcion, fecha_ini_socio, fecha_fin_socio, fecha_fin_no_socio) VALUES 

(1, 'Periodo 1', 'Periodo para verano', '2022-2-24', '2022-3-24', '2022-3-30'), 

(2, 'Periodo 2', 'Periodo para otoño','2022-2-27', '2022-3-30', '2022-4-30'), 

(3, 'Periodo 3', 'Periodo para invierno','2022-3-17', '2022-3-30', '2022-4-30'); 
INSERT INTO actividades(id_actividad, nombre, descripcion, aforo, precio_socio, precio_no_socio, fecha_ini, fecha_fin, deporte, instalacion, periodo_inscripcion) VALUES 

(1,'Torneo Padel I', 'Primer torneo de padel.' , '32', '8.50', '12.00', '2022-2-24', '2022-3-3', 'Padel', '3', '1'), 

(2,'Torneo Tenis I', 'Primer torneo de tenis.' , '24', '10.00', '15.00', '2022-2-27', '2022-3-10', 'Tenis', '1', '2'), 

(3,'Torneo Futbol I', 'Primer torneo de futbol.' , '100', '6.50', '10.00', '2022-3-17', '2022-3-27', 'Futbol', '4', '3'),

(4,'Torneo Futbol II', 'Segundo torneo de futbol.' , '50', '7.50', '15.00', '2022-3-24', '2022-3-30', 'Futbol', '4', '3'); 

INSERT INTO inscripciones(id_inscripcion, persona, actividad, fecha) VALUES 

(1,'52347843G', 1, '2022-2-23 17:15:10'), 

(2,'58438791C', 2,'2022-2-25 18:50:41'), 

(3,'54487543A', 3,'2022-2-26 20:05:23');


INSERT INTO sesiones(id_sesion, dia, hora_ini, hora_fin, actividad) VALUES 

(1, 'Lunes', '9:00:00', '11:00:00', '1'), 

(2, 'Miercoles', '16:00:00', '18:00:00', '1'), 

(3, 'Viernes', '9:00:00', '12:00:00', '2'),

(4, 'Sabado', '20:00:00', '21:00:00', '3'); 


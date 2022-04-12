DELETE FROM sesiones;
DELETE FROM reservas;
DELETE FROM inscripciones;
DELETE FROM actividades;
DELETE FROM periodos_inscripcion;
DELETE FROM instalaciones;
DELETE FROM clientes;
DELETE FROM pagos;

-- INSERT INTO socios(id_socio, nombre, fecha_nacimiento, dni) VALUES 

-- (1, 'David', '2001-12-21' , '12345678C'), 

-- (2, 'Matias', '2005-6-24', '87654321F'), 

-- (3, 'Juan', '1972-7-15', '13254768D'); 

INSERT INTO clientes(dni, id_socio, nombre, fecha_nacimiento, moroso, contraseña, cuotaInicial, cuotaReservas, cuotaActividades, tlf,  direccion) VALUES 

('58438791C',1,'Alvaro', '2001-12-21', 1, '58438791C', 30.30, 0.0 , 0.0, '603441826', 'Carretera carbonera, 69, 5G'), 

('54487543A',NULL,'Daniel', null, 0, null, null , null, null, '123456789', null ), 

('46739273H',2,'Roberto', '1972-7-15', 0, '46739273H',30.30, 22.95, 0.0, '603666826', 'Carretera carbonera, 71, 5G'), 

('52347843G',NULL,'Martín', null, 0, null, null, null, null, '987654321', null ),

('58428791E',3,'Mario', null, 0, '58428791E',30.30, 0.0, 0.0, '603666829', 'Fray ceferino, 35, 1A'),

('a', 0, 'Dani', null, 0, 'a', 30.30, 0.0, 0.0, '403666829', 'Fray ceferino, 35, 2A') ;

INSERT INTO instalaciones(id_instalacion, nombre, deporte, precio) VALUES 

(1,'Pista de tenis 1', 'Tenis', 7), 

(2,'Pista de tenis 2', 'Tenis', 5.3), 

(3, 'Pista de padel 1', 'Padel', 10.50),

(4, 'Pista de futbol 1', 'Futbol', 3.50);  
INSERT INTO reservas(id_reserva, persona, instalacion, fecha, fecha_reserva, precio,actividad) VALUES 

(0,NULL,NULL,NULL,NULL,NULL,NULL),

(1,NULL, 3, '2022-2-23', '2022-4-8 17:00:00', '6.25' ,1), 

(2,2, 2,'2022-3-26', '2022-4-13 20:00:00', '4.50' ,0),

(3,2, 3,'2022-3-21', '2022-3-31 19:00:00', '3.20' ,0),
 
(4,NULL, 4,'2022-2-26', '2022-4-9 15:00:00', '3.20' ,3),

(5,NULL, 1, '2022-2-23', '2022-4-20 17:00:00', '6.25' ,2),

(6,2, 2,'2022-3-29', '2022-4-14 21:00:00', '4.50' ,0),

(7,2, 3,'2022-2-25', '2022-3-28 10:00:00', '6.25' ,0),

(8,NULL, 3,'2022-2-25', '2022-3-2 16:00:00', '6.25' ,2),

(9,1, 3,'2022-2-25', '2022-2-28 10:00:00', '6.25' ,0),
(10, 2, 4,'2022-4-2', '2022-4-8 14:00:00', '4.50' ,0);

 
INSERT INTO periodos_inscripcion(id_periodo_inscripcion, nombre, descripcion, fecha_ini_socio, fecha_fin_socio, fecha_fin_no_socio) VALUES 

(1, 'Periodo 1', 'Periodo para verano', '2022-2-24', '2022-3-24', '2022-3-30'), 

(2, 'Periodo 2', 'Periodo para otoño','2022-2-27', '2022-3-30', '2022-4-30'), 

(3, 'Periodo 3', 'Periodo para invierno','2022-3-17', '2022-3-30', '2022-4-30'); 
INSERT INTO actividades(id_actividad, nombre, descripcion, aforo, plazas, precio_socio, precio_no_socio, fecha_ini, fecha_fin, deporte, instalacion, periodo_inscripcion) VALUES 

(1,'Torneo Padel I', 'Primer torneo de padel.' , '32' , '32', '8.50', '12.00', '2022-2-24', '2022-3-3', 'Padel', '3', '1'), 

(2,'Torneo Tenis I', 'Primer torneo de tenis.' , '24' , '24', '10.00', '15.00', '2022-2-27', '2022-3-10', 'Tenis', '1', '2'), 

(3,'Torneo Futbol I', 'Primer torneo de futbol.' , '100' , '100', '6.50', '10.00', '2022-3-17', '2022-3-27', 'Futbol', '4', '3'),

(4,'Torneo Futbol II', 'Segundo torneo de futbol.' , '50' , '50', '7.50', '15.00', '2022-3-24', '2022-3-30', 'Futbol', '4', '3'); 

INSERT INTO inscripciones(id_inscripcion, persona, actividad, fecha) VALUES 

(1,'52347843G', 1, '2022-2-23 17:15:10'), 

(2,'58438791C', 2,'2022-2-25 18:50:41'), 

(3,'54487543A', 3,'2022-2-26 20:05:23'),

(4,'46739273H', 1, '2022-2-23 17:15:10'),

(5,'46739273H', 2, '2022-3-30 20:28:15');


INSERT INTO sesiones(id_sesion, dia, hora_ini, hora_fin, actividad) VALUES 

(1, 'lunes', '9:00:00', '11:00:00', '1'), 

(2, 'miercoles', '16:00:00', '18:00:00', '1'), 

(3, 'viernes', '9:00:00', '12:00:00', '2'),

(4, 'sabado', '20:00:00', '21:00:00', '3'); 

INSERT INTO pagos(id_pago, fecha, persona, inscripcion, reserva) VALUES

(1, '2022-03-14', '46739273H', NULL, 6),

(2, '2022-03-14', '46739273H', NULL, 3),

(3, '2022-03-10', '54487543A', 3, 0),

(4, '2022-02-26', '58438791C', 2, 0),

(5, '2022-02-25', '46739273H', NULL, 7),

(6, '2022-03-29', '46739273H', NULL, 6),

(7, '2022-03-25', '46739273H', 4, 0);







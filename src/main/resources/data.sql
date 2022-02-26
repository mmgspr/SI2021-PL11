CREATE SCHEMA IF NOT EXISTS db_test; 

  

  

CREATE TABLE  db_test.socios( 

id_socio integer unsigned auto_increment primary key, 

    nombre varchar(50), 

    fecha_nacimiento date, 

    dni varchar(9) 

); 

  

INSERT INTO db_test.socios(nombre, fecha_nacimiento, dni) VALUES 

('David', '2001-12-21' , '12345678C'), 

('Matias', '2005-6-24', '87654321F'), 

('Juan', '1972-7-15', '13254768D'); 

  

CREATE TABLE db_test.cliente( 

id_dni_cliente varchar(9) unique primary key, 

    nombre varchar(50), 

    fecha_nacimiento date 

); 

  

INSERT INTO db_test.cliente(nombre, fecha_nacimiento, id_dni_cliente) VALUES 

('Alvaro', '2001-12-21' , '18275678C'), 

('Daniel', '2005-6-24', '99654321F'), 

('Roberto', '1972-7-15', '21654768D'); 

  

  

CREATE TABLE db_test.instalacion( 

id_instalacion integer unsigned auto_increment primary key, 

    nombre varchar(50), 

    deporte varchar(20), 

    precio decimal(10,2) 

   ); 

  

INSERT INTO db_test.instalacion(nombre, deporte, precio) VALUES 

('Pista de tenis 1', 'tenis', 7), 

('Pista de tenis 2', 'tenis', 5.3), 

('Pista de padel 1', 'padel', 10.50); 

  

  

CREATE TABLE db_test.reserva( 

id_reserva integer unsigned auto_increment primary key, 

    foreign key (socio) references socios(id_socio), 

    foreign key (instalacion) references instalacion(id_instalacion), 

    fecha datetime, 

    fecha_reserva datetime 

   ); 

  

INSERT INTO db_test.reserva(socio, instalacion, fecha, fecha_reserva) VALUES 

(1, 1, '2022-2-23 17:15:10', '2022-2-24 17:00:00'), 

(2, 2,'2022-2-25 18:50:41', '2022-2-27 20:00:00'), 

(3, 3,'2022-2-26 20:05:23', '2022-2-27 19:00:00'); 

 

CREATE TABLE db_test.actividad( 

    id_actividad  integer unsigned auto_increment primary key, 

    nombre varchar(50), 

    descripcion varchar(200), 

    aforo integer, 

    precio_socio decimal(10,2), 

    precio_no_socio decimal(10,2), 

    fecha_ini date, 

    fecha_fin date, 

    deporte varchar(20), 

    foreign key (instalacion) references instalacion(id_instalacion), 

    foreign key (periodo_inscripcion) references periodo_inscripcion(id_periodo_inscripcion) 

    ); 

  

INSERT INTO db_test.actividad(nombre, descripcion, aforo, precio_socio, precio_no_socio, fecha_ini, fecha_fin, deporte, instalaciÃ³n, periodo_inscripcion) VALUES 

('Torneo Padel I', 'Primer torneo de padel.' , '32', '8.50', '12.00', '2022-2-24', '2022-3-3', 'padel', '1', '1'), 

('Torneo Tenis I', 'Primer torneo de tenis.' , '24', '10.00', '15.00', '2022-2-27', '2022-3-10', 'tenis', '2', '2'), 

('Torneo Futbol I', 'Primer torneo de futbol.' , '100', '6.50', '10.00', '2022-3-17', '2022-3-27', 'futbol', '3', '3'); 

 

 

CREATE TABLE db_test.periodo_inscripcion( 

    id_periodo_inscripcion integer unsigned auto_increment primary key, 

    fecha_ini_socio date, 

    fecha_fin_socio date, 

    fecha_fin_no_socio date 

); 

  

 

INSERT INTO db_test.periodo_inscripcion(fecha_ini_socio, fecha_fin_socio, fecha_fin_no_socio) VALUES 

('2022-2-24', '2022-3-24', '2022-3-30'), 

('2022-2-27', '2022-3-30', '2022-4-30'), 

('2022-3-17', '2022-3-30', '2022-4-30'); 

 

CREATE TABLE db_test.inscripcion( 

id_inscripcion integer unsigned auto_increment primary key, 

    foreign key (persona) references socios(dni), 

    foreign key (actividad) references actividad(id_actividad), 

    fecha datetime 

   ); 

INSERT INTO db_test.inscripcion(persona, actividad, fecha) VALUES 

('12345678C', 1, '2022-2-23 17:15:10'), 

('12345678C', 2,'2022-2-25 18:50:41'), 

('13254768D', 3,'2022-2-26 20:05:23');


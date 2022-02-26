DROP TABLE socios;
DROP TABLE clientes;
DROP TABLE instalaciones;
DROP TABLE reservas;
DROP TABLE periodos_inscripcion;
DROP TABLE actividades;
DROP TABLE inscripciones;

CREATE TABLE socios( 

id_socio integer unsigned auto_increment primary key, 

    nombre varchar(50), 

    fecha_nacimiento date, 

    dni varchar(9) 

);
CREATE TABLE clientes( 

id_dni_cliente varchar(9) unique primary key, 

    nombre varchar(50), 

    fecha_nacimiento date 

); 
CREATE TABLE instalaciones( 

    id_instalacion integer unsigned auto_increment primary key, 

    nombre varchar(50), 

    deporte varchar(20), 

    precio decimal(10,2) 

   );
CREATE TABLE reservas( 

id_reserva integer unsigned auto_increment primary key, 

    foreign key (socio) references socios(id_socio), 

    foreign key (instalacion) references instalaciones(id_instalacion), 

    fecha datetime, 

    fecha_reserva datetime 

   ); 
CREATE TABLE periodos_inscripcion( 

    id_periodo_inscripcion integer unsigned auto_increment primary key, 

    fecha_ini_socio date, 

    fecha_fin_socio date, 

    fecha_fin_no_socio date 

); 
CREATE TABLE actividades( 

    id_actividad  integer unsigned auto_increment primary key, 

    nombre varchar(50), 

    descripcion varchar(200), 

    aforo integer, 

    precio_socio decimal(10,2), 

    precio_no_socio decimal(10,2), 

    fecha_ini date, 

    fecha_fin date, 

    deporte varchar(20), 

    foreign key (instalacion) references instalaciones(id_instalacion) 

    foreign key (periodo_inscripcion) references periodos_inscripcion(id_periodo_inscripcion) 

    );
CREATE TABLE inscripciones( 

id_inscripcion integer unsigned auto_increment primary key, 

    foreign key (persona) references socios(dni), 

    foreign key (actividad) references actividades(id_actividad), 

    fecha datetime 

   ); 
DROP TABLE IF EXISTS reservas;
DROP TABLE IF EXISTS inscripciones;
DROP TABLE IF EXISTS actividades;
DROP TABLE IF EXISTS socios;
DROP TABLE IF EXISTS periodos_inscripcion;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS instalaciones;
-- CREATE TABLE socios( 

-- id_socio integer unsigned unique not null primary key, 

--    nombre varchar(50), 

--    fecha_nacimiento date, 

--     dni varchar(9) 

-- );
CREATE TABLE clientes( 

    dni varchar(9) unique not null primary key,

    id_socio integer unsigned unique, 

    nombre varchar(50), 

    fecha_nacimiento date

); 
CREATE TABLE instalaciones( 

    id_instalacion integer unsigned unique not null primary key, 

    nombre varchar(50), 

    deporte varchar(20), 

    precio decimal(10,2) 

   );
CREATE TABLE reservas( 

    id_reserva integer unsigned unique not null primary key, 

    persona integer unsigned,
    
    instalacion integer unsigned,

    foreign key (persona) references clientes(id_socio), 

    foreign key (instalacion) references instalaciones(id_instalacion), 

    fecha datetime, 

    fecha_reserva datetime, 
    
    precio varchar(10), 
    
    actividad integer unsigned

   ); 
CREATE TABLE periodos_inscripcion( 

    id_periodo_inscripcion integer unsigned unique not null primary key, 

	nombre varchar(50), 

    descripcion varchar(200), 

    fecha_ini_socio date, 

    fecha_fin_socio date, 

    fecha_fin_no_socio date 

); 
CREATE TABLE actividades( 

    id_actividad  integer unsigned unique not null primary key, 

    nombre varchar(50), 

    descripcion varchar(200), 

    aforo integer, 

    precio_socio decimal(10,2), 

    precio_no_socio decimal(10,2), 

    fecha_ini date, 

    fecha_fin date, 

    deporte varchar(20), 
    
    instalacion integer unsigned,
    
    periodo_inscripcion integer unsigned,

    foreign key (instalacion) references instalaciones(id_instalacion),

    foreign key (periodo_inscripcion) references periodos_inscripcion(id_periodo_inscripcion) 

    );
CREATE TABLE inscripciones( 

    id_inscripcion integer unsigned unique not null primary key, 

    persona varchar(9),
    
    actividad integer unsigned,

    foreign key (persona) references clientes(dni), 

    foreign key (actividad) references actividades(id_actividad), 

    fecha datetime 

   ); 

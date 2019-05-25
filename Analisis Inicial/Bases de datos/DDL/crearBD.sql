-- ************************************************************************
-- * CREACION DE TABLAS PROYECTO 1ºDAW JOSE GRACIA Y CARLOS ROBLES 2018-19*
-- ************************************************************************
 
 
 /**                                                                            
  * Descripción: SQL que crea todas las tablas necesarias para el proyecto.
  * Información detallada: Se crean todas las tablas necesarias para el funcionamiento del proyecto de 1ºDAW 2018-2019. (https://github.com/Josee9988/1DAW-ProyectoFinal-CaJo)
  * @author  Jose Gracia
  * @version 1.0 Jose Gracia 03/03/2019
  * @since   0.5
  * @return          No tiene parámetro de salida
  */


-- creamos la base de datos; ponemos el prefijo m_ por necesidades del centro y poderlo ejecutar en equipos del aula
CREATE DATABASE IF NOT EXISTS m_inventariojc;


-- usar base de datos proyecto
USE m_inventariojc;





/* ##### INICIO CREACIÓN DE TABLAS ##### */
-- PERSONA
CREATE TABLE IF NOT EXISTS Persona(
    DNI CHAR(9) NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Apelllidos VARCHAR(50) NOT NULL,
    Identificador CHAR(9) NOT NULL,
    Passwd VARCHAR(30) NOT NULL,
    ROL VARCHAR(50) NOT NULL,
PRIMARY KEy(DNI)
);


-- PROFESORES
CREATE TABLE IF NOT EXISTS Profesores(
    DNI CHAR(9) NOT NULL,
PRIMARY KEY(DNI),
FOREIGN KEY(DNI) REFERENCES Persona(DNI) ON UPDATE CASCADE ON DELETE CASCADE
);


-- ADMINISTRADORES
CREATE TABLE IF NOT EXISTS Administradores(
    DNI CHAR(9) NOT NULL,
PRIMARY KEY(DNI),
FOREIGN KEY(DNI) REFERENCES Persona(DNI) ON UPDATE CASCADE ON DELETE CASCADE
);


-- MANTENIMIENTO
CREATE TABLE IF NOT EXISTS Mantenimiento(
    DNI CHAR(9) NOT NULL,
PRIMARY KEY(DNI),
FOREIGN KEY(DNI) REFERENCES Persona(DNI) ON UPDATE CASCADE ON DELETE CASCADE
);


-- JEFEDEPARTAMENTO
CREATE TABLE IF NOT EXISTS JefeDepartamento(
    DNI CHAR(9) NOT NULL,
PRIMARY KEY(DNI),
FOREIGN KEY(DNI) REFERENCES Persona(DNI) ON UPDATE CASCADE ON DELETE CASCADE
);


-- UBICACION
CREATE TABLE IF NOT EXISTS Ubicacion(
    Codigo CHAR(9) NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Descripcion VARCHAR(512) NOT NULL,
    Edificio VARCHAR(80) NOT NULL,
    Planta INTEGER NOT NULL,
PRIMARY KEY(Codigo)
);


-- MENSAJES
CREATE TABLE IF NOT EXISTS Mensajes(
    Codigo CHAR(9) NOT NULL,
    Asunto VARCHAR(128) NOT NULL,
    Cuerpo VARCHAR(2048) NOT NULL,
    Fecha DATE NOT NULL,
PRIMARY KEY(Codigo)
);


-- INCIDENCIA
CREATE TABLE IF NOT EXISTS Incidencia(
    Codigo CHAR(9) NOT NULL,
    cod_ubicacion CHAR(9) NOT NULL,
    DNI CHAR(9) NOT NULL,
    Descripcion VARCHAR(512) NOT NULL,
    nivel_urgencia VARCHAR(50) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    EstadoIncidencia VARCHAR(50) NOT NULL,
    fechaIncidencia DATE NOT NULL,
    fechaResolucion DATE NOT NULL,
    observaciones_resolucion VARCHAR(512) NOT NULL,
PRIMARY KEY(Codigo, cod_ubicacion, DNI),
FOREIGN KEY(DNI) REFERENCES PERSONA(DNI) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(cod_ubicacion) REFERENCES Ubicacion(Codigo) ON UPDATE CASCADE ON DELETE CASCADE
);


-- PROVEEDORES
CREATE TABLE IF NOT EXISTS Proveedores(
    Codigo char(9) NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Direccion VARCHAR(128) NOT NULL,
    Poblacion VARCHAR(128) NOT NULL,
    Telefono CHAR(9) NOT NULL,
    PagWeb VARCHAR(50) NOT NULL,
    CorreoElectronico VARCHAR(50) NOT NULL,
    Contacto VARCHAR(50) NOT NULL,
PRIMARY KEY(Codigo)
);


-- PEDIDOS
CREATE TABLE IF NOT EXISTS Pedidos(
    Codigo CHAR(9) NOT NULL,
    FechaPedido DATE NOT NULL,
    Codigo_prov CHAR(9) NOT NULL,
PRIMARY KEY(Codigo, Codigo_prov),
FOREIGN KEY(Codigo_prov) REFERENCES Proveedores(Codigo) ON UPDATE CASCADE ON DELETE CASCADE
);


-- LINEA PEDIDO
CREATE TABLE IF NOT EXISTS LineaPedido(
    Codigo_linea CHAR(9) NOT NULL,
    Codigo_pedido CHAR(9) NOT NULL,
    NumeroPedido INT NOT NULL,
    Fecha DATE NOT NULL,
    Articulo VARCHAR(50) NOT NULL,
    FechaPedido DATE NOT NULL,
    Cantidad INT NOT NULL,
PRIMARY KEY(Codigo_linea, Codigo_pedido),
FOREIGN KEY(Codigo_pedido) REFERENCES Pedidos(Codigo) ON UPDATE CASCADE ON DELETE CASCADE
);


-- MATERIAL INFORMATICO
CREATE TABLE IF NOT EXISTS MaterialInformatico(
    Codigo CHAR(9) NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Descripcion VARCHAR(512) NOT NULL,
    FechaAlta DATE NOT NULL,
    FechaBaja DATE NOT NULL,
    baja boolean NOT NULL,
    MotivoBaja VARCHAR(50),
    Precio DECIMAL NOT NULL,
    MotivoPeticion VARCHAR(128) NOT NULL,
PRIMARY KEY(Codigo)
);


-- MATERIAL INVENTARIABLE
CREATE TABLE IF NOT EXISTS MaterialInventariable(
    Codigo_material Char(9) NOT NULL,
PRIMARY KEY(Codigo_material),
FOREIGN KEY(Codigo_material) REFERENCES MaterialInformatico(Codigo) ON UPDATE CASCADE ON DELETE CASCADE
);


-- MATERIAL NO INVENTARIABLE
CREATE TABLE IF NOT EXISTS MaterialNoInventariable(
    Codigo_material Char(9) NOT NULL,
PRIMARY KEY(Codigo_material),
FOREIGN KEY(Codigo_material) REFERENCES MaterialInformatico(Codigo) ON UPDATE CASCADE ON DELETE CASCADE
);


-- SOFTWARE
CREATE TABLE IF NOT EXISTS Software(
    Codigo_material Char(9) NOT NULL,
    VersionActual VARCHAR(50) NOT NULL,
    Licencia VARCHAR(50) NOT NULL,
    Caducidad DATE NOT NULL,
PRIMARY KEY(Codigo_material),
FOREIGN KEY(Codigo_material) REFERENCES MaterialInformatico(Codigo) ON UPDATE CASCADE ON DELETE CASCADE
);


-- HARDWARE
CREATE TABLE IF NOT EXISTS Hardware(
    Codigo_material Char(9) NOT NULL,
    UnidadesDisponibles INT NOT NULL,
    AnyoAdquisicion INT NOT NULL,
PRIMARY KEY(Codigo_material),
FOREIGN KEY(Codigo_material) REFERENCES MaterialInformatico(Codigo) ON UPDATE CASCADE ON DELETE CASCADE
);


-- SOBREMESA 
CREATE TABLE IF NOT EXISTS Sobremesa(
    Codigo_material Char(9) NOT NULL,
    PlacaBase VARCHAR(80) NOT NULL,
    Procesador VARCHAR(50) NOT NULL,
    Grafica VARCHAR(128) NOT NULL,
    DiscosDuros VARCHAR(128) NOT NULL,
    TarjetaRed VARCHAR(50) NOT NULL,
    TarjetaSonido VARCHAR(50) NOT NULL,
    FuenteAlimentacion VARCHAR(50) NOT NULL,
    Caja VARCHAR(50) NOT NULL,
    Grabadora VARCHAR(50) NOT NULL,
    Disquetera VARCHAR(50) NOT NULL,
    Conectividad VARCHAR(128) NOT NULL,
PRIMARY KEY(Codigo_material),
FOREIGN KEY(Codigo_material) REFERENCES MaterialInformatico(Codigo) ON UPDATE CASCADE ON DELETE CASCADE
);


-- PORTATIL
CREATE TABLE IF NOT EXISTS Portatil(
    Codigo_material Char(9) NOT NULL,
    PlacaBase VARCHAR(80) NOT NULL,
    Procesador VARCHAR(50) NOT NULL,
    Grafica VARCHAR(128) NOT NULL,
    DiscosDuros VARCHAR(128) NOT NULL,
    TarjetaRed VARCHAR(50) NOT NULL,
    TarjetaSonido VARCHAR(50) NOT NULL,
    FuenteAlimentacion VARCHAR(50) NOT NULL,
    Grabadora VARCHAR(50) NOT NULL,
    Disquetera VARCHAR(50) NOT NULL,
    Conectividad VARCHAR(128) NOT NULL,

PRIMARY KEY(Codigo_material),
FOREIGN KEY(Codigo_material) REFERENCES MaterialInformatico(Codigo) ON UPDATE CASCADE ON DELETE CASCADE
);


-- PETICION COMPRAS
CREATE TABLE IF NOT EXISTS PeticionCompras(
    Codigo CHAR(9) NOT NULL,
    DNI Char(9) NOT NULL,
    Motivo VARCHAR(128) NOT NULL,
PRIMARY KEY(Codigo, DNI),
FOREIGN KEY(DNI) REFERENCES Persona(DNI) ON UPDATE CASCADE ON DELETE CASCADE
);


-- GESTION MATERIAL
CREATE TABLE IF NOT EXISTS GestionMaterial(
    DNI Char(9) NOT NULL,
    Codigo CHAR(9) NOT NULL,
    Articulo VARCHAR(50) NOT NULL,
PRIMARY KEY(DNI, Codigo),
FOREIGN KEY(DNI) REFERENCES Persona(DNI) ON UPDATE CASCADE ON DELETE CASCADE
);


-- GESTION COMPRAS
CREATE TABLE IF NOT EXISTS GestionCompras(
    Codigo CHAR(9) NOT NULL,
    Nombre VARCHAR(50),
    Descripcion VARCHAR(512) NOT NULL,
    Stock INT NOT NULL,
    Pvp DECIMAL NOT NULL,
    Categoria VARCHAR(50) NOT NULL,
PRIMARY KEY(Codigo)
);


-- TIENE
CREATE TABLE IF NOT EXISTS Tiene(
    Codigo_pedidos CHAR(9) NOT NULL,
    Codigo_lineas CHAR(9) NOT NULL,
    Codigo_materials CHAR(9) NOT NULL UNIQUE,
PRIMARY KEY(Codigo_pedidos, Codigo_lineas),
FOREIGN KEY(Codigo_pedidos) REFERENCES Pedidos(Codigo) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(Codigo_lineas) REFERENCES LineaPedido(Codigo_linea) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT codigomaterials_unique FOREIGN KEY (Codigo_materials)REFERENCES MaterialInformatico(Codigo) ON UPDATE CASCADE ON DELETE CASCADE
);

/* ##### FIN CREACIÓN DE TABLAS ##### */




/* ##### INDEX ##### */
CREATE INDEX dnipersona ON Persona(DNI);
CREATE INDEX codmensajes ON Mensajes(Codigo);
CREATE INDEX codubicacion ON Ubicacion(Codigo);
CREATE INDEX codincidencia ON Incidencia(Codigo);
CREATE INDEX codpedido ON Pedidos(Codigo);
CREATE INDEX codlineapedido ON LineaPedido(Codigo_pedido);
CREATE INDEX codproveedores ON Proveedores(Codigo);
CREATE INDEX codmaterial ON MaterialInformatico(Codigo);
CREATE INDEX codpeticion ON PeticionCompras(Codigo);
CREATE INDEX codgestionmaterial ON GestionMaterial(Codigo);
CREATE INDEX codgestioncompras ON GestionCompras(Codigo);





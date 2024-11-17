drop database if exists ventas;
create database ventas;
use ventas;
create table usuario(
idUsuario int (11) auto_increment primary key,
nombre varchar(30) not null,
apellido varchar(30) not null,
usuario varchar(15) not null,
password varchar(15) not null,
telefono varchar(15) not null,
tipo int(1)	not null default 1
);
ALTER TABLE usuario MODIFY COLUMN password VARCHAR(64);
insert into usuario values(1,'root','root','root',SHA2('root',256),'1',0);


create table categoria(
idCategoria int (11) auto_increment primary key,
descripcion varchar(200) not null,
estado int(1) not null
);

create table producto(
idProducto int (11) auto_increment primary key,
codigo int(12)	not null,
nombre varchar(100) not null,
cantidad int(11) not null,
precio double(10,2) not null,
descripcion varchar(200) not null,
idCategoria int(11) not null,
estado int(1) not null,
iva int (2)	not null,
CONSTRAINT fk_idCategoria FOREIGN KEY (idCategoria) REFERENCES categoria(idCategoria) ON DELETE CASCADE ON UPDATE CASCADE
);
alter table producto add column minimo int not null;

create table venta(
idVenta int (11) auto_increment primary key,
valorPagar double(10,2) not null,
fechaVenta date not null
);

create table detalle_venta(
idDetalleVenta int (11) auto_increment primary key,
idVenta int (11) not null,
idProducto int (11) not null,
cantidad int(11) not null,
precioUnitario double(10,2) not null,
subtotal double(10,2) not null,
descuento double(10,2) not null,
totalPagar double(10,2) not null,
CONSTRAINT fk_idVenta FOREIGN KEY (idVenta) REFERENCES venta(idVenta) ON DELETE CASCADE ON UPDATE CASCADE
);

select * from usuario;
select * from producto;
select * from categoria;

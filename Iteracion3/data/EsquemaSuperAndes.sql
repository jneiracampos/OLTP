--- Sentencias SQL para la creacion del esquema de superAndes
--- Las tablas tienen prefijo A_ para facilitar su acceso desde SQL Developer

-- USO
-- Copie el contenido de este archivo en una pestana SQL de SQL Developer
-- Ejecutelo como un script - Utilice el boton correspondiente de la pestana utilizada

-- Creacion del secuenciador
create sequence superAndes_sequence;

-- Creacion de la tabla ROL y especificaciones de sus restricciones
create table A_ROL
(
  ID number(10) not null,
  NOMBRE_ROL varchar2(50) not null,
  DESCRIPCION varchar2(100) not null,
  constraint ROL_PK primary key (ID)
);


-- Creacion de la tabla USUARIO y especificaciones de sus restricciones
create table A_USUARIO
(
  ID number(10),
  NOMBRE varchar2(50) not null,
  CORREO varchar2(50) not null,
  PALABRA_CLAVE varchar2(50) not null,
  constraint PK_USUARIO primary key (ID)
);

-- Creacion de la tabla USUARIO_ROL y especificaciones de sus restricciones
create table A_USUARIO_ROL
(
  ID_USUARIO number(10) not null,
  ID_ROL number(10) not null,
  ID_SUCURSAL number,
  constraint PK_USUARIO_ROL primary key (ID_USUARIO, ID_ROL),
  constraint FK_USUARIO_ROL_ROL foreign key (ID_ROL) references A_ROL (ID_ROL),
  constraint FK_USUARIO_ROL_USUARIO foreign key (ID_USUARIO) references A_USUARIO (ID_USUARIO)
  constraint FK_USUARIO_ROL_SUCURSAL foreign key (ID_SUCURSAL) references A_SUCURSAL (ID_SUCURSAL)
);

-- Creacion de la tabla SUCURSAL y especificacion de sus restricciones
CREATE TABLE A_SUCURSAL
   (ID NUMBER, 
	NOMBRE VARCHAR2(255 BYTE), 
    CIUDAD VARCHAR2(255 BYTE),
    DIRECCION VARCHAR2(255 BYTE),
    CONSTRAINT A_SUCURSAL_PK PRIMARY KEY (ID));
	
ALTER TABLE A_SUCURSAL
	ADD CONSTRAINT UN_NOMBRE_SUCURSAL
	UNIQUE (NOMBRE)
ENABLE;

-- Creacion de la tabla CATEGORIA_PRODUCTO y especificacion de sus restricciones
CREATE TABLE A_CATEGORIA_PRODUCTO
   (ID NUMBER, 
	TIPO VARCHAR2(255 BYTE), 
	CONSTRAINT A_CATEGORIA_PRODUCTO_PK PRIMARY KEY (ID));

-- Creacion de la tabla PRODUCTO y especificacion de sus restricciones
CREATE TABLE A_PRODUCTO 
   (ID NUMBER, 
	NOMBRE VARCHAR2(255 BYTE), 
    MARCA VARCHAR2(255 BYTE),
    PRESENTACION VARCHAR2(255 BYTE),
    ID_CATEGORIA_PRODUCTO NUMBER,
    PRECIO_UNITARIO NUMBER,
    CANTIDAD_PRESENTACION NUMBER,
    VOLUMEN_EMPAQUE NUMBER,
    PESO_EMPAQUE NUMBER,
    PRECIO_POR_UNIDAD NUMBER,
	CONSTRAINT A_PRODUCTO_PK PRIMARY KEY (ID));
	
ALTER TABLE A_PRODUCTO
ADD CONSTRAINT fk_p_categoria_producto
    FOREIGN KEY (id_categoria_producto)
    REFERENCES a_categoria_producto(id)
ENABLE;

-- Creacion de la tabla PROVEEDOR y especificacion de sus restricciones
CREATE TABLE A_PROVEEDOR
   (ID NUMBER, 
	NOMBRE VARCHAR2(255 BYTE), 
	CONSTRAINT A_PROVEEDOR_PK PRIMARY KEY (ID));

-- Creacion de la tabla CLIENTE y especificacion de sus restricciones
CREATE TABLE A_CLIENTE 
(
  ID NUMBER,
  DIRECCION VARCHAR2(255 BYTE), 
  CORREO VARCHAR2(255 BYTE), 
  TIPO_CLIENTE VARCHAR2(20 BYTE), 
  PUNTOS NUMBER,
  PALABRA_CLAVE VARCHAR2(255 BYTE),
  CONSTRAINT A_CLIENTE_PK PRIMARY KEY (ID));

ALTER TABLE A_CLIENTE
ADD CONSTRAINT CK_TIPO_CLIENTE
    CHECK (TIPO_CLIENTE IN ('PERSONA NATURAL', 'PERSONA JURIDICA'))
ENABLE;

-- Creacion de la tabla CLIENTE_SUCURSAL y especificacion de sus restricciones
CREATE TABLE A_CLIENTE_SUCURSAL
(
  IDCLIENTE NUMBER,
  IDSUCURSAL NUMBER,
  CONSTRAINT A_CLIENTE_SUCURSAL_PK PRIMARY KEY (IDCLIENTE, IDSUCURSAL));

ALTER TABLE A_CLIENTE_SUCURSAL
ADD CONSTRAINT fk_cs_cliente
    FOREIGN KEY (idcliente)
    REFERENCES a_cliente(id)
ENABLE;
    
ALTER TABLE A_CLIENTE_SUCURSAL
ADD CONSTRAINT fk_cs_sucursal
    FOREIGN KEY (idsucursal)
    REFERENCES a_sucursal(id)
ENABLE;

-- Creacion de la tabla PROVEEDOR_SUCURSAL y especificacion de sus restricciones
CREATE TABLE A_PROVEEDOR_SUCURSAL
(
  IDPROVEEDOR NUMBER,
  IDSUCURSAL NUMBER,
  CONSTRAINT A_PROVEEDOR_SUCURSAL_PK PRIMARY KEY (IDPROVEEDOR, IDSUCURSAL));

ALTER TABLE A_PROVEEDOR_SUCURSAL
ADD CONSTRAINT fk_proveedorSucursal_proveedor
    FOREIGN KEY (idproveedor)
    REFERENCES a_proveedor(id)
ENABLE;

ALTER TABLE A_PROVEEDOR_SUCURSAL
ADD CONSTRAINT fk_proveedorSucursal_sucursal
    FOREIGN KEY (idsucursal)
    REFERENCES a_sucursal(id)
ENABLE;

-- Creacion de la tabla PRODUCTO_SUCURSAL y especificacion de sus restricciones
CREATE TABLE A_PRODUCTO_SUCURSAL
(
  IDPRODUCTO NUMBER,
  IDSUCURSAL NUMBER,
  CONSTRAINT A_PRODUCTO_SUCURSAL_PK PRIMARY KEY (IDPRODUCTO, IDSUCURSAL));

ALTER TABLE A_PRODUCTO_SUCURSAL
ADD CONSTRAINT fk_productoSucursal_producto
    FOREIGN KEY (idproducto)
    REFERENCES a_producto(id)
ENABLE;

ALTER TABLE A_PRODUCTO_SUCURSAL
ADD CONSTRAINT fk_productoSucursal_sucursal
    FOREIGN KEY (idsucursal)
    REFERENCES a_sucursal(id)
ENABLE;

-- Creacion de la tabla CATEGORIA_PRODUCTO_SUCURSAL y especificacion de sus restricciones
CREATE TABLE A_CATEGORIA_PRODUCTO_SUCURSAL
(
  IDCATEGORIA_PRODUCTO NUMBER,
  IDSUCURSAL NUMBER,
  CONSTRAINT A_CATEGORIA_PRODUCTO_SUCURSAL_PK PRIMARY KEY (IDCATEGORIA_PRODUCTO, IDSUCURSAL));

ALTER TABLE A_CATEGORIA_PRODUCTO_SUCURSAL
ADD CONSTRAINT fk_cps_categoria_producto
    FOREIGN KEY (idcategoria_producto)
    REFERENCES a_categoria_producto(id)
ENABLE;

ALTER TABLE A_CATEGORIA_PRODUCTO_SUCURSAL
ADD CONSTRAINT fk_cps_sucursal
    FOREIGN KEY (idsucursal)
    REFERENCES a_sucursal(id)
ENABLE;

-- Creacion de la tabla ORDEN_PEDIDO y especificacion de sus restricciones
CREATE TABLE A_ORDEN_PEDIDO
(
    ID NUMBER,
    FECHA_ESPERADA_ENTREGA DATE,
    FECHA_ENTREGA DATE,
    CALIFICACION NUMBER,
    IDSUCURSAL NUMBER,
    IDPROVEEDOR NUMBER,
    CONSTRAINT A_ORDEN_PEDIDO_PK PRIMARY KEY (ID));

ALTER TABLE A_ORDEN_PEDIDO
ADD CONSTRAINT fk_op_sucursal
    FOREIGN KEY (idsucursal)
    REFERENCES a_sucursal(id)
ENABLE;

ALTER TABLE A_ORDEN_PEDIDO
ADD CONSTRAINT fk_op_proveedor
    FOREIGN KEY (idproveedor)
    REFERENCES a_proveedor(id)
ENABLE;

-- Creacion de la tabla ACUERDO_VENTA y especificacion de sus restricciones
CREATE TABLE A_ACUERDO_VENTA
(
    ID_ORDEN_PEDIDO NUMBER,
    ID_PRODUCTO NUMBER,
    CANTIDAD NUMBER,
    PRECIO NUMBER,
    CONSTRAINT A_ACUERDO_VENTA_PK PRIMARY KEY (ID_ORDEN_PEDIDO, ID_PRODUCTO));

ALTER TABLE A_ACUERDO_VENTA
ADD CONSTRAINT fk_av_producto
    FOREIGN KEY (idproducto)
    REFERENCES a_producto(id)
ENABLE;

ALTER TABLE A_ACUERDO_VENTA
ADD CONSTRAINT fk_av_orden_pedido
    FOREIGN KEY (idorden_pedido)
    REFERENCES a_orden_pedido(id)
ENABLE;

-- Creacion de la tabla PROMOCION y especificacion de sus restricciones
CREATE TABLE A_PROMOCION
(
    ID NUMBER,
    IDPRODUCTO NUMBER,
    FECHA_FIN VARCHAR2(255 BYTE),
    TIPO_PROMOCION VARCHAR2(20 BYTE),
    PROMOCION VARCHAR2(255 BYTE),
    IDSUCURSAL NUMBER,
    CONSTRAINT A_PROMOCION_PK PRIMARY KEY (ID));

ALTER TABLE A_PROMOCION
ADD CONSTRAINT fk_p_producto
    FOREIGN KEY (idproducto)
    REFERENCES a_producto(id)
ENABLE;

ALTER TABLE A_PROMOCION
ADD CONSTRAINT fk_p_sucursal
    FOREIGN KEY (idsucursal)
    REFERENCES a_sucursal(id)
ENABLE;

-- Creacion de la tabla FACTURA y especificacion de sus restricciones

CREATE TABLE A_FACTURA
(
    ID NUMBER,
    IDCLIENTE NUMBER,
    IDSUCURSAL NUMBER,
    SUBTOTAL NUMBER,
    TOTAL_PAGAR NUMBER,
    FECHA DATE,
    CONSTRAINT A_FACTURA_PK PRIMARY KEY (ID));

ALTER TABLE A_FACTURA
ADD CONSTRAINT fk_f_cliente
    FOREIGN KEY (idcliente)
    REFERENCES a_cliente(id)
ENABLE;

ALTER TABLE A_FACTURA
ADD CONSTRAINT fk_f_sucursal
    FOREIGN KEY (idsucursal)
    REFERENCES a_sucursal(id)
ENABLE;

-- Creacion de la tabla PRODUCTO_FACTURA y especificacion de sus restricciones
CREATE TABLE A_PRODUCTO_FACTURA
(
    IDFACTURA NUMBER,
    IDPRODUCTO NUMBER,
    CANTIDAD NUMBER,
    IDPROMOCION NUMBER,
    CONSTRAINT A_PRODUCTO_FACTURA_PK PRIMARY KEY (IDPRODUCTO, IDFACTURA));

ALTER TABLE A_PRODUCTO_FACTURA
ADD CONSTRAINT fk_pf_factura
    FOREIGN KEY (idfactura)
    REFERENCES a_factura(id)
ENABLE;

ALTER TABLE A_PRODUCTO_FACTURA
ADD CONSTRAINT fk_pf_producto
    FOREIGN KEY (idproducto)
    REFERENCES a_producto(id)
ENABLE;

ALTER TABLE A_PRODUCTO_FACTURA
ADD CONSTRAINT fk_pf_promocion
    FOREIGN KEY (idpromocion)
    REFERENCES a_promocion(id)
ENABLE;

-- Creacion de la tabla BODEGA y especificacion de sus restricciones
CREATE TABLE A_BODEGA
(
    ID NUMBER,
    CAPACIDAD_TOTAL NUMBER,
    CAPACIDAD_DISPONIBLE NUMBER,
    ID_CATEGORIA_PRODUCTO NUMBER,
    IDSUCURSAL NUMBER,
    CONSTRAINT A_BODEGA_PK PRIMARY KEY (ID));

ALTER TABLE A_BODEGA
ADD CONSTRAINT fk_b_categoria_producto
    FOREIGN KEY (id_categoria_producto)
    REFERENCES a_categoria_producto(id)
ENABLE;

ALTER TABLE A_BODEGA
ADD CONSTRAINT fk_b_sucursal
    FOREIGN KEY (idsucursal)
    REFERENCES a_sucursal(id)
ENABLE;

-- Creacion de la tabla ESTANTE y especificacion de sus restricciones
CREATE TABLE A_ESTANTE
(
    ID NUMBER,
    CAPACIDAD_TOTAL NUMBER,
    NIVEL_ABASTECIMIENTO NUMBER,
    ID_CATEGORIA_PRODUCTO NUMBER,
    ID_SUCURSAL NUMBER,
    CONSTRAINT A_ESTANTE_PK PRIMARY KEY (ID));

ALTER TABLE A_ESTANTE
ADD CONSTRAINT fk_e_categoria_producto
    FOREIGN KEY (id_categoria_producto)
    REFERENCES a_categoria_producto(id)
ENABLE;

ALTER TABLE A_ESTANTE
ADD CONSTRAINT fk_e_sucursal
    FOREIGN KEY (id_sucursal)
    REFERENCES a_sucursal(id)
ENABLE;

-- Creacion de la tabla PRODUCTO_BODEGA y especificacion de sus restricciones
CREATE TABLE A_PRODUCTO_BODEGA
(
    IDBODEGA NUMBER,
    IDPRODUCTO NUMBER,
    CANTIDAD NUMBER,
    CONSTRAINT A_PRODUCTO_BODEGA_PK PRIMARY KEY (IDPRODUCTO, IDBODEGA));

ALTER TABLE A_PRODUCTO_BODEGA
ADD CONSTRAINT fk_pb_bodega
    FOREIGN KEY (idbodega)
    REFERENCES a_bodega(id)
ENABLE;

ALTER TABLE A_PRODUCTO_BODEGA
ADD CONSTRAINT fk_pb_producto
    FOREIGN KEY (idproducto)
    REFERENCES a_producto(id)
ENABLE;

-- Creacion de la tabla REABASTECER y especificacion de sus restricciones
CREATE TABLE A_REABASTECER
(
    IDBODEGA NUMBER,
    IDESTANTE NUMBER,
    CANTIDAD NUMBER,
    CONSTRAINT A_REABASTECER_PK PRIMARY KEY (IDBODEGA, IDESTANTE));

ALTER TABLE A_REABASTECER
ADD CONSTRAINT fk_r_bodega
    FOREIGN KEY (idbodega)
    REFERENCES a_bodega(id)
ENABLE;

ALTER TABLE A_REABASTECER
ADD CONSTRAINT fk_r_bestante
    FOREIGN KEY (idestante)
    REFERENCES a_estante(id)
ENABLE;

-- Creacion de la tabla CARRO_COMPRA y especificacion de sus restricciones
CREATE TABLE A_CARRO_COMPRA
(
    ID NUMBER,
    ID_CLIENTE NUMBER,
    ISACTIVO VARCHAR2(5 BYTE),
    ID_SUCURSAL NUMBER,
    CONSTRAINT A_CARRO_COMPRA_PK PRIMARY KEY (ID));

ALTER TABLE A_CARRO_COMPRA
ADD CONSTRAINT fk_cc_cliente
    FOREIGN KEY (id_cliente)
    REFERENCES a_cliente(id)
ENABLE;

ALTER TABLE A_CARRO_COMPRA
ADD CONSTRAINT CK_ACTIVO_CARRO
    CHECK (ISACTIVO IN ('TRUE', 'FALSE'))
ENABLE;

ALTER TABLE A_CARRO_COMPRA
ADD CONSTRAINT fk_cc_sucursal
    FOREIGN KEY (id_sucursal)
    REFERENCES a_sucursal(id)
ENABLE;

ALTER TABLE A_CARRO_COMPRA
ADD CONSTRAINT ND_ID_CLIENTE
    UNIQUE (ID_CLIENTE)
ENABLE;

-- Creacion de la tabla PRODUCTO_CARRO_COMPRA y especificacion de sus restricciones

CREATE TABLE A_PRODUCTO_CARRO_COMPRA
(
    ID_CARRO_COMPRA NUMBER,
    ID_PRODUCTO NUMBER,
    CANTIDAD NUMBER,
    CONSTRAINT A_PRODUCTO_CARRO_COMPRA_PK PRIMARY KEY (ID_CARRO_COMPRA, ID_PRODUCTO));

ALTER TABLE A_PRODUCTO_CARRO_COMPRA
ADD CONSTRAINT fk_pcc_carro_compra
    FOREIGN KEY (id_carro_compra)
    REFERENCES a_carro_compra(id)
ENABLE;

ALTER TABLE A_PRODUCTO_CARRO_COMPRA
ADD CONSTRAINT fk_pcc_producto
    FOREIGN KEY (id_producto)
    REFERENCES a_producto(id)
ENABLE;

COMMIT;

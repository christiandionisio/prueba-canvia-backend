
use canviaBD;
DROP PROCEDURE IF EXISTS `insertar_factura_detalle`;

delimiter %%

CREATE PROCEDURE insertar_factura_detalle(
        IN id_factura INT(11), 
        IN id_item INT(11),
        IN cantidad INT(11)
    )
    DETERMINISTIC
    NO SQL
BEGIN

    INSERT INTO factura_detalle (id_factura, id_item, cantidad) VALUES (id_factura, id_item, cantidad);
    SELECT ROW_COUNT();

END%%

delimiter ;
import React from 'react';
import Tabla from './TablaPromociones';

const FilaPromociones = ({ detallexpromo }) => {
    return (

        <tr>
            <td>{detallexpromo.id_promocion}</td>
            <td>{detallexpromo.nombre_producto}</td>
            <td>{detallexpromo.nombre_promocion}</td>
            <td>{detallexpromo.descuento}</td>
            <td>{detallexpromo.fecha_inicio}</td>
            <td>{detallexpromo.fecha_fin}</td>
        </tr>
    );
};

export default FilaPromociones;
import React from 'react';
import Tabla from './Tabla';

const Fila = ({ producto }) => {
    return (

        <tr>
            <td>{producto.id}</td>
            <td>{producto.nombre}</td>
            <td>{producto.precio}</td>
            <td>{producto.descripcion}</td>
        </tr>
    );
};

export default Fila;
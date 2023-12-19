import React from 'react';
import Fila from './Fila';
import 'bootstrap/dist/css/bootstrap.min.css';

const Tabla = ({ data }) => {
    return (
        <div>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Descripcion</th>
                    </tr>
                </thead>
                <tbody>
                    {data.length > 0 ? (
                        data.map((prod) => (
                            <Fila key={prod.id} producto={prod} id={prod.id} />
                        ))
                    ) : (
                        <tr>
                            <td colSpan="4">No se encuentran datos</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
};

export default Tabla;
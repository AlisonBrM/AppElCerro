import React from 'react';
import Fila from './FilaPromociones';
import 'bootstrap/dist/css/bootstrap.min.css';

const Tabla = ({ data }) => {
    return (
        <div>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>Codigo Promocion</th>
                        <th>Nombre</th>
                        <th>Producto</th>
                        <th>Descuento</th>
                        <th>Fecha de inicio</th>
                        <th>Fecha de fin</th>
                    </tr>
                </thead>
                <tbody>
                    {data.length > 0 ? (
                        data.map((prom) => (
                            <Fila key={prom.id} detallexpromo={prom} id={prom.id} />
                        ))
                    ) : (
                        <tr>
                            <td colSpan="6">No se encuentran datos</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
};

export default Tabla;
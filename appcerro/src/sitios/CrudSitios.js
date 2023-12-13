import React, {useState, useEffect} from 'react';
import Tabla from './Tabla';
import TablaPromociones from './TablaPromociones';
import Axios from 'axios';

const CrudSitios = () => {
    const [List, setList] = useState([]);
    const [ListPromociones, setListPromociones] = useState([]);
    useEffect(() => {
        const mostrar = async() => {
            try {
                const response = await Axios({
                    url:"http://localhost:8080/AppElCerro/resources/producto/productos"
                })
                setList(response.data);
                const responseI = await Axios({
                    url: "http://localhost:8080/AppElCerro/resources/detallexpromo/detallesxpromos"
                });
                setListPromociones(responseI.data);
            } catch (error) {
                console.log(error)
            }
        };
        mostrar();
    }

    )
    return (
        <div>
          <Tabla data = {List}/>  
          <TablaPromociones data = {ListPromociones}/>
        </div> 
    );
};

export default CrudSitios;

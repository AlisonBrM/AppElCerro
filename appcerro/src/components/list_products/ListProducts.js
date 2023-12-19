import React, { useState, useEffect } from 'react';
import CardProduct from './card_product/CardProduct';
import './ListProducts.css';
import { useParams } from 'react-router-dom';

function ListProducts() {
  const [data, setData] = useState([]);
  const { tipo } = useParams();  

  useEffect(() => {
    const fetchData = async () => {
      try {
        let apiUrl = 'http://localhost:8080/AppElCerro/resources/producto/productos';
        if (tipo) {
          apiUrl = `http://localhost:8080/AppElCerro/resources/producto/productostipo/${tipo}`;
        }

        const response = await fetch(apiUrl);
        if (!response.ok) {
          throw new Error('Error al obtener los datos');
        }
        const jsonData = await response.json();
        setData(jsonData);
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchData();
  }, [tipo]);
  return (
    <div className="list-products-title">
      <div className="listp-product-title">
        <h2>Inicio &gt; Lista de productos</h2>
      </div>
      <div className="listp-product-title2">
        <h2>Listado de productos</h2>
      </div>
      <div className="list-grid">
        {data.map(product => (
          <CardProduct
            key={product.id}
            name={product.nombre}
            description={product.descripcion}
            price={product.precio}
            url={product.img}
          />
        ))}
      </div>
    </div>
  );
}

export default ListProducts;

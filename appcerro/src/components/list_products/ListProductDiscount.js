import React, { useState, useEffect } from 'react';
import CardProduct from './card_discount/CardProductDiscount';
import './ListProductDiscount.css';
import { useParams } from 'react-router-dom';

function ListProductsDiscount() {
  const [data, setData] = useState([]);
  const { tipo } = useParams();  

  useEffect(() => {
    const fetchData = async () => {
      try {
        let apiUrl = 'http://localhost:8080/AppElCerro/resources/detallexpromo/detallesxpromos';
        if (tipo) {
          apiUrl = `http://localhost:8080/AppElCerro/resources/detallexpromo/detallesxpromos/${tipo}`;
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
    <div className="listp-product-discount-title">
      <div className="listp-product-discount-title">
        <h2>Inicio &gt; Lista de productos descuentos</h2>
      </div>
      <div className="listp-product-discount-title2">
        <h2>Listado de productos con descuento</h2>
      </div>
      <div className="list-grid">
        {data.map(productDiscount => (
          <CardProduct
            key={productDiscount.id_producto}
            name={productDiscount.nombre_producto}
            description={productDiscount.descripcion}
            discount={productDiscount.descuento}
            discountPrice={productDiscount.precio_descuento}
            price={productDiscount.precio_sin_descuento}
            url={productDiscount.img}
          />
        ))}
      </div>
    </div>
  );
}

export default ListProductsDiscount;
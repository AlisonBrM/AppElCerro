import React from "react";
import "./CardProductDiscount.css";

function CardProductDiscount({ name, description, price, discountPrice, discount, id, url }) {
  const onClick = () => {
    const value = localStorage.getItem('productsSelected');
    if (value == null) {
      localStorage.setItem('productsSelected', id);
    } else {
      localStorage.setItem('productsSelected', value + ";" + id);
    }
  };

  return (
    <div className="product-card">
      <div className="product-card-body">
        <div className="product-discountBadge">{discount}%</div>
        <img src={url} alt="muestra" />
      </div>
      <div className="product-title">{name}</div>
      <div className="product-description">{description}</div>
      <div className="product-discountPrice">$ {discountPrice}</div>
      <div className="product-price">$ {price}</div>
      <div className="product-footer">
        <button onClick={onClick}>Agregar</button>
      </div>
    </div>
  );
}

export default CardProductDiscount;

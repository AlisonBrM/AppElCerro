import React from 'react';
import { Link } from 'react-router-dom';
import './SectionCards.css';

function SectionCards() {
  const products = [
    { id: 1, imageSrc: 'https://i.postimg.cc/sDXJTMTs/1.png' },
    { id: 2, imageSrc: 'https://i.postimg.cc/mDhmtVtz/2.png' },
    { id: 3, imageSrc: 'https://i.postimg.cc/YC3RRTFL/3.png' },
    { id: 4, imageSrc: 'https://i.postimg.cc/Qd0g11Lp/4.png' },
    { id: 5, imageSrc: 'https://i.postimg.cc/pXPnCrJk/5.png' },
    { id: 6, imageSrc: 'https://i.postimg.cc/25nzgFzW/6.png' },
  ];

  return (
    <div className="card-grid">
      {products.map(product => (
        <Link to="/products" key={product.id_producto} className="card">
          <img src={product.imageSrc} className="card-img-top" alt={`Product ${product.id}`} />
          <div className="card-body">
            <h5 className="card-title">{`Product ${product.id}`}</h5>
          </div>
        </Link>
      ))}
    </div>
  );
}

export default SectionCards;

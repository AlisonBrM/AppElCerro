import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './Carrusel.css';

function Carrusel() {
  const [currentSlide, setCurrentSlide] = useState(0);

  const nextSlide = () => {
    setCurrentSlide((prevSlide) => (prevSlide + 1) % 3);
  };

  const previousSlide = () => {
    setCurrentSlide((prevSlide) => (prevSlide - 1 + 3) % 3);
  };

  return (
    <div className="slider-container">
      <Link to="/productsDiscount/1" className="slide-link">
        <img
          src="https://i.postimg.cc/4dDgQRm5/1.png"
          alt="img 1"
          className={currentSlide === 0 ? "slide showing" : "slide"}
        />
      </Link>
      <Link to="/productsDiscount/2" className="slide-link">
        <img
          src="https://i.postimg.cc/kXFmKZ9Q/2.png"
          alt="img 2"
          className={currentSlide === 1 ? "slide showing" : "slide"}
        />
      </Link>
      <Link to="/products/AseoPersonal" className="slide-link">
        <img
          src="https://i.postimg.cc/zX5mzjWV/3.png"
          alt="img 3"
          className={currentSlide === 2 ? "slide showing" : "slide"}
        />
      </Link>
      <div className="buttons">
        <button className="controls" onClick={previousSlide}>
          &lt;
        </button>
        <button className="controls" onClick={nextSlide}>
          &gt;
        </button>
      </div>
    </div>
  );
}

export default Carrusel;

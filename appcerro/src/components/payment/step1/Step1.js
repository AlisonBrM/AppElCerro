import React from "react";
import { useNavigate } from "react-router-dom";
import "./Step1.css"

const Step1 = () => {
    const navigate = useNavigate();

    const Total = localStorage.getItem("Total");

    const handleIconClick = () => {
        navigate("/payment/step1");
    };

    const handleNextClick = () => {
        navigate("/payment/step2");
    };

    return (
        <div className="step1-title">
            <div className="step1-title">
                <h2>Inicio &gt; Proceso de pago &gt; <span style={{backgroundColor: 'white', color: 'black'}}>Step1</span></h2>
            </div>
            <div className="step1-title2">
                <h2>Carrito de compras</h2>
            </div>
            <div className="icons">
                <button onClick={handleIconClick} className="icon-button">
                    <div className="icon-background">
                        <img src="https://i.postimg.cc/fRym7QLT/carrito-de-compras.png" alt="Carrito" />
                    </div>
                </button>
                <span className="arrow">→</span>
                <img src="https://i.postimg.cc/qRxWQvD2/usuario.png" alt="Usuario" />
                <span className="arrow">→</span>
                <img src="https://i.postimg.cc/YSrpHTcN/mano.png" alt="Mano" />
            </div>
            <div className="step1-title1">
                <h2>Lista de compras</h2>
            </div>
            <div className="total-and-button">
                <p>Total: ${Total}</p>
                <button onClick={handleNextClick} className="next-step-button">Continuar con la compra</button>
            </div>
        </div>
    );
};

export default Step1;

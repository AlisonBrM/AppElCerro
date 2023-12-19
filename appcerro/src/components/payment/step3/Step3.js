import React from 'react';
import { useNavigate } from "react-router-dom";
import "./Step3.css"

const Step3 = ({ formData, total }) => {
    const navigate = useNavigate();

    const bolsaPlastica = 60;
    const costoEnvio = 5000;
    const subtotal = total;

    const totalGeneral = subtotal + bolsaPlastica + costoEnvio;

    const handleIconClick = () => {
        navigate("/payment/step3");
    };

    const handleOrderConfirmation = () => {
        alert('¡Su pedido ha sido realizado exitosamente! Esperamos lo disfrute.');
    };

    return (
        <div className="step3-title">
            <div className="step3-title">
                <h2>Inicio &gt; Proceso de pago &gt; <span style={{ backgroundColor: 'white', color: 'black' }}>Step3</span></h2>
            </div>
            <div className="step3-title2">
                <h2>Pago Total </h2>
            </div>
            <div className="icons">
                <img src="https://i.postimg.cc/fRym7QLT/carrito-de-compras.png" alt="Carrito" />
                <span className="arrow">→</span>
                <img src="https://i.postimg.cc/qRxWQvD2/usuario.png" alt="Usuario" />
                <span className="arrow">→</span>
                <button onClick={handleIconClick} className="icon-button">
                    <div className="icon-background">
                        <img src="https://i.postimg.cc/YSrpHTcN/mano.png" alt="Mano" />
                    </div>
                </button>
            </div>
            <div className="step3-title3">
                <div style={{ padding: '10px', background: '#AB8742' }}>

                    <p>Tenga en cuenta que el pago es contra entrega, lo que significa que al momento de recibir su pedido debe hacer entrega del pago al domiciliario y estar atento al momento de la verificación, la cual le corresponde al domiciliario. </p>
                </div>

                <div className="step3-title4">
                    <div>
                        <p>Subtotal {subtotal}</p>
                    </div>
                    <div>
                        <p>Bolsa plástica {bolsaPlastica}</p>
                        <p>Envío {costoEnvio}</p>
                    </div>
                    <div className="step3-title5">
                    <div>
                        <p>TOTAL: {totalGeneral}</p>
                    </div>
                    </div>
                    </div>
                    <button onClick={handleOrderConfirmation}className="next-step-button">Hacer pedido</button>
            </div>
        </div>
    );
};

export default Step3;

import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Step2.css';

const Step2 = ({ formData, handleNextStep }) => {
    const [editedData, setEditedData] = useState({
        nombre: formData?.nombre || '',
        direccion: formData?.direccion || '',
        email: formData?.email || '',
        telefono: formData?.telefono || '',
    });

    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setEditedData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleIconClick = () => {
        navigate("/payment/step2");
    };

    const handleNextClick = () => {
        handleNextStep(editedData);
        navigate('/payment/step3');  
    };

    return (
        <div className="step2-title">
            <div className="step2-title">
                <h2>Inicio &gt; Proceso de pago &gt; <span style={{ backgroundColor: 'white', color: 'black' }}>Step2</span></h2>
            </div>
            <div className="step2-title2">
                <h2>Información Personal </h2>
            </div>
            <div className="icons">
                <img src="https://i.postimg.cc/fRym7QLT/carrito-de-compras.png" alt="Carrito" />
                <span className="arrow">→</span>
                <button onClick={handleIconClick} className="icon-button">
                    <div className="icon-background">
                        <img src="https://i.postimg.cc/qRxWQvD2/usuario.png" alt="Usuario" />
                    </div>
                </button>
                <span className="arrow">→</span>
                <img src="https://i.postimg.cc/YSrpHTcN/mano.png" alt="Mano" />
            </div>
            <div className="step2-title3">
                <div style={{ padding: '10px', background: '#AB8742' }}>
                    <h2>Realiza el siguiente formulario</h2>
                    <p>Realiza el formulario con tus datos personales, para cumplir de manera satisfactoria el proceso del envio de tu producto.</p>
                </div>
                <h3>Información personal</h3>
                <div className="input-field">
                    <div>
                        <label>
                            Nombre:
                            <input type="text" name="nombre" value={editedData.nombre} onChange={handleInputChange} />
                        </label>
                    </div>
                    <div>
                        <label>
                            Dirección:
                            <input type="text" name="direccion" value={editedData.direccion} onChange={handleInputChange} />
                        </label>
                    </div>
                    <div>
                        <label>
                            Email:
                            <input type="email" name="email" value={editedData.email} onChange={handleInputChange} />
                        </label>
                    </div>
                    <div>
                        <label>
                            Teléfono:
                            <input type="tel" name="telefono" value={editedData.telefono} onChange={handleInputChange} />
                        </label>
                    </div>
                </div>
                <button onClick={handleNextClick}className="next-step-button">Siguiente paso</button>
            </div>

        </div>
       
    );
};

export default Step2;

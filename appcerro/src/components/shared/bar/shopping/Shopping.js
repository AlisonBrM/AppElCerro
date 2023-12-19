import "./Shopping.css"
import ShoppingItem from "./shopping_item/ShoppingItem";
import { Link } from 'react-router-dom';

function Shopping({ isOpen, closeModal }) {
    if (!isOpen) return null;
    let productsSelect = localStorage.getItem('productsSelected')?.split(";")
    const listProducts = localStorage.getItem('allProducts')
    let productsObj
    if (productsSelect?.length >= 0) {
        productsObj = JSON.parse(listProducts).filter(objeto => productsSelect.includes(objeto.id));
    }

    return (
        <div className="modal">
            <div className="modal-container">
                <h1>Tu Carrito</h1>
                <button className="close" onClick={() => closeModal()}>X</button>
                {!productsSelect || productsSelect[0] === '' ? <h1>
                    No se han seleccionado productos
                </h1> : productsObj.map(product => {
                    return (
                        <ShoppingItem
                            key={product.id}
                            name={product.nombre}
                            description={product.descripcion}
                            price={product.precio}
                            id={product.id}
                            url={product.url}
                            quantity={productsSelect.filter(item => parseInt(item) === parseInt(product.id)).length}
                        />
                    )
                })}
                <div className="shopping-content">
                    <div className="shopping-total">
                        <p>Total</p>
                        <p>$200</p>
                    </div>
                    <Link to="/payment/step1" className="shopping-link">
                        <button onClick={() => closeModal()}>Ir a pagar</button>
                    </Link>
                </div>
            </div>
        </div>
    )
}

export default Shopping;


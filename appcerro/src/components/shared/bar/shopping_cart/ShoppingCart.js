import "./ShoppingCart.css"

function ShoppingCart({setIsModalOpen}) {
    return (
        <div className="shopping-cart">
            <span className="shopping-cart-item-count">0</span>
            <button onClick={() => setIsModalOpen(true)}><img src="https://i.postimg.cc/SR7z8Rgk/carrito.png" alt="Icono de carrito de compras"></img></button>
        </div>
    );
}

export default ShoppingCart;
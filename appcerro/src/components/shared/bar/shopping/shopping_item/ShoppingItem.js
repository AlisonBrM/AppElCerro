import { useState } from "react";
import "./ShoppingItem.css"

function ShoppingItem({ name, description, price, id, url, quantity }) {
    const [quantityProduct, setQuantityProduct] = useState(quantity);
    const plus = () => {
        const value = localStorage.getItem('productsSelected');
        if(value == null){
            localStorage.setItem('productsSelected',id)
        }
        else{
            localStorage.setItem('productsSelected',value +";"+id)
        }
        setQuantityProduct(quantityProduct + 1)
    }
    const trash = () => {
        if (quantityProduct === 0) {
            console.log("esto se debe eliminar del carrito")
        } else {
            const value = localStorage.getItem('productsSelected')?.split(";");
            let listProducts = deleteCoincidenci(value,id)
            localStorage.setItem('productsSelected',listProducts.join(";"))
            setQuantityProduct(quantityProduct - 1)
        }
    }
    function deleteCoincidenci(array, id) {
        const arrayClonado = [...array];
        const index = arrayClonado.indexOf(id);
        if (index !== -1) {
            arrayClonado.splice(index, 1);
        }
        return arrayClonado;
      }
      
    return (
        <div className="shopping-item">
            <div className="shopping-item-url">
                <img src={url} alt={description}></img>
            </div>
            <div>
                <div className="shopping-item-name">
                    {name}
                </div>
                <div className="shopping-item-price">
                    {price}
                </div>
                <div className="shopping-item-edit">
                    <div className="shopping-item-button-trash">
                        <button onClick={() => trash()}><img src="https://i.postimg.cc/5yStQYWZ/trash.png" alt="Icono de Borrar"></img></button>
                    </div>
                    <div className="shopping-item-quatity">
                        {quantityProduct}
                    </div>
                    <div className="shopping-item-button-plus">
                        <button onClick={() => plus()}><img src="https://i.postimg.cc/cLNV59YM/plus.png" alt="Icono de Borrar"></img></button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ShoppingItem;
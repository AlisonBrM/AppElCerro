import "./CardProduct.css"
function CardProduct({name, description, price, id, url}) {

    const onClick =() =>{
        const value = localStorage.getItem('productsSelected');
        if(value == null){
            localStorage.setItem('productsSelected',id)
        }
        else{
            localStorage.setItem('productsSelected',value +";"+id)
        }
    }

    return (
        <div className="card-Product-card">
            <div className="body-card-product">
                <img src={url} alt="muestra"></img>
            </div>
            <div className="title">{name}</div>
            <div className="description">{description}</div>
            <div className="price">$ {price}</div>
            <div className="foot">
                <button onClick={() =>onClick()}>Agregar</button>
            </div>
        </div>
    )

}
export default CardProduct;
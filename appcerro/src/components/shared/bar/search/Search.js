import { useState } from "react";
import "./Search.css"
import { useNavigate } from "react-router-dom";

function Search() {
    const [inputValue, setInputValue] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        setInputValue(event.target.value);
    };


    function searchByString() {
        const listProducts = JSON.parse(localStorage.getItem('allProducts'))
        const results = listProducts?.filter(object => {
            const nameMatch = object.nombre.toLowerCase().includes(inputValue.toLowerCase());
            const descriptionMatch = object.descripcion.toLowerCase().includes(inputValue.toLowerCase());
            return nameMatch || descriptionMatch;
        });

        localStorage.setItem('searchProducts', JSON.stringify(results));
        navigate('/products');
    }

    return (
        <div className="search">
            <input type="text" placeholder="🔍︎ Buscar Producto..." value={inputValue} onChange={handleInputChange}></input>
            <button onClick={() => searchByString()}><img src="https://i.postimg.cc/MHh7wH9S/lupa1.png" alt="lupa de busqueda"></img></button>
        </div>
    )

}

export default Search;
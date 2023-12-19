import Search from "./search/Search";
import ShoppingCart from "./shopping_cart/ShoppingCart";
import Menu from "./menu/Menu";
import "./Bar.css"
import Shopping from "./shopping/Shopping";
import { useState } from "react";

function Bar() {
    const[isModalOpen, setIsModalOpen] = useState(false);
    return (
        <div className="bar">
            <div className="body-bar">
                <a href="/"><img src="https://i.postimg.cc/zG85wrd3/El-Cerro.png" alt="Logo del supermercado El Cerro"></img></a>
                <Search />
                <ShoppingCart setIsModalOpen={setIsModalOpen} />
                <Shopping isOpen={isModalOpen} closeModal={() => setIsModalOpen(false)} />
            </div>
            <Menu />
        </div>
    )

}

export default Bar;
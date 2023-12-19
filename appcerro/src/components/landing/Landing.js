import Carrusel from "./carrusel/Carrusel";
import SectionCards from "./section_cards/SectionCards";
import './Landing.css';

function Landing() {
    return (
        <div className="landing">
            <Carrusel />
            <SectionCards />
        </div>
    )

}

export default Landing;
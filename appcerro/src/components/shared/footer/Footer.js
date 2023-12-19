import React from 'react';
import './Footer.css';

function Footer() {
  return (
    <div className="footer">
      <div className="footer-content">
        <div className="footer-logo-container">
          <img src="https://i.postimg.cc/zG85wrd3/El-Cerro.png" alt="Logo del supermercado El Cerro" className="footer-logo-image" />
        </div>
        <div className="footer-contact-info">
          <p>¿Necesitas ayuda?</p>
          <p>Contáctanos al <span className="footer-phone-number">300 - 0000000</span></p>
          <p>o síguenos en nuestras redes sociales</p>
          <div className="footer-social-icons">
            <a href="https://twitter.com/" target="_blank" rel="noopener noreferrer">
              <img src="https://i.postimg.cc/90q24JXH/twitter.png" alt="Twitter" className="icon footer-social-icon1" />
            </a>
            <a href="https://www.youtube.com/channel/UCP_c3w64Eh_foIcejqc31xw" target="_blank" rel="noopener noreferrer">
              <img src="https://i.postimg.cc/QdrSYTxq/youtube.png" alt="YouTube" className="icon footer-social-icon2" />
            </a>
            <a href="https://www.instagram.com/_linda_memories_/" target="_blank" rel="noopener noreferrer">
              <img src="https://i.postimg.cc/KvhswV0L/instagram.png" alt="Instagram" className="icon footer-social-icon3" />
            </a>
            <a href="https://www.facebook.com/" target="_blank" rel="noopener noreferrer">
              <img src="https://i.postimg.cc/d1Jzbfs4/fb.png" alt="Facebook" className="icon footer-social-icon4" />
            </a>
          </div>
        </div>
        <div className="footer-about-us">
          <p>Nuestra empresa</p>
          <p>Somos una empresa familiar, proveniente del campo de nuestra querida Colombia, llevando a las casas de los colombianos los mejores productos que ofrece el mercado</p>
        </div>
      </div>
    </div>
  );
}

export default Footer;

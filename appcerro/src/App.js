import React from 'react';
import Bar from './components/shared/bar/Bar';
import Footer from './components/shared/footer/Footer';
import { Route, Routes, useNavigate, Outlet } from 'react-router-dom';
import Landing from './components/landing/Landing';
import ListProducts from './components/list_products/ListProducts';
import ListProductsDiscount from './components/list_products/ListProductDiscount';
import Step1 from './components/payment/step1/Step1';
import Step2 from './components/payment/step2/Step2';
import Step3 from './components/payment/step3/Step3';
import Payment from './components/payment/Payment';
import SectionCards from './components/landing/section_cards/SectionCards';
import useFetch from './hooks/useFetchData';
import { Toaster, toast } from 'sonner';

function App() {
  const { data: products, error } = useFetch("http://localhost:8080/AppElCerro/resources/producto/productos");
  if (error != null) {
    toast.error(error);
  }
  localStorage.setItem('allProducts', JSON.stringify(products));

  const navigate = useNavigate();

  const redirectToProducts = () => {
    navigate('/products');
  };

  const handleNextStep = () => {
  };

  const formData = {
    nombre: '',
    direccion: '',
    email: '',
    telefono: '',
  };


  return (
    <div className="App">
      <Toaster />
      <Bar />
      <Outlet /> 
      <Routes>
        <Route path="/" element={<Landing />} />
        <Route path="/products" element={<ListProducts />} />
        <Route path="/products/:tipo" element={<ListProducts />} />
        <Route path="/productsDiscount" element={<ListProductsDiscount />} />
        <Route path="/productsDiscount/:tipo" element={<ListProductsDiscount />} />
        <Route path="/SectionCards" element={<SectionCards />} />
        <Route path="/payment/step1" element={<Step1 />} />
        <Route path="/payment/step2" element={<Step2 />} />
        <Route path="/payment/step3" element={<Step3 />} />
        <Route path="/payment" element={<Payment />} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
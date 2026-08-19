import "./App.css"
import "../src/components/AuthWindow/AuthWindow"
import { BrowserRouter, Route, Routes } from "react-router"
import AuthPage from "./pages/AuthPage/AuthPage"
import MainPage from "./pages/MainPage/MainPage"
const App = () => {
  
  return (
  <BrowserRouter>
    <section className="main">
      <Routes>
        <Route path="/" element={<AuthPage/>} />
        <Route path="/main" element={<MainPage/>} />
      </Routes>
    </section>
  </BrowserRouter>
  )
}

export default App

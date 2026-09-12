import "./App.css"
import "../src/components/AuthWindow/AuthWindow"
import { BrowserRouter, Route, Routes } from "react-router"
import AuthPage from "./pages/AuthPage/AuthPage"
import MainPage from "./pages/MainPage/MainPage"
import ProjectPage from "./pages/ProjectPage/ProjectPage"
const App = () => {
  
  return (
  <BrowserRouter>
    <section className="main">
      <Routes>
        <Route path="/" element={<AuthPage/>} />
        <Route path="/main" element={<MainPage/>} />
        <Route path="/project" element={<ProjectPage />} />
      </Routes>
    </section>
  </BrowserRouter>
  )
}

export default App

import AuthWindow from "../../components/AuthWindow/AuthWindow"
import {useNavigate} from "react-router"

const AuthPage = () => {
    let navigate = useNavigate()
    return (       
    <section>
        <AuthWindow navigateFunction={() => navigate("/main")}/>
    </section>
    )
}

export default AuthPage
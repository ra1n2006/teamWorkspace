import { useState, type FC } from 'react'
import axios from 'axios'
import './AuthWindow.css'
import { ApiEndpoints } from '../../service/RESTservice'
import { userData } from '../../data/userData'
import { InputField } from '../../UI/InputField/InputField'

export interface NavProps {
    navigateFunction: () => void
}
const AuthWindow: FC<NavProps> = ({navigateFunction}) => {
    const [login, setLogin] = useState<string>('') 
    const [password, setPassword] = useState('')
    const [authState, setAuthState] = useState(false)

    const handleSetLogin = (login : string) => {
        setLogin(login)
    }

    const handleSetPassword = (password : string) => {
        setPassword(password)
    }

    const submit = async (status: 'login' | 'register') => {
        try {
            const response = await axios.post(
                ApiEndpoints.authPOST + `/${status}`,
                {
                    status: status,
                    login: login,
                    password: password
                }
            )
            userData.setUserData({
            ...response.data.user,
            token: response.data.token
            })
            navigateFunction()
            console.log(response.data)

        } catch (error) {
            console.error('Authorization error:', error)
        }
    }

    const LoginForm = () => {
        return (
            <section className="auth-form">
                <InputField 
                    type = "text"
                    value = {login}
                    onChange={handleSetLogin}
                    label="login"
                />

                <InputField 
                    type = "password"
                    value = {password}
                    onChange={handleSetPassword}
                    label="password"
                />

                <button onClick={() => submit('login')}>
                    Sign in
                </button>
            </section>
        )
    }

    const RegForm = () => {
        return (
            <section className="auth-form">
                <InputField 
                    type = "text"
                    value = {login}
                    onChange={handleSetLogin}
                    label="login"
                />

                <InputField 
                    type = "password"
                    value = {password}
                    onChange={handleSetPassword}
                    label="password"
                />

                <button onClick={() => submit('register')}>
                    Sign up
                </button>
            </section>
        )
    }

    return (
        <section className="login-page">
            <section className="login-card">
                <article className="login-title">
                    TeamWorkspace
                </article>

                <article className="login-subtitle">
                    {authState ? 'sign in' : 'create account'}
                </article>

                {authState ? LoginForm() : RegForm()}

                <article className="register-text">
                    <button onClick={() => setAuthState(!authState)}>
                        {authState
                            ? 'Create account'
                            : 'Already have an account? Sign in'}
                    </button>
                </article>
            </section>
        </section>
    )
}

export default AuthWindow
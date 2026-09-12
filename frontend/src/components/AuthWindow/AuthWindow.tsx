import { useState, type FC } from 'react'
import axios from 'axios'
import './AuthWindow.css'
import { ApiEndpoints } from '../../service/RESTservice'
import { useNavigate } from 'react-router'
import { userData } from '../../data/userData'

export interface NavProps {
    navigateFunction: () => void
}
const AuthWindow: FC<NavProps> = ({navigateFunction}) => {
    const [login, setLogin] : string | any = useState('') 
    const [password, setPassword] = useState('')
    const [authState, setAuthState] = useState(false)

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
                <article>login</article>
                <input
                    type="text"
                    value={login}
                    onChange={(event) => setLogin(event.target.value)}
                />

                <article>password</article>
                <input
                    type="password"
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
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
                <article>login</article>
                <input
                    type="text"
                    value={login}
                    onChange={(event) => setLogin(event.target.value)}
                />

                <article>password</article>
                <input
                    type="password"
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                />

                <article>repeat password</article>
                <input type="password" />

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

                {authState ? <LoginForm /> : <RegForm />}

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
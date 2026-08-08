import { useState } from 'react'
import './AuthWindow.css'

const AuthWindow = () => {
    const [login, setLogin] = useState('')
    const [password, setPassword] = useState('')
    const [authState, setAuthState] = useState(false)

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

                <button>Sign in</button>
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

                <button>Sign up</button>
            </section>
        )
    }

    return (
        <section className="login-page">
            <section className="login-card">
                <article className="login-title">TeamWorkspace</article>

                <article className="login-subtitle">
                    {authState ? 'sign in' : 'create account'}
                </article>

                {authState ? <LoginForm /> : <RegForm />}

                <article className="register-text">
                    <button onClick={() => setAuthState(!authState)}>
                        {authState ? 'Create account' : 'Already have an account? Sign in'}
                    </button>
                </article>
            </section>
        </section>
    )
}

export default AuthWindow

import { Link } from "react-router-dom";
import { useContext } from "react";
import AuthContext from "../context/AuthContext";
import "../styles/Navbar.css"; // Import CSS

const Navbar = () => {
    const { token, logout } = useContext(AuthContext);

    return (
        <nav className="navbar">
            <Link to="/">Home</Link>
            {!token ? (
                <>
                    <Link to="/register">Register</Link>
                    <Link to="/login">Login</Link>
                </>
            ) : (
                <>
                    <Link to="/dashboard">Dashboard</Link>
                    <button onClick={logout}>Logout</button>
                </>
            )}
        </nav>
    );
};

export default Navbar;

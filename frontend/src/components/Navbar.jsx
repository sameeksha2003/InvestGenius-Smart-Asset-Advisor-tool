import { useContext } from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext'; 

import '../styles/navbar.css';

const Navbar = () => {
  const { user,logout } = useAuth();


  return (
    <nav className="navbar">
      <div className="logo">Smart Asset Advisor</div>
      <ul className="nav-links">
        {user ? (
          <>
            <li><Link to="/dashboard">Dashboard</Link></li>
            <li><button onClick={logout}>Logout</button></li>
          </>
        ) : (
          <>
            <li><Link to="/login">Login</Link></li>
            <li><Link to="/signup">Sign Up</Link></li>
          </>
        )}
      </ul>
    </nav>
  );
};

export default Navbar;

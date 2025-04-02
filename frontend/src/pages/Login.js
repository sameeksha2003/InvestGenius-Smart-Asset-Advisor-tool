import { useContext, useState } from "react";
import { AuthContext } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
    const { login } = useContext(AuthContext);
    const navigate = useNavigate(); // ✅ Safe to use here
    
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:5000/api/auth/login", { email, password });
    
            console.log("✅ Login response:", response.data);
    
            const { token } = response.data; // ✅ Extract token
            if (!token) {
                console.error("❌ Error: No token received!");
                return;
            }
    
            // ✅ Store token immediately
            localStorage.setItem("token", token);
    
            // 🔥 Fetch user details separately
            const userResponse = await axios.get(`http://localhost:5000/api/auth/user?email=${email}`, {
                headers: { Authorization: `Bearer ${token}` }
            });
    
            console.log("✅ User details fetched:", userResponse.data);
    
            const user = userResponse.data;
            if (!user) {
                console.error("❌ Error: No user details received!");
                return;
            }
    
            // ✅ Store user and update AuthContext
            localStorage.setItem("user", JSON.stringify(user));
            login(user); // ✅ Update AuthContext state
    
            console.log("✅ Navigating to /dashboard...");
            navigate("/dashboard");
    
        } catch (error) {
            console.error("❌ Login failed:", error.response?.data?.message || error.message);
        }
    };
    
    

    return (
        <form onSubmit={handleLogin}>
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Email" />
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" />
            <button type="submit">Login</button>
        </form>
    );
};

export default Login;

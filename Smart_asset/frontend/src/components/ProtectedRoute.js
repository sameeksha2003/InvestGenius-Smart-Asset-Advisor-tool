import { Navigate } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";

const ProtectedRoute = ({ children }) => {
    const { user } = useContext(AuthContext);

    console.log("🛡️ ProtectedRoute user:", user);

    if (user === null) {
        return <p>Loading...</p>; // ✅ Prevents redirect until user is set
    }

    if (!user) {
        return <Navigate to="/login" replace />;
    }

    return children;
};

export default ProtectedRoute;

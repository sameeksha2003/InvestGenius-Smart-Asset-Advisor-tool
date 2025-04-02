import React from "react";
import ReactDOM from "react-dom/client"; // ✅ Ensure correct import
import { AuthProvider } from "./context/AuthContext";
import App from "./App";

ReactDOM.createRoot(document.getElementById("root")).render(
        <AuthProvider>
            <App />
        </AuthProvider>
);

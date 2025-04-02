import React, { useEffect, useState } from "react";
import axios from "axios";

const Dashboard = () => {
    const [advice, setAdvice] = useState(null);
    const [error, setError] = useState(null);

    const fetchAdvice = async () => {
        try {
            const token = localStorage.getItem("token");

            if (!token) {
                console.error("❌ No token found. User is not authenticated.");
                setError("User is not authenticated.");
                return;
            }

            console.log("✅ Sending Token:", token); // Debugging

            const response = await axios.get("http://localhost:5000/api/investment/advice", {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });

            console.log("✅ Investment Advice:", response.data);
            setAdvice(response.data);
        } catch (error) {
            console.error("❌ Error fetching investment advice:", error.response);
            setError("Failed to fetch investment advice.");
        }
    };

    useEffect(() => {
        fetchAdvice();
    }, []);

    return (
        <div>
            <h2>Dashboard</h2>
            {error && <p style={{ color: "red" }}>{error}</p>}
            {advice ? <p>{advice}</p> : <p>Loading investment advice...</p>}
        </div>
    );
};

export default Dashboard;

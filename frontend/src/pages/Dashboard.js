import React, { useState, useEffect } from "react";
import { useAuth } from "../context/AuthContext";
import axios from "axios";

const Dashboard = () => {
    const { user, token } = useAuth();
    const [investmentAdvice, setInvestmentAdvice] = useState("Fetching advice...");
    const [marketMood, setMarketMood] = useState("Fetching market mood...");

    useEffect(() => {
        const fetchAdvice = async () => {
            if (token) {
                try {
                    const response = await axios.get("http://localhost:5000/api/investment/advice", {
                        headers: { Authorization: `Bearer ${token}` },
                    });
                    setMarketMood(response.data.marketMood);
                    setInvestmentAdvice(response.data.advice);
                } catch (error) {
                    console.error("Error fetching investment advice:", error);
                    setInvestmentAdvice("Unable to fetch advice.");
                    setMarketMood("Unable to fetch market mood.");
                }
            }
        };
        fetchAdvice();
    }, [token]);

    return (
        <div className="container">
            <h1>Welcome, {user?.name || "Guest"}!</h1>
            <h2>Market Mood & Investment Advice</h2>
            <p><strong>Market Mood:</strong> {marketMood}</p>
            <p><strong>Investment Advice:</strong> {investmentAdvice}</p>
        </div>
    );
};

export default Dashboard;

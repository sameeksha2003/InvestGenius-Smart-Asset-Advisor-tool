import React, { useState, useEffect } from "react";
import axios from "axios";

const Dashboard = () => {
  const [advice, setAdvice] = useState(""); // State to store advice
  const [loading, setLoading] = useState(true); // Track loading state

  useEffect(() => {
    const fetchAdvice = async () => {
      try {
        const token = localStorage.getItem("token"); // Get token
        const response = await axios.get("http://localhost:5000/api/investment/advice", {
          headers: { Authorization: `Bearer ${token}` },
        });

        console.log("✅ Investment Advice Response:", response.data);
        setAdvice(response.data); // Update advice state
      } catch (error) {
        console.error("❌ Error fetching investment advice:", error);
      } finally {
        setLoading(false); // Stop loading
      }
    };

    fetchAdvice();
  }, []);

  return (
    <div className="dashboard-container">
      <h1>📊 Investment Dashboard</h1>

      {loading ? (
        <p>Loading investment advice...</p>
      ) : (
        <div className="investment-advice">
          <h2>💡 Investment Advice</h2>
          <p>
            <strong>📊 Investment Strategy Based on Your Risk Profile & Market Conditions</strong>
          </p>
          <p>📈 {advice}</p> {/* Display the advice from backend here */}

          <p>💡 <strong>Recommended Investments:</strong></p>
          <ul>
            <li>✅ Large Cap Stocks</li>
            <li>✅ ETFs</li>
            <li>✅ 60% Stocks, 30% Bonds, 10% Alternative Assets</li>
          </ul>
        </div>
      )}
    </div>
  );
};

export default Dashboard;

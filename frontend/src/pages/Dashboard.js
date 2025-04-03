import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";  // 🚀 Import useNavigate
import axios from "axios";
import "../styles/dashboard.css";

const Dashboard = () => {
  const navigate = useNavigate(); // 🚀 For redirecting user after logout
  const [advice, setAdvice] = useState(""); 
  const [loading, setLoading] = useState(true);
  const [marketMood, setMarketMood] = useState("Neutral"); 
  const [marketMoodImage, setMarketMoodImage] = useState("/images/market-mood.jpg");

  useEffect(() => {
    const fetchAdvice = async () => {
      try {
        const token = localStorage.getItem("token"); 
        const response = await axios.get("http://localhost:5000/api/investment/advice", {
          headers: { Authorization: `Bearer ${token}` },
        });

        console.log("✅ Investment Advice Response:", response.data);
        setAdvice(response.data);

        if (response.data.includes("Extreme Greed")) setMarketMood("Extreme Greed");
        else if (response.data.includes("Greed")) setMarketMood("Greed");
        else if (response.data.includes("Fear")) setMarketMood("Fear");
        else if (response.data.includes("Extreme Fear")) setMarketMood("Extreme Fear");
        else setMarketMood("Neutral");

      } catch (error) {
        console.error("❌ Error fetching investment advice:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchAdvice();
  }, []);

  // 🚀 Logout Function
  const handleLogout = () => {
    localStorage.removeItem("token"); // Clear user session
    navigate("/login"); // Redirect to login page
  };

  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <h1 className="dashboard-title">📊 Investment Dashboard</h1>
        <button className="logout-btn" onClick={handleLogout}>🚪 Logout</button>
      </div>

      {loading ? (
        <p className="loading-text">Loading investment advice...</p>
      ) : (
        <div className="investment-advice">
          <h2>💡 Investment Advice</h2>
          <div className="advice-text" dangerouslySetInnerHTML={{ __html: advice }} />

          <div className="market-mood">
            <h3>📈 Market Mood</h3>
            <p>Current market sentiment: <strong>{marketMood}</strong></p>
            <img 
              src={marketMoodImage} 
              alt="Market Mood" 
              className="market-mood-img"
              onError={() => setMarketMoodImage("/images/default-market-mood.jpg")} 
            />
          </div>
        </div>
      )}
    </div>
  );
};

export default Dashboard;

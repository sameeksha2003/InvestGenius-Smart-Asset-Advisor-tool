import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";  
import axios from "axios";
import "../styles/dashboard.css";

const Dashboard = () => {
  const navigate = useNavigate(); 
  const [advice, setAdvice] = useState(""); 
  const [loading, setLoading] = useState(true);
  const [marketMood, setMarketMood] = useState("Neutral"); 
  const [marketMoodImage, setMarketMoodImage] = useState("../images/market-mood.jpg");
  const [news, setNews] = useState([]); 

  useEffect(() => {
    const fetchInvestmentAdvice = async () => {
      try {
        const token = localStorage.getItem("token"); 
        const response = await axios.get("http://localhost:5000/api/investment/advice", {
          headers: { Authorization: `Bearer ${token}` },
        });

        console.log("✅ Investment Advice Response:", response.data);
        setAdvice(response.data);

        // Update Market Mood Based on Advice
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

    const fetchMarketSentiment = async () => {
      try {
        const response = await axios.get("http://localhost:5000/api/market-sentiment/analyze");
        console.log("✅ Market Sentiment Response:", response.data);
        
        setMarketMood(response.data.marketMood);  
        setNews(response.data.summary);  
        switch (response.data.marketMood) {
          case "📈 Positive":
            setMarketMoodImage("/images/positive-market.jpg");
            break;
          case "📉 Negative":
            setMarketMoodImage("/images/negative-market.jpg");
            break;
          default:
            setMarketMoodImage("../images/neutral-market.jpg");
        }
      } catch (error) {
        console.error("❌ Error fetching market sentiment:", error);
      }
    };
    

    fetchInvestmentAdvice();
    fetchMarketSentiment();
  }, []);
  const handleLogout = () => {
    localStorage.removeItem("token"); 
    navigate("/login"); 
  };

  const getSentimentColor = (sentiment) => {
    if (sentiment === "positive") return "text-green-600";
    if (sentiment === "negative") return "text-red-600";
    return "text-gray-600";
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
            <h3>📈 Market Sentiment</h3>
            <p>Current Market Mood: <strong>{marketMood}</strong></p>
            <img 
              src={marketMoodImage} 
              alt="Market Mood" 
              className="market-mood-img"
              onError={() => setMarketMoodImage("/images/default-market-mood.jpg")} 
            />
          </div>

          {/* 📰 Latest Financial News */}
          {/* 📰 Market Sentiment Insights */}
          <div className="financial-news">
          <h3>📰 Market News Summary</h3>
          <ul className="news-list">
         {Array.isArray(news) ? (
          news.map((item, index) => (
          <li key={index}>🔹 {item}</li>
           ))
          ) : (
        news?.split("• ").map((item, index) => (
        item.trim() && <li key={index}>🔹 {item.trim()}</li>
         ))
        )}
        </ul>

          </div>
        </div>
      )}
    </div>
  );
};

export default Dashboard;

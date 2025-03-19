import { useState } from 'react';
import InvestmentForm from '../components/InvestmentForm';
import InvestmentRecommendations from '../components/InvestmentRecommendations';
import MarketMood from '../components/MarketMood';
import '../styles/dashboard.css';

const Dashboard = () => {
  const [recommendations, setRecommendations] = useState([]);

  return (
    <div className="dashboard">
      <h1>Investment Advisory Dashboard</h1>
      <MarketMood />
      <InvestmentForm setRecommendations={setRecommendations} />
      <InvestmentRecommendations recommendations={recommendations} />
    </div>
  );
};

export default Dashboard;

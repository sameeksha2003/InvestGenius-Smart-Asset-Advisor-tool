import { useState } from 'react';
import { getInvestmentAdvice } from '../services/api';
import '../styles/dashboard.css';

const InvestmentForm = ({ setRecommendations }) => {
  const [portfolioSize, setPortfolioSize] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const { data } = await getInvestmentAdvice(portfolioSize);
      setRecommendations(data);
    } catch (error) {
      console.error('Error fetching investment advice:', error);
    }
  };

  return (
    <div className="investment-form">
      <h2>Get Investment Advice</h2>
      <form onSubmit={handleSubmit}>
        <input 
          type="number" 
          placeholder="Enter Portfolio Size ($)" 
          value={portfolioSize} 
          onChange={(e) => setPortfolioSize(e.target.value)} 
          required 
        />
        <button type="submit">Get Advice</button>
      </form>
    </div>
  );
};

export default InvestmentForm;

import { useEffect, useState } from 'react';
import api from '../services/api';

const MarketMood = () => {
  const [marketMood, setMarketMood] = useState(null);

  useEffect(() => {
    const fetchMarketMood = async () => {
      try {
        const { data } = await api.get('/market/mood');
        setMarketMood(data);
      } catch (error) {
        console.error('Error fetching market mood:', error);
      }
    };
    fetchMarketMood();
  }, []);

  return (
    <div className="market-mood">
      <h2>Market Mood Index</h2>
      {marketMood ? <p>{marketMood}</p> : <p>Loading...</p>}
    </div>
  );
};

export default MarketMood;

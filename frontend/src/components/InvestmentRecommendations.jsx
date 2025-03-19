const InvestmentRecommendations = ({ recommendations }) => {
    return (
      <div className="investment-recommendations">
        <h2>Recommended Investment Buckets</h2>
        {recommendations.length > 0 ? (
          <ul>
            {recommendations.map((bucket, index) => (
              <li key={index}>
                <strong>{bucket.name}</strong> - {bucket.percentage}% allocation
              </li>
            ))}
          </ul>
        ) : (
          <p>No recommendations available.</p>
        )}
      </div>
    );
  };
  
  export default InvestmentRecommendations;
  
import React, { useState, useEffect } from "react";
import axios from "axios";

const Dashboard = () => {
  const [advice, setAdvice] = useState("");  // State to store advice
  const [loading, setLoading] = useState(true);  // Track loading state

  useEffect(() => {
    const fetchAdvice = async () => {
      try {
        const token = localStorage.getItem("token"); // Get token
        const response = await axios.get("http://localhost:5000/api/investment/advice", {
          headers: { Authorization: `Bearer ${token}` },
        });

        console.log("✅ Investment Advice Response:", response.data);
        setAdvice(response.data); // Update advice state
        setLoading(false); // Stop loading
      } catch (error) {
        console.error("❌ Error fetching investment advice:", error);
        setLoading(false); // Stop loading on error
      }
    };

    fetchAdvice();
  }, []);

  return (
    <div>
      <h1>Dashboard</h1>
      {loading ? (
        <p>Loading investment advice...</p>
      ) : (
        <p>💡 Investment Advice: {advice}</p>
      )}
    </div>
  );
};

export default Dashboard;

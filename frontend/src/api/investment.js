import axios from "axios";

const API_URL = "http://localhost:5000/api/investment";

export const getInvestmentAdvice = async (email) => {
    try {
        const response = await axios.get(`${API_URL}/advice`, {
            params: { email },
        });
        return response.data;
    } catch (error) {
        console.error("Error fetching investment advice:", error);
        return "Failed to fetch investment advice.";
    }
};

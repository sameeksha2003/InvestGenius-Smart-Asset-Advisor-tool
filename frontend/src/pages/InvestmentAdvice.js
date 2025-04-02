import { useState, useEffect, useContext } from "react";
import { fetchInvestmentAdvice } from "../api/advice";
import AuthContext from "../context/AuthContext";
import { Container, Typography, Paper, CircularProgress } from "@mui/material";

const InvestmentAdvice = () => {
    const { user, token } = useContext(AuthContext);
    const [advice, setAdvice] = useState("");
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const getAdvice = async () => {
            if (!user || !token) {
                setAdvice("User not authenticated. Please log in.");
                setLoading(false);
                return;
            }

            try {
                const data = await fetchInvestmentAdvice(user.id, token);
                setAdvice(data.advice || "No advice available.");
            } catch (error) {
                console.error("Error fetching investment advice:", error);
                setAdvice("Unable to fetch investment advice.");
            } finally {
                setLoading(false);
            }
        };

        getAdvice();
    }, [user, token]);

    return (
        <Container style={{ padding: "20px" }}>
            <Typography variant="h4" gutterBottom>
                📊 Your Investment Advice
            </Typography>
            {loading ? (
                <CircularProgress />
            ) : (
                <Paper elevation={3} style={{ padding: "20px", backgroundColor: "#f3f4f6" }}>
                    <Typography variant="h6">{advice}</Typography>
                </Paper>
            )}
        </Container>
    );
};

export default InvestmentAdvice;

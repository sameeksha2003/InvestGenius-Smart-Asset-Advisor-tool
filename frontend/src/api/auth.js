import axios from "axios";

const BASE_URL = "http://localhost:5000/api/auth"; // ✅ Ensure backend port is 5000

export const loginUser = async (credentials) => {
  const response = await axios.post(`${BASE_URL}/login`, credentials);
  return response.data;
};

export const registerUser = async (userData) => {
  const response = await axios.post(`${BASE_URL}/register`, userData);
  return response.data;
};

export const fetchUser = async (token) => { // ✅ Ensure this function exists
  const response = await axios.get(`${BASE_URL}/user`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

export const logoutUser = () => { // ✅ Ensure this function exists
  localStorage.removeItem("token");
};

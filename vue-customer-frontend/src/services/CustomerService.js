import axios from 'axios';

const API_URL = 'http://localhost:8080';
export const CustomerService = {
  getHealthCheckStatus() {
    return axios.get(`${API_URL}/q/health`);
  },
  getCustomersByPage(page, size) {
    return axios.get(`${API_URL}/customer?page=${page}&size=${size}`);
  },
  addCustomer(customer) {
    console.log("Adding customer:", customer);
    return axios.post(`${API_URL}/customer`, customer, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
  }
  
};

import axios from 'axios';
// TODO nån slags miljövariabel!
const API_URL = 'http://localhost:8080';
// TODO ta bort
// const API_URL = 'http://localhost:8080/customer/all';
export const CustomerService = {
  getHealthCheckStatus() {
    return axios.get(`${API_URL}/q/health`);
  },
  getCustomersByPage(page, size) {
    return axios.get(`${API_URL}/customer?page=${page}&size=${size}`);
  },
  addCustomer(customer) {
    // TODO ta bort
    console.log("Adding customer:", customer);
    return axios.post(`${API_URL}/customer`, customer, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
  }
  
};

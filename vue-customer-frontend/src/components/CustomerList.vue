<template>
    <table class="table table-bordered mt-3">
      <thead class="thead-light">
        <tr>
          <th>Name</th>
          <th>Address</th>
          <th>Email</th>
          <th>Telephone</th>
        </tr>
      </thead>
      <tbody>
        <CustomerRow
          v-for="customer in customers"
          :key="customer.email"
          :customer="customer"
        />
      </tbody>
    </table>
  </template>
  
  <script>
  import CustomerRow from "./CustomerRow.vue";
  
  export default {
    name: "CustomerList",
    components: {
      CustomerRow,
    },
    props: {
      customers: {
        type: Array,
        required: true,
      },
    },
  };
  </script>
  
  <style scoped>
  </style>
  
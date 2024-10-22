<template>
    <div class="modal fade show" style="display: block;" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Add Customer</h5>
            <button type="button" class="close" @click="$emit('close')">
              <span>&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="submitForm">
              <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" v-model="customer.name" required />
              </div>
              <div class="form-group">
                <label for="address">Address</label>
                <input type="text" class="form-control" v-model="customer.address" required />
              </div>
              <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" v-model="customer.email" required />
              </div>
              <div class="form-group">
                <label for="telephone">Telephone</label>
                <input type="tel" class="form-control" v-model="customer.telephone" required />
              </div>
              <button type="submit" class="btn btn-primary">Add Customer</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { ref } from "vue";
  
  export default {
    name: "CustomerModal",
    emits: ["close", "add-customer"],
    setup(props, { emit }) {
      const customer = ref({
        name: "",
        address: "",
        email: "",
        telephone: "",
      });
  
      const submitForm = () => {
        emit("add-customer", customer.value);
        emit("close");
        customer.value = {};
      };
  
      return { customer, submitForm };
    },
  };
  </script>
  
  <style scoped>
  .modal {
    display: block;
  }
  </style>
  
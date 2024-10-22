<template>
  <div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h1>Customer List</h1>
      <button class="btn btn-primary" @click="showModal = true">Add</button>
    </div>

    <div v-if="loading" class="text-center mb-3">
      <div class="spinner-border" role="status">
        <span class="sr-only">Loading...</span>
      </div>
    </div>

    <div v-else-if="isErrorWithBackend" class="alert alert-danger" role="alert">
      There was an error reaching the backend. Please try again later.
    </div>

    <div v-else>
      <CustomerList v-if="!isErrorWithBackend" :customers="customers" />
    </div>

    <div class="d-flex justify-content-between align-items-center mb-3">
      <button
        class="btn btn-primary"
        @click="previousPage()"
        :disabled="pageIndex <= 0"
      >
        Previous
      </button>
      <button class="btn btn-primary" @click="nextPage()">Next</button>
    </div>

    <CustomerModal
      v-if="showModal"
      @close="showModal = false"
      @add-customer="addCustomer"
    />
  </div>
</template>


<script>
import { ref, onMounted } from "vue";
import { CustomerService } from "../services/CustomerService";
import CustomerList from "../components/CustomerList.vue";
import CustomerModal from "../components/CustomerModal.vue";

export default {
  name: "CustomerView",
  components: {
    CustomerList,
    CustomerModal,
  },
  setup() {
    const customers = ref([]);
    const loading = ref(true);
    const isErrorWithBackend = ref(false);
    const showModal = ref(false);
    const pageIndex = ref(0);
    const pageSize = 10;

    const healthCheckAndFetchCustomers = async () => {
      await healthCheck();
    };

    const healthCheck = async () => {
      loading.value = true;
      isErrorWithBackend.value = false;
      try {
        const response = await CustomerService.getHealthCheckStatus();
        if (response.status === 200) {
          await fetchCustomers();
        } else {
          isErrorWithBackend.value = true;
        }
      } catch (error) {
        console.error("Error fetching health check status:", error);
        isErrorWithBackend.value = true;
      } finally {
        loading.value = false;
      }
    };

    const fetchCustomers = async () => {
      try {
        const response = await CustomerService.getCustomersByPage(
          pageIndex.value,
          pageSize
        );
        customers.value = response.data;
      } catch (error) {
        console.error("Error fetching customers:", error);
        isErrorWithBackend.value = true;
      } finally {
        loading.value = false;
      }
    };

    const previousPage = async () => {
      if (pageIndex.value > 0) {
        loading.value = true;
        pageIndex.value--;
        await fetchCustomers();
      }
    };

    const nextPage = async () => {
      loading.value = true;
      pageIndex.value++;
      await fetchCustomers();
    };

    const addCustomer = async (newCustomer) => {
      try {
        await CustomerService.addCustomer(newCustomer);
        await fetchCustomers();
        showModal.value = false;
      } catch (error) {
        console.error("Error adding customer:", error);
      }
    };

    onMounted(healthCheckAndFetchCustomers);

    return {
      customers,
      loading,
      isErrorWithBackend,
      showModal,
      addCustomer,
      previousPage,
      nextPage,
      pageIndex,
    };
  },
};
</script>

<style scoped>
</style>

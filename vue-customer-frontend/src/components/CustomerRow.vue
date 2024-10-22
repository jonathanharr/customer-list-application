<template>
  <tr>
    <td>{{ customer.name }}</td>
    <td>{{ customer.address }}</td>
    <td>{{ customer.email }}</td>
    <td>{{ customer.telephone }}</td>
  </tr>
</template>

<script>
export default {
  name: "CustomerRow",
  props: {
    customer: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
</style>

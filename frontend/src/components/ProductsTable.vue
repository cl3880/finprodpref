<template>
    <section class="products">
      <h2>可選擇的金融商品</h2>
      
      <div v-if="loading" class="loading">載入中...</div>
      
      <table v-else>
        <thead>
          <tr>
            <th>產品代號</th>
            <th>產品名稱</th>
            <th>價格 (NTD)</th>
            <th>手續費率</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in products" :key="product.productNo">
            <td>{{ product.productNo }}</td>
            <td>{{ product.productName }}</td>
            <td>{{ formatNumber(product.price) }}</td>
            <td>{{ formatPercent(product.feeRate) }}%</td>
            <td>
              <button @click="$emit('add', product)" class="btn-add">
                加入喜好
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </section>
  </template>
  
  <script>
  export default {
    name: 'ProductsTable',
    
    props: {
      products: {
        type: Array,
        default: () => []
      },
      loading: {
        type: Boolean,
        default: false
      }
    },
    
    methods: {
      formatNumber(num) {
        return parseFloat(num || 0).toLocaleString('zh-TW', {
          minimumFractionDigits: 2,
          maximumFractionDigits: 2
        })
      },
      
      formatPercent(rate) {
        return (parseFloat(rate || 0) * 100).toFixed(2)
      }
    }
  }
  </script>
  
  <style scoped>
  .products {
    background: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
  }
  
  h2 {
    color: #2c3e50;
    margin-bottom: 20px;
  }
  
  .loading {
    text-align: center;
    padding: 40px;
    color: #666;
  }
  
  table {
    width: 100%;
    border-collapse: collapse;
  }
  
  th, td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #e0e0e0;
  }
  
  th {
    background-color: #f5f5f5;
    font-weight: bold;
  }
  
  tr:hover {
    background-color: #f9f9f9;
  }
  
  .btn-add {
    padding: 6px 16px;
    border: none;
    border-radius: 4px;
    background-color: #3498db;
    color: white;
    cursor: pointer;
    font-size: 14px;
  }
  
  .btn-add:hover {
    background-color: #2980b9;
  }
  </style>
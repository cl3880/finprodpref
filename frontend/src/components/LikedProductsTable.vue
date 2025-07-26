<template>
    <section class="liked-products">
      <h2>我的喜好金融商品清單</h2>
      
      <div v-if="loading" class="loading">載入中...</div>
      
      <template v-else>
        <div v-if="likedProducts.length === 0" class="empty">
          尚無喜好商品
        </div>
        
        <table v-else>
          <thead>
            <tr>
              <th>產品名稱</th>
              <th>扣款帳號</th>
              <th>購買數量</th>
              <th>總手續費 (NTD)</th>
              <th>預計扣款總金額 (NTD)</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in likedProducts" :key="`${item.productNo}-${item.account}`">
              <td>{{ item.productName }}</td>
              <td>{{ item.account }}</td>
              <td>{{ item.orderName || '-' }}</td>
              <td>{{ formatNumber(item.totalFee) }}</td>
              <td>{{ formatNumber(item.totalAmount) }}</td>
              <td>
                <button @click="$emit('edit', item)" class="btn-edit">編輯</button>
                <button @click="$emit('delete', item)" class="btn-delete">刪除</button>
              </td>
            </tr>
          </tbody>
        </table>
        
        <div v-if="likedProducts.length > 0" class="summary">
          <p><strong>使用者聯絡電子信箱：</strong>{{ userEmail }}</p>
          <p><strong>總手續費用：</strong>{{ formatNumber(totalFees) }} NTD</p>
          <p><strong>預計扣款總金額：</strong>{{ formatNumber(totalAmount) }} NTD</p>
        </div>
      </template>
    </section>
  </template>
  
  <script>
  export default {
    name: 'LikedProductsTable',
    
    props: {
      likedProducts: {
        type: Array,
        default: () => []
      },
      userEmail: {
        type: String,
        default: ''
      },
      loading: {
        type: Boolean,
        default: false
      }
    },
    
    computed: {
      totalFees() {
        return this.likedProducts.reduce((sum, item) => 
          sum + parseFloat(item.totalFee || 0), 0
        )
      },
      
      totalAmount() {
        return this.likedProducts.reduce((sum, item) => 
          sum + parseFloat(item.totalAmount || 0), 0
        )
      }
    },
    
    methods: {
      formatNumber(num) {
        return parseFloat(num || 0).toLocaleString('zh-TW', {
          minimumFractionDigits: 2,
          maximumFractionDigits: 2
        })
      }
    }
  }
  </script>
  
  <style scoped>
  .liked-products {
    background: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
  }
  
  h2 {
    color: #2c3e50;
    margin-bottom: 20px;
  }
  
  .loading, .empty {
    text-align: center;
    padding: 40px;
    color: #666;
  }
  
  table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
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
  
  .btn-edit, .btn-delete {
    padding: 6px 12px;
    margin: 0 4px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 14px;
  }
  
  .btn-edit {
    background-color: #f39c12;
    color: white;
  }
  
  .btn-edit:hover {
    background-color: #e67e22;
  }
  
  .btn-delete {
    background-color: #e74c3c;
    color: white;
  }
  
  .btn-delete:hover {
    background-color: #c0392b;
  }
  
  .summary {
    margin-top: 20px;
    padding: 15px;
    background-color: #ecf0f1;
    border-radius: 4px;
  }
  
  .summary p {
    margin: 8px 0;
  }
  </style>
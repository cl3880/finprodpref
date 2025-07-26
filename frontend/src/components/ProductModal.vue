<template>
    <div class="modal-overlay" @click="$emit('close')">
      <div class="modal-content" @click.stop>
        <h3>{{ isEditing ? '編輯' : '新增' }}喜好金融商品</h3>
        
        <div class="form-group">
          <label>產品名稱</label>
          <input type="text" :value="product.productName" disabled />
        </div>
        
        <div class="form-group">
          <label>扣款帳號 <span class="required">*</span></label>
          <select v-model="formData.account" required>
            <option value="" disabled>請選擇帳號</option>
            <option v-for="account in accounts" :key="account" :value="account">
              {{ account }}
            </option>
          </select>
        </div>
        
        <div class="form-group">
          <label>購買數量 <span class="required">*</span></label>
          <input
            type="number"
            v-model.number="formData.quantity"
            min="1"
            required
          />
        </div>
        
        <div class="form-actions">
          <button @click="handleSave" class="btn-primary" :disabled="!isValid">
            儲存
          </button>
          <button @click="$emit('close')" class="btn-secondary">
            取消
          </button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { reactive, computed } from 'vue'
  
  export default {
    name: 'ProductModal',
    
    props: {
      product: {
        type: Object,
        required: true
      },
      accounts: {
        type: Array,
        default: () => []
      },
      isEditing: {
        type: Boolean,
        default: false
      }
    },
    
    emits: ['save', 'close'],
    
    setup(props, { emit }) {
      const formData = reactive({
        account: props.product.account || '',
        quantity: props.product.quantity || 1
      })
      
      const isValid = computed(() => {
        return formData.account && formData.quantity > 0
      })
      
      const handleSave = () => {
        if (!isValid.value) {
          alert('請填寫完整資料')
          return
        }
        
        emit('save', {
          ...props.product,
          account: formData.account,
          quantity: formData.quantity,
          oldAccount: props.product.oldAccount || props.product.account
        })
      }
      
      return {
        formData,
        isValid,
        handleSave
      }
    }
  }
  </script>
  
  <style scoped>
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 1000;
  }
  
  .modal-content {
    background: white;
    padding: 30px;
    border-radius: 8px;
    width: 90%;
    max-width: 500px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
  }
  
  h3 {
    margin-bottom: 20px;
    color: #2c3e50;
  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  .form-group label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #555;
  }
  
  .form-group input,
  .form-group select {
    width: 100%;
    padding: 8px 12px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 16px;
  }
  
  .form-group input:disabled {
    background-color: #f5f5f5;
    cursor: not-allowed;
  }
  
  .required {
    color: #e74c3c;
  }
  
  .form-actions {
    display: flex;
    gap: 10px;
    justify-content: flex-end;
    margin-top: 30px;
  }
  
  .btn-primary,
  .btn-secondary {
    padding: 8px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s;
  }
  
  .btn-primary {
    background-color: #3498db;
    color: white;
  }
  
  .btn-primary:hover:not(:disabled) {
    background-color: #2980b9;
  }
  
  .btn-primary:disabled {
    background-color: #bdc3c7;
    cursor: not-allowed;
  }
  
  .btn-secondary {
    background-color: #95a5a6;
    color: white;
  }
  
  .btn-secondary:hover {
    background-color: #7f8c8d;
  }
  </style>
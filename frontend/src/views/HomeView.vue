<template>
  <div class="home">
    <h1>金融商品喜好紀錄系統</h1>
    
    <div class="user-selection">
      <label>選擇使用者：</label>
      <select v-model="selectedUserId" @change="onUserChange">
        <option value="">請選擇使用者</option>
        <option value="A123456789">王o明 (A123456789)</option>
        <option value="B987654321">陳o迅 (B987654321)</option>
        <option value="C456789012">林o豪 (C456789012)</option>
      </select>
    </div>

    <div v-if="selectedUserId" class="content">
      <LikedProductsTable
        :liked-products="likedProducts"
        :user-email="userEmail"
        :loading="loading.liked"
        @edit="handleEdit"
        @delete="handleDelete"
      />

      <ProductsTable
        :products="products"
        :loading="loading.products"
        @add="handleAdd"
      />
    </div>

    <ProductModal
      v-if="showModal"
      :product="modalProduct"
      :accounts="userAccounts"
      :is-editing="isEditing"
      @save="handleSave"
      @close="closeModal"
    />
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import api from '@/services/api'
import LikedProductsTable from '@/components/LikedProductsTable.vue'
import ProductsTable from '@/components/ProductsTable.vue'
import ProductModal from '@/components/ProductModal.vue'

export default {
  name: 'HomeView',
  
  components: {
    LikedProductsTable,
    ProductsTable,
    ProductModal
  },
  
  setup() {
    const selectedUserId = ref('')
    const likedProducts = ref([])
    const products = ref([])
    const userAccounts = ref([])
    const showModal = ref(false)
    const isEditing = ref(false)
    const modalProduct = ref({})
    const loading = ref({
      liked: false,
      products: false
    })
    
    const userEmail = computed(() => {
      return likedProducts.value[0]?.userEmail || ''
    })
    
    const loadProducts = async () => {
      try {
        loading.value.products = true
        products.value = await api.getProducts()
      } catch (error) {
        console.error('Error loading products:', error)
        alert('載入商品清單失敗')
      } finally {
        loading.value.products = false
      }
    }
    
    const loadUserData = async () => {
      if (!selectedUserId.value) return
      
      try {
        loading.value.liked = true
        const [accountsData, likedData] = await Promise.all([
          api.getUserAccounts(selectedUserId.value),
          api.getLikedProducts(selectedUserId.value)
        ])
        
        userAccounts.value = accountsData
        likedProducts.value = likedData
      } catch (error) {
        console.error('Error loading user data:', error)
        alert('載入使用者資料失敗')
      } finally {
        loading.value.liked = false
      }
    }
    
    const onUserChange = () => {
      loadUserData()
    }
    
    const handleAdd = (product) => {
      isEditing.value = false
      modalProduct.value = {
        productNo: product.productNo,
        productName: product.productName,
        account: '',
        quantity: 1
      }
      showModal.value = true
    }
    
    const handleEdit = (item) => {
      isEditing.value = true
      modalProduct.value = {
        productNo: item.productNo,
        productName: item.productName,
        account: item.account,
        quantity: item.quantity || 1,
        oldAccount: item.account
      }
      showModal.value = true
    }
    
    const handleDelete = async (item) => {
      if (!confirm(`確定要刪除 ${item.productName} 嗎？`)) return
      
      try {
        await api.deleteLikedProduct(selectedUserId.value, item.productNo, item.account)
        await loadUserData()
        alert('刪除成功')
      } catch (error) {
        console.error('Error deleting:', error)
        alert('刪除失敗')
      }
    }
    
    const handleSave = async (data) => {
      try {
        const requestData = {
          userId: selectedUserId.value,
          productNo: data.productNo,
          quantity: data.quantity,
          newAccount: data.account
        }
        
        if (isEditing.value) {
          requestData.oldAccount = data.oldAccount
          await api.updateLikedProduct(requestData)
        } else {
          await api.addLikedProduct(requestData)
        }
        
        closeModal()
        await loadUserData()
        alert(isEditing.value ? '更新成功' : '新增成功')
      } catch (error) {
        console.error('Error saving:', error)
        
        const errorMessage = error.message || '操作失敗，請稍後重試'
        
        if (error.message && error.message.includes('409')) {
          alert('該產品已存在於所選帳戶的喜好清單中')
        } else if (errorMessage.includes('already exists')) {
          alert('該產品已存在於所選帳戶的喜好清單中')
        } else if (errorMessage.includes('Quantity must be greater than 0')) {
          alert('數量必須大於 0')
        } else {
          alert(errorMessage)
        }
      }
    }
    
    const closeModal = () => {
      showModal.value = false
      modalProduct.value = {}
    }
    
    loadProducts()
    
    return {
      selectedUserId,
      likedProducts,
      products,
      userAccounts,
      userEmail,
      showModal,
      isEditing,
      modalProduct,
      loading,
      onUserChange,
      handleAdd,
      handleEdit,
      handleDelete,
      handleSave,
      closeModal
    }
  }
}
</script>

<style scoped>
.home {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  text-align: center;
  color: #2c3e50;
  margin-bottom: 30px;
}

.user-selection {
  text-align: center;
  margin-bottom: 30px;
}

.user-selection select {
  padding: 8px 16px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-left: 10px;
}

.content {
  display: flex;
  flex-direction: column;
  gap: 30px;
}
</style>
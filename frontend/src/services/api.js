const BASE_URL = '/api'

async function fetchApi(url, options = {}) {
  const response = await fetch(`${BASE_URL}${url}`, {
    headers: {
      'Content-Type': 'application/json',
      ...options.headers
    },
    ...options
  })
  
  if (!response.ok) {
    try {
      const errorData = await response.json()
      const errorMessage = errorData.message || errorData.error || `HTTP ${response.status}: ${response.statusText}`
      throw new Error(errorMessage)
    } catch (jsonError) {
      throw new Error(`HTTP ${response.status}: ${response.statusText}`)
    }
  }
  
  if (response.status === 204 || response.status === 201) return null
  return response.json()
}

export default {
  getProducts: () => fetchApi('/products'),
  getUserAccounts: (userId) => fetchApi(`/users/${userId}/accounts`),
  getLikedProducts: (userId) => fetchApi(`/liked-products/${userId}`),
  addLikedProduct: (data) => fetchApi('/liked-products', { method: 'POST', body: JSON.stringify(data) }),
  updateLikedProduct: (data) => fetchApi('/liked-products', { method: 'PUT', body: JSON.stringify(data) }),
  deleteLikedProduct: (userId, productNo, account) => fetchApi(`/liked-products/${userId}/${productNo}/${account}`, { method: 'DELETE' })
}
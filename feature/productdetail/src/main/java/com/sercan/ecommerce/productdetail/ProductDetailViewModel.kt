package com.sercan.ecommerce.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sercan.ecommerce.database.dao.ProductDao
import com.sercan.ecommerce.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ProductDetailUiState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productDao: ProductDao,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState
    
    init {
        savedStateHandle.get<Int>("productId")?.let { productId ->
            loadProduct(productId)
        }
    }
    
    private fun loadProduct(productId: Int) {
        _uiState.update { it.copy(isLoading = true) }
        
        // Örnek ürün verisi (gerçek uygulamada bu veri veritabanından gelecek)
        val sampleProduct = Product(
            id = productId,
            name = "Air Max 200 SE",
            price = 30.99,
            imageUrl = "https://via.placeholder.com/400",
            description = "The Nike Air Max 200 SE brings you heritage Air Max DNA with a modern twist. The upper features a mix of materials for a fresh look, while visible Max Air cushioning delivers comfort that lasts.",
            sizes = listOf("UK 6", "UK 7", "UK 8", "UK 9"),
            colors = listOf("Red", "Blue")
        )
        
        _uiState.update {
            it.copy(
                product = sampleProduct,
                isLoading = false
            )
        }
    }
} 
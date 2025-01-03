package com.sercan.ecommerce.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sercan.ecommerce.database.dao.ProductDao
import com.sercan.ecommerce.database.dao.CartDao
import com.sercan.ecommerce.database.entity.ProductEntity
import com.sercan.ecommerce.database.entity.CartItemEntity
import com.sercan.ecommerce.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
    val selectedCategory: String = "Tümü",
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productDao: ProductDao,
    private val cartDao: CartDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
        observeProducts()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            productDao.getProducts()
                .map { entities -> entities.map { it.toProduct() } }
                .catch { e ->
                    _uiState.update { it.copy(error = e.message, isLoading = false) }
                }
                .collect { products ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            products = products,
                            filteredProducts = filterProductsByCategory(products, currentState.selectedCategory),
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun filterProductsByCategory(products: List<Product>, category: String): List<Product> {
        return if (category == "Tümü") {
            products
        } else {
            products.filter { it.category == category }
        }
    }

    fun filterProducts(category: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedCategory = category,
                filteredProducts = filterProductsByCategory(currentState.products, category)
            )
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                val sampleProducts = listOf(
                    ProductEntity(
                        id = 1,
                        name = "Nike Air Max",
                        price = 2499.99,
                        imageUrl = "https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/99486859-0ff3-46b4-949b-2d16af2ad421/custom-nike-air-max-90-by-you.png",
                        description = "Nike Air Max spor ayakkabı, maksimum konfor ve şık tasarım.",
                        category = "Ayakkabı",
                        sizes = listOf("40", "41", "42", "43", "44"),
                        colors = listOf("Siyah", "Beyaz", "Kırmızı")
                    ),
                    ProductEntity(
                        id = 2,
                        name = "Adidas Superstar",
                        price = 1999.99,
                        imageUrl = "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/7ed0855435194229a525aad6009a0497_9366/Superstar_Ayakkabi_Beyaz_EG4958_01_standard.jpg",
                        description = "Adidas Superstar, klasik tasarımıyla her tarza uygun.",
                        category = "Ayakkabı",
                        sizes = listOf("39", "40", "41", "42", "43"),
                        colors = listOf("Beyaz", "Siyah", "Altın")
                    ),
                    ProductEntity(
                        id = 3,
                        name = "Puma RS-X",
                        price = 1799.99,
                        imageUrl = "https://images.puma.com/image/upload/f_auto,q_auto,b_rgb:fafafa,w_600,h_600/global/368845/01/sv01/fnd/TUR/fmt/png/RS-X-Efekt-Ayakkab%C4%B1",
                        description = "Puma RS-X spor ayakkabı, retro tasarım modern teknoloji.",
                        category = "Ayakkabı",
                        sizes = listOf("39", "40", "41", "42", "43"),
                        colors = listOf("Beyaz", "Mavi", "Gri")
                    ),
                    ProductEntity(
                        id = 4,
                        name = "New Balance 574",
                        price = 2299.99,
                        imageUrl = "https://nb.scene7.com/is/image/NB/ml574evg_nb_02_i?$$&wid=440&hei=440",
                        description = "New Balance 574, rahatlık ve stil bir arada.",
                        category = "Ayakkabı",
                        sizes = listOf("40", "41", "42", "43", "44"),
                        colors = listOf("Gri", "Lacivert", "Yeşil")
                    ),
                    ProductEntity(
                        id = 5,
                        name = "Oversize T-Shirt",
                        price = 399.99,
                        imageUrl = "https://cdn.dsmcdn.com/ty662/product/media/images/20221220/11/241476122/652095385/1/1_org_zoom.jpg",
                        description = "Rahat kesim pamuklu oversize t-shirt.",
                        category = "Giyim",
                        sizes = listOf("S", "M", "L", "XL"),
                        colors = listOf("Siyah", "Beyaz", "Gri")
                    ),
                    ProductEntity(
                        id = 6,
                        name = "Slim Fit Jean",
                        price = 699.99,
                        imageUrl = "https://cdn.dsmcdn.com/ty101/product/media/images/20210407/12/78068096/19117515/1/1_org_zoom.jpg",
                        description = "Yüksek kalite slim fit kot pantolon.",
                        category = "Giyim",
                        sizes = listOf("30/32", "32/32", "34/32", "36/32"),
                        colors = listOf("Mavi", "Siyah")
                    ),
                    ProductEntity(
                        id = 7,
                        name = "Basic Sweatshirt",
                        price = 549.99,
                        imageUrl = "https://cdn.dsmcdn.com/ty885/product/media/images/20230803/12/395485314/968078435/1/1_org_zoom.jpg",
                        description = "Yumuşak dokulu basic sweatshirt.",
                        category = "Giyim",
                        sizes = listOf("S", "M", "L", "XL"),
                        colors = listOf("Siyah", "Gri", "Lacivert")
                    ),
                    ProductEntity(
                        id = 8,
                        name = "Kapüşonlu Sweatshirt",
                        price = 599.99,
                        imageUrl = "https://cdn.dsmcdn.com/ty952/product/media/images/20230524/18/351630751/904786090/1/1_org_zoom.jpg",
                        description = "Rahat kesim kapüşonlu sweatshirt.",
                        category = "Giyim",
                        sizes = listOf("S", "M", "L", "XL"),
                        colors = listOf("Siyah", "Gri", "Bordo")
                    ),
                    ProductEntity(
                        id = 9,
                        name = "Deri Cüzdan",
                        price = 449.99,
                        imageUrl = "https://cdn.dsmcdn.com/ty252/product/media/images/20211125/11/1063213/10892435/2/2_org_zoom.jpg",
                        description = "Hakiki deri erkek cüzdanı.",
                        category = "Aksesuar",
                        sizes = listOf("Standart"),
                        colors = listOf("Kahverengi", "Siyah")
                    ),
                    ProductEntity(
                        id = 10,
                        name = "Güneş Gözlüğü",
                        price = 899.99,
                        imageUrl = "https://cdn.dsmcdn.com/ty545/product/media/images/20220916/18/177257191/576078858/1/1_org_zoom.jpg",
                        description = "UV korumalı polarize güneş gözlüğü.",
                        category = "Aksesuar",
                        sizes = listOf("Standart"),
                        colors = listOf("Siyah", "Gold")
                    ),
                    ProductEntity(
                        id = 11,
                        name = "Deri Kemer",
                        price = 299.99,
                        imageUrl = "https://cdn.dsmcdn.com/ty558/product/media/images/20221006/11/188357311/584847427/1/1_org_zoom.jpg",
                        description = "Klasik tasarım deri kemer.",
                        category = "Aksesuar",
                        sizes = listOf("90", "95", "100", "105"),
                        colors = listOf("Kahverengi", "Siyah")
                    ),
                    ProductEntity(
                        id = 12,
                        name = "Spor Çanta",
                        price = 799.99,
                        imageUrl = "https://cdn.dsmcdn.com/ty547/product/media/images/20220919/11/178259314/576834054/1/1_org_zoom.jpg",
                        description = "Dayanıklı ve şık spor çantası.",
                        category = "Aksesuar",
                        sizes = listOf("Standart"),
                        colors = listOf("Siyah", "Lacivert", "Gri")
                    )
                )
                productDao.insertProducts(sampleProducts)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            try {
                productDao.toggleFavorite(product.id, !product.isFavorite)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            try {
                cartDao.insertCartItem(
                    CartItemEntity(
                        id = 0,
                        productId = product.id,
                        name = product.name,
                        price = product.price,
                        imageUrl = product.imageUrl,
                        quantity = 1,
                        selectedSize = product.sizes.firstOrNull(),
                        selectedColor = product.colors.firstOrNull()
                    )
                )
                // Başarılı mesajı
                _uiState.update { it.copy(error = "${product.name} sepete eklendi") }
                clearError()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun clearError() {
        viewModelScope.launch {
            delay(2000) // 2 saniye sonra error'u temizle
            _uiState.update { it.copy(error = null) }
        }
    }
} 
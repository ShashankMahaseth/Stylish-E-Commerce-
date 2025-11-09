import com.example.stylishe_commerceapp.data.remote.Dimensions
import com.example.stylishe_commerceapp.data.remote.Meta
import com.example.stylishe_commerceapp.data.remote.Review
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val availabilityStatus: String? = null,
    val brand: String? = null,
    val category: String? = null,
    val description: String? = null,
    val dimensions: Dimensions? = null,
    val discountPercentage: Double? = null,
    val id: Int? = null,
    val images: List<String>? = emptyList(),
    val meta: Meta? = null,
    val minimumOrderQuantity: Int? = null,
    val price: Double? = null,
    val rating: Double? = null,
    val returnPolicy: String? = null,
    val reviews: List<Review>? = null,
    val shippingInformation: String? = null,
    val sku: String? = null,
    val stock: Int? = null,
    val tags: List<String>? = emptyList(),
    val thumbnail: String? = null,
    val title: String? = null,
    val warrantyInformation: String? = null,
    val weight: Int? = null
)

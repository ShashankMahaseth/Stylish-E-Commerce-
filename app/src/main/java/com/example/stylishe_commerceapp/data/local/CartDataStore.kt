package com.example.stylishe_commerceapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.stylishe_commerceapp.data.remote.Product
import com.example.stylishe_commerceapp.domain.model.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class CartDataStore(private val context: Context) {
    companion object {
        private val Context.cartDataStore: DataStore<Preferences> by preferencesDataStore("cart_preferences")//Creating folder/file in internal Storage of our device to Store key and value
        private val CART_ITEMS = stringPreferencesKey("cart_items")//key
    }

    private val json = Json {//this configure make JSON flexible
        ignoreUnknownKeys = true
        isLenient = true
    }
    val cartItems: Flow<List<CartItem>> = context.cartDataStore.data.map { preferences ->//Every time DataStore updates, the Flow sends the new value.
            val itemJson = preferences[CART_ITEMS]
                ?: "[]"//preferences every emission gives us current stored values
            try {
                json.decodeFromString<List<CartItem>>(itemJson)//decoding JSON to List<CartItem>
            } catch (e: Exception) {
                emptyList()
            }
        }

    suspend fun addToCart(product: Product, quantity: Int = 1) {
        context.cartDataStore.edit { preferences ->
            val currentJson = preferences[CART_ITEMS] ?: "[]"
            val currentItems = try {
                json.decodeFromString<List<CartItem>>(currentJson)
            } catch (e: Exception) {
                emptyList()
            }
            val existingItemIndex = currentItems.indexOfFirst { it.product.id == product.id }//check if product already exist

            val updateItems = if (existingItemIndex != -1) {
                currentItems.toMutableList()
                    .apply {//if product already exist then Increase quantity
                        val existingItem = this[existingItemIndex]
                        this[existingItemIndex] =
                            this[existingItemIndex].copy(quantity = existingItem.quantity + quantity)
                    }
            } else {
                currentItems + CartItem(product, quantity)//if product not exist then add new item
            }
            preferences[CART_ITEMS] =
                json.encodeToString(updateItems)//convert back to JSON and save

        }

    }

    suspend fun removeFromCart(productId: Int) {
        context.cartDataStore.edit { preferences ->
            val currentJson = preferences[CART_ITEMS] ?: "[]"
            val currentItems = try {
                json.decodeFromString<List<CartItem>>(currentJson)
            } catch (e: Exception) {
                emptyList()
            }

            val updatedItems = currentItems.filter {  // filter out unwanted products
                it.product.id != productId
            }
            preferences[CART_ITEMS] = json.encodeToString(updatedItems) //save updated list

        }

    }

    suspend fun updateQuantity(productId: Int, quantity: Int) {
        context.cartDataStore.edit { preferences ->
            val currentJson = preferences[CART_ITEMS] ?: "[]"
            val currentItems = try {
                json.decodeFromString<List<CartItem>>(currentJson)
            } catch (e: Exception) {
                emptyList()
            }
            val updatedItems = currentItems.map { item ->//replace the matching item
                if (item.product.id == productId) {
                    item.copy(quantity = quantity)
                } else {
                    item
                }

            }.filter {
                it.quantity > 0 // remove items with quantity  = 0
            }
            preferences[CART_ITEMS] = json.encodeToString(updatedItems)  //save updated list

        }

    }

    suspend fun clearCart() {
        context.cartDataStore.edit { preferences ->
            preferences[CART_ITEMS] = "[]"

        }

    }

    suspend fun getCartItemCount(): Int {
        var count = 0
        context.cartDataStore.data.map { preferences ->//read dataStoreFlow
            val currentJson = preferences[CART_ITEMS] ?: "[]"
            val currentItems = try {
                json.decodeFromString<List<CartItem>>(currentJson)
            } catch (e: Exception) {
                emptyList()

            }
            count = currentItems.sumOf { it.quantity } //sum quantity

        }.collect { }
        return count
    }


}
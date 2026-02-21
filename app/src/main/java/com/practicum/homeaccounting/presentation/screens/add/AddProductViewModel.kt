package com.practicum.homeaccounting.presentation.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.homeaccounting.domain.Product
import com.practicum.homeaccounting.domain.ProductCategory
import com.practicum.homeaccounting.domain.usecase.AddProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {

    // Состояние формы
    private val _uiState = MutableStateFlow(AddProductUiState())
    val uiState: StateFlow<AddProductUiState> = _uiState.asStateFlow()

    // Валидация полей
    fun updateName(name: String) {
        _uiState.update { it.copy(
            name = name,
            isNameValid = name.isNotBlank()
        ) }
    }

    fun updateCategory(category: ProductCategory) {
        _uiState.update { it.copy(category = category) }
    }

    fun updateExpiryDate(timestamp: Long) {
        _uiState.update { it.copy(
            expiryDate = timestamp,
            isExpiryDateValid = timestamp > System.currentTimeMillis()
        ) }
    }

    fun updateQuantity(quantity: String) {
        val qty = quantity.toIntOrNull() ?: 1
        _uiState.update { it.copy(
            quantity = qty,
            quantityText = quantity,
            isQuantityValid = qty > 0
        ) }
    }

    fun updateNotes(notes: String) {
        _uiState.update { it.copy(notes = notes) }
    }

    // Проверка, можно ли сохранить
    fun isFormValid(): Boolean {
        return _uiState.value.isNameValid &&
                _uiState.value.isExpiryDateValid &&
                _uiState.value.isQuantityValid
    }

    // Сохранение продукта
    fun saveProduct(onSuccess: () -> Unit) {
        if (!isFormValid()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }

            try {
                val product = Product(
                    id = UUID.randomUUID().toString(),
                    name = _uiState.value.name,
                    expiryDate = _uiState.value.expiryDate,
                    quantity = _uiState.value.quantity,
                    category = _uiState.value.category,
                    notes = _uiState.value.notes.takeIf { it.isNotBlank() },
                    createdAt = System.currentTimeMillis()
                )

                addProductUseCase(product)
                onSuccess()

            } catch (e: Exception) {
                _uiState.update { it.copy(
                    errorMessage = "Ошибка сохранения: ${e.message}",
                    isSaving = false
                ) }
            } finally {
                _uiState.update { it.copy(isSaving = false) }
            }
        }
    }

    // Сброс ошибки
    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}

// Состояние экрана добавления
data class AddProductUiState(
    val name: String = "",
    val isNameValid: Boolean = false,

    val category: ProductCategory = ProductCategory.OTHER,

    val expiryDate: Long = System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000, // +7 дней
    val isExpiryDateValid: Boolean = true,

    val quantity: Int = 1,
    val quantityText: String = "1",
    val isQuantityValid: Boolean = true,

    val notes: String = "",

    val isSaving: Boolean = false,
    val errorMessage: String? = null
)

package com.mreyesco.store.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mreyesco.core.repositories.StoreRepository
import com.mreyesco.store.viewmodel.mapper.CurrencyMapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class StoreViewModel(private val storeRepository: StoreRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _products = MutableLiveData<List<ProductViewModel>>()
    val subtotal: ObservableField<String> = ObservableField()

    val isLoading: LiveData<Boolean> = _isLoading
    val products: LiveData<List<ProductViewModel>> = _products

    init {
        subtotal.set(CurrencyMapper.getFormattedCurrency(0.0))
    }

    fun getProducts() {
        performRequest(storeRepository.getProducts()) { products ->
            _products.postValue(products.map {
                ProductViewModel(it) {
                    calculateSubtotal()
                }
            })
        }
    }

    fun goToCheckout() {

    }

    private fun calculateSubtotal() {
        _products.value?.let { products ->
            if (products.isNotEmpty()) {
                val totals = arrayListOf<Double>()
                val promo = hashMapOf<String, Int>()
                products.forEach {
                    val amount = it.amount.get()
                    if (amount > 0) {
                        promo[it.code] = amount
                    }
                }
                promo.keys.forEach { code ->
                    val totalByItem = promo[code] ?: 0
                    if (totalByItem >= 3 && code == CODE_TSHIRT) {
                        totals.add(totalByItem * 19.0)
                    } else {
                        val item = products.find { it.code == code }
                        item?.let {
                            val value = if (totalByItem > 1) {
                                if (totalByItem % 2 == 0) it.rawPrice * (totalByItem / 2)
                                else it.rawPrice + (it.rawPrice * ((totalByItem - 1) / 2))
                            } else (it.rawPrice * totalByItem)
                            totals.add(value)
                        }

                    }
                }
                val subtotalValue = if (totals.isNotEmpty()) totals.reduce { total, x -> total + x }
                else 0.0
                subtotal.set(CurrencyMapper.getFormattedCurrency(subtotalValue))
            }
        }
    }

    private fun <T : Any> performRequest(
        observable: Observable<T>,
        onErrorResponse: (Throwable) -> Unit = {},
        onSuccessResponse: (T) -> Unit = {}
    ) {
        disposable.add(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _isLoading.postValue(true)
            }
            .subscribe({
                _isLoading.postValue(false)
                onSuccessResponse.invoke(it)
            }, {
                _isLoading.postValue(false)
                onErrorResponse.invoke(it)
            })
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
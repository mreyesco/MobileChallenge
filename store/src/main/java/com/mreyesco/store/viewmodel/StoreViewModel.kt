package com.mreyesco.store.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mreyesco.core.repositories.StoreRepository
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

    fun getProducts() {
        performRequest(storeRepository.getProducts()) { products ->
            _products.postValue(products.map { ProductViewModel(it) })
        }
    }

    fun goToCheckout(){

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
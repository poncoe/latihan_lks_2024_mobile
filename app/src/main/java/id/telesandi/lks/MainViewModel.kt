package id.telesandi.lks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.telesandi.lks.model.Produk
import id.telesandi.lks.network.ProdukApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val data = MutableLiveData<List<Produk>>()
    private val status = MutableLiveData<ProdukApi.ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(ProdukApi.ApiStatus.LOADING)
            try {
                data.postValue(ProdukApi.service.getProduk())
                status.postValue(ProdukApi.ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.postValue(ProdukApi.ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Produk>> = data
    fun getStatus(): LiveData<ProdukApi.ApiStatus> = status
}
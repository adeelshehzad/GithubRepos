package com.adeel.githubrepos.networking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

open class BaseViewModel : ViewModel() {
    val loadingLiveData = MutableLiveData<Event<Boolean>>()
    val errorLiveData = MutableLiveData<Event<Boolean>>()

    private val compositeDisposable = CompositeDisposable()

    fun <T> getResponse(single: Single<T>, responseLiveData: MutableLiveData<Event<T>>) {
        loadingLiveData.value = Event(true)
        compositeDisposable.add(
            single.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<T>() {
                    override fun onSuccess(t: T) {
                        loadingLiveData.value = Event(false)
                        responseLiveData.value = Event(t)
                    }

                    override fun onError(e: Throwable) {
                        loadingLiveData.value = Event(false)
                        errorLiveData.value = Event(true)
                        e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
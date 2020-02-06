package com.adeel.githubrepos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adeel.githubrepos.model.Filter

class FilterSharingViewModel : ViewModel() {
    val filterLanguage = MutableLiveData<Filter>()
    val filterTrendingSince = MutableLiveData<Filter>()
}
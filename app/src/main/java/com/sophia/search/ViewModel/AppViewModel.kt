package com.sophia.search.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sophia.search.Room.AppRepository
import com.sophia.search.Room.Infor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class AppViewModel(private val repository: AppRepository) : ViewModel() {

//    enum class FragmentType {
//        EDIT, INFOR, MINJI
//    }
//
//    private val _fragmentTypeLivaData = MutableLiveData(FragmentType.EDIT)
//    val fragmentTypeLiveData: LiveData<FragmentType>
//        get() = _fragmentTypeLivaData
//
//    fun replaceFragment(fragmentType: FragmentType) {
//        _fragmentTypeLivaData.value = fragmentType
//    }

    val inforListLiveData = repository.getAllinfor()

    private val _editLiveData = MutableLiveData<Infor?>()
    val editLiveData: LiveData<Infor?>
        get() = _editLiveData

    fun setEditMemo(position: Int) {
        val infor = inforListLiveData.value?.get(position) ?: throw Exception()
        _editLiveData.value = infor
    }


    fun saveinfor(name: String, phnumber: String) {
        viewModelScope.launch {
            if (hashCurrentInfor()) {
                updateinfor(name, phnumber)
            } else {
                insertinfor(name, phnumber)
            }
        }
    }
    private fun hashCurrentInfor() = _editLiveData.value != null


    fun insertinfor(name: String, phnumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val infor = Infor(name, phnumber)
            repository.insertinfor(infor)
        }
    }

    fun updateinfor(name: String, phnumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val infor = _editLiveData.value?.apply {
                this.name = name
                this.phnumber = phnumber
            } ?: throw Exception()

            repository.updateinfor(infor)

            launch(Dispatchers.Main) {
                _editLiveData.value = null
            }
        }
    }

    fun deleteinfor(position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val infor = inforListLiveData.value?.get(position) ?: throw Exception()
            repository.deleteinfor(infor)
        }
    }
}
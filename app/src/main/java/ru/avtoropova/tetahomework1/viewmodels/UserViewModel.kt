package ru.avtoropova.tetahomework1.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.avtoropova.tetahomework1.model.room.AppDB
import ru.avtoropova.tetahomework1.model.room.entities.User
import ru.avtoropova.tetahomework1.utils.SHARED_PREFERENCES
import ru.avtoropova.tetahomework1.utils.USER_ID


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDB.getAppDB(application)

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User>
        get() = _userData

    init {
        initUser()
    }

    private fun initUser() {
        val sharedPrefs = getApplication<Application>().getSharedPreferences(
            SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
        val userId = sharedPrefs.getLong(USER_ID, -1)
        viewModelScope.launch {
            if (userId == -1L) {

                val newUserId = db!!.userDao().insertUser(User())
                sharedPrefs.edit().putLong(USER_ID, newUserId).apply()
                val user = db.userDao().getUserById(newUserId)
                _userData.postValue(user)
            } else {
                val user = db!!.userDao().getUserById(userId)
                _userData.postValue(user)
            }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            db!!.userDao().updateUser(user)
            _userData.postValue(user)
        }
    }
}
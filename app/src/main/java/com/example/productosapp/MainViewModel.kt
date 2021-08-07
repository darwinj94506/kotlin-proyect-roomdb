package com.example.productosapp
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.productosapp.data.Injector
import com.example.productosapp.data.User
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Injector.provideRepository(application)

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val stateLogin = MutableLiveData<Boolean>(false)
    val registerStatus = MutableLiveData<Boolean>()


    init {
        val preferences = getApplication<Application>().getSharedPreferences("APP", Context.MODE_PRIVATE)
        userName.value = preferences.getString("user", "darwin")
        password.value = preferences.getString("pass", "123457")
    }

    fun authenticate() {
        val user = userName.value ?: return
        val pass = password.value ?: return
        viewModelScope.launch {
            val userdb = repository.login(user, pass)
            if(userdb!=null){
                stateLogin.value = true
                saveForm(user, pass)
            }else{
                Toast.makeText(getApplication(), "Usuario no registrado", Toast.LENGTH_SHORT).show()
                stateLogin.value= false
            }

        }
    }

    fun register() {
        val user = userName.value ?: return
        val pass = password.value ?: return
        viewModelScope.launch {
            Toast.makeText(getApplication(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
            repository.register(User(username= user, password = pass))
            registerStatus.value= true
        }
    }

    private fun saveForm(user: String, pass: String) {
        val preferences =
            getApplication<Application>().getSharedPreferences("APP", Context.MODE_PRIVATE)
        preferences.edit()
            .putString("user", user)
            .putString("pass", pass)
            .apply()
    }
}
package com.example.findfilm.data

import android.content.Context
import android.content.SharedPreferences

class PreferenceProvider(context: Context) {

    //Нам нужен контекст приложения
    private val appContext = context.applicationContext

    //Создаем экземпляр класса SharedPreferences
    private val preference: SharedPreferences = appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)

    init {
        //Логика для первого запуска приложения, чтоб положить наши настройки
        //Сюда потом можно добавлять другие настройки
        if(preference.getBoolean(KEY_FIRST_LAUNCH, false)){
            preference.edit().apply {
                putString(KEY_DEFAULT_CATEGORY, DEFAULT_CATEGORY)
                putBoolean(KEY_FIRST_LAUNCH, false)
                apply()
            }
        }
    }

    //Category prefs
    //Сохраняем категорию
    fun saveDefaultCategory(category: String){
        preference.edit().apply {
            putString(KEY_DEFAULT_CATEGORY, category)
            apply()
        }
    }

    //Получаем категорию
    fun getDefaultCategory(): String{
        return preference.getString(KEY_DEFAULT_CATEGORY, DEFAULT_CATEGORY) ?: DEFAULT_CATEGORY
    }

    //Ключи для наших настроек, по ним мы их будем получать
    companion object{
        private const val KEY_FIRST_LAUNCH = "first_launch"
        private const val KEY_DEFAULT_CATEGORY = "default_category"
        private const val DEFAULT_CATEGORY = "popular"
    }
}
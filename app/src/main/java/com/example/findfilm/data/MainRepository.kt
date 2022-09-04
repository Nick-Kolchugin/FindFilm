package com.example.findfilm.data

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.findfilm.R
import com.example.findfilm.SplashActivity
import com.example.findfilm.data.db.DataBaseHelper
import com.example.findfilm.domain.Film
import com.example.findfilm.view.activities.MainActivity
import com.example.findfilm.view.fragments.HomeFragment

class MainRepository(dataBaseHelper: DataBaseHelper) {

    //Инициализируем объект для взаимодействия с БД
    private val sqlDb = dataBaseHelper.readableDatabase
    //Создаем курсор для обработки запросов из БД
    private lateinit var cursor: Cursor

    fun putToDb(film: Film){
        //Создаем объект ContentValues, который будет хранить пары ключ значения,
        //для того чтобы класть нужные данные в нужные столбцы
        val cv = ContentValues()
        cv.apply {
            put(DataBaseHelper.COLUMN_TITLE, film.title)
            put(DataBaseHelper.COLUMN_POSTER, film.poster)
            put(DataBaseHelper.COLUMN_DESCRIPTION, film.description)
            put(DataBaseHelper.COLUMN_RATING, film.rating)
        }
        //Кладем фильм в БД
        sqlDb.insert(DataBaseHelper.TABLE_NAME, null, cv)
    }

    fun getAllFilmsDB(): List<Film>{
        //Созздаем курсор на основании запроса "Получить все из таблицы"
        cursor = sqlDb.rawQuery("SELECT * FROM ${DataBaseHelper.TABLE_NAME}", null)
        //Сюда будем сохранять результат получения данных
        val result = mutableListOf<Film>()
        //Проверяем, есть ли хоть одна строка в ответе на запрос
        if(cursor.moveToFirst()){
            //Итерируемся по таблице, пока есть записи, и создаем на основании объект Film
            do{
                val title = cursor.getString(1)
                val poster = cursor.getString(2)
                val description = cursor.getString(3)
                val rating = cursor.getDouble(4)

                result.add(Film(
                    title = title,
                    poster = poster,
                    description = description,
                    rating = rating,
                ))
            } while (cursor.moveToNext())
        }

        //Возвращаем список фильмов
        return result
    }

}
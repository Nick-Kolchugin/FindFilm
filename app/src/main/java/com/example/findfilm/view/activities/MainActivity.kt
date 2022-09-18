package com.example.findfilm.view.activities

import android.app.Activity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.findfilm.FavoritesDB
import com.example.findfilm.R
import com.example.findfilm.databinding.ActivityMainBinding
import com.example.findfilm.data.entity.Film
import com.example.findfilm.view.fragments.*
import java.util.concurrent.Executors
import kotlin.math.hypot
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    //Благодря использованию ViewDataBinding нажняя строчка больше не используется
    //lateinit var topAppBar: MaterialToolbar
    val favoritesDB = FavoritesDB()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        window.allowEnterTransitionOverlap = true
        super.onCreate(savedInstanceState)
        //Инициализируем объект
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Передаем его в объект
        setContentView(binding.root)

        window.enterTransition = Slide(Gravity.END).apply {
            mode = Slide.MODE_IN
            excludeTarget(android.R.id.navigationBarBackground, true)
        }

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment(), "home")
            //.addToBackStack(null)
            .commit()
        //Благодря использованию ViewDataBinding нажняя строчка больше не используется
        //topAppBar = findViewById<MaterialToolbar>(R.id.top_app_bar)
        //val navToolBar = findViewById<BottomNavigationView>(R.id.bottom_nav_bar)



        binding.topAppBar.setNavigationOnClickListener {
            Toast.makeText(this, R.string.menu, Toast.LENGTH_SHORT).show()
        }
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.top_bar_settings -> {
                    Toast.makeText(this, R.string.settings, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        binding.bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_favorite -> {
                    val tag = "favorite"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: FavoritesFragment(), tag)
                    true
                }

                R.id.nav_home -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    //В первом параметре, если фрагмент не найден и метод вернул null, то с помощью
                    // элвиса мы вызываем создание нового фрагмента
                    changeFragment(fragment ?: HomeFragment(), tag)
                    true
                }

                R.id.nav_catalog -> {
                    val tag = "selections"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: SelectionsFragment(), tag)
                    true
                }

                R.id.nav_watch_later -> {
                    val tag = "watch_later"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: WatchLaterFragment(), tag)
                    true
                }

                R.id.nav_settings ->{
                    val tag = "settings"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?:SettingsFragment(), tag)
                    true
                }

                else -> false
            }
        }

    }

    override fun onBackPressed() {
        finish()
    }

    fun launchDetailsFragment(film: Film) {
        //создаем посылку
        val bundle = Bundle()
        //кладем наш фильм в посылку
        bundle.putParcelable("film", film)
        //кладем фрагмент с деталями в переменную
        val fragment = DetailsFragment()
        //прикрепляем нашу посылку к фрагменту
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    //ищем фрагмент по теу, если он есть возвращаем его, если нет то null
    private fun checkFragmentExistence(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

    //функция запуска фрагмента
    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            //.addToBackStack(null)
            .commit()
    }

    object AnimationHelper {
        //Это переменная для того, чтобы круг проявления расходился именно от иконки меню навигации
        private const val menuItems = 5

        //В метод у нас приходит 3 параметра:
        //1 - наше rootView, которое одновременно является и контейнером
        //и объектом анимации
        //2 - активити для того, чтобы вернуть выполнение нового треда в UI поток
        //3 - позиция в меню навигации, чтобы круг проявления расходился именно от иконки меню навигации
        fun performFragmentCircularRevealAnimation(
            rootView: View,
            activity: Activity,
            position: Int
        ) {
            //Создаем новый тред
            Executors.newSingleThreadExecutor().execute {
                //в бесконечном цикле проверяем, когда наше анимированное view будет прикреплено к экрану
                while (true) {
                    //когда оно будет прикреплено, выполним код
                    if (rootView.isAttachedToWindow) {
                        //Возвращаемся в главный тред, чтобы выполнить анимацию
                        activity.runOnUiThread {
                            //суперсложная математика вычисления старта анимации
                            val itemCenter = rootView.width / (menuItems * 2)
                            val step = (itemCenter * 2) * (position - 1) + itemCenter

                            val x: Int = step
                            val y: Int = rootView.y.roundToInt() + rootView.height

                            val startRadius = 0
                            val endRadius = hypot(rootView.width.toDouble(), rootView.height.toDouble())
                            //создаем саму анимацию
                            ViewAnimationUtils.createCircularReveal(rootView, x, y, startRadius.toFloat(), endRadius.toFloat()).apply {
                                //Устанавливаем время анимации
                                duration = 500
                                //интерполятор для более естественной анимации
                                interpolator = AccelerateDecelerateInterpolator()
                                //запуск
                                start()
                            }
                            //выставляем видимость нашего элемента
                            rootView.visibility = View.VISIBLE
                        }
                        return@execute
                    }
                }
            }
        }
    }

}
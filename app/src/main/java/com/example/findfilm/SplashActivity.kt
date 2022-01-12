package com.example.findfilm

import android.animation.Animator
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import kotlinx.coroutines.delay
import java.util.concurrent.Executors

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.exitTransition = Slide(Gravity.START).apply {
            mode = Slide.MODE_OUT
            excludeTarget(android.R.id.navigationBarBackground, true)
        }

        val splashAnimationView = findViewById<LottieAnimationView>(R.id.splash_animation)
        splashAnimationView.apply {
            setAnimation(R.raw.splash_anim)
            playAnimation()
        }
        splashAnimationView.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this@SplashActivity)
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent, activityOptions.toBundle())

                //Чтобы при нажатии назад не возвращаться на это активити завершаем его спустя
                //1.5 секунды. Это нужно чтоб переход на следующее активити был плавным,а не прерывестым
                //из-за скорого завершения первого активти
                Executors.newSingleThreadExecutor().execute{
                    Thread.sleep(1500)
                    finish()
                }

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }

        })


    }
}
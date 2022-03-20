package com.example.findfilm.view.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.findfilm.R

class RatingDonutView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    //Овал для рисования сегментов прогресс бара
    private var oval = RectF()

    //Координаты центра View, и радиус
    private var radius = 0f
    private var centerX = 0f
    private var centerY = 0f

    //Толщина линии прогресса
    private var stroke = 10f

    //Значение прогресса от 0 - 100
    private var progress = 50

    //Значение размера тектса внутри кольца
    private var scaleSize = 60f

    //Краски для наших фигур
    private lateinit var strokePaint: Paint
    private lateinit var digitPaint: Paint
    private lateinit var circlePaint: Paint

    init {
        //Получаем атрибуты и устанавливаем их в соответствующие поля
        val a =
            context.theme.obtainStyledAttributes(attributeSet, R.styleable.RatingDonutView, 0, 0)
        try {
            stroke = a.getFloat(
                R.styleable.RatingDonutView_stroke, stroke
            )
            progress = a.getInt(
                R.styleable.RatingDonutView_progress, progress
            )
        } finally {
            a.recycle()
        }

        //Инициализируем первоначальные краски
        initPaint()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val chooseWidth = choseDimension(widthMode, widthSize)
        val chooseHeight = choseDimension(heightMode, heightSize)

        val minSide = Math.min(chooseWidth, chooseHeight)
        centerX = minSide.div(2f)
        centerY = minSide.div(2f)

        setMeasuredDimension(minSide, minSide)
    }

    override fun onDraw(canvas: Canvas?) {
        //Рисуем кольцо и задний фон
        drawRating(canvas)
        //Рисуем цифры
        drawText(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = if (width > height) {
            height.div(2f)
        } else {
            width.div(2f)
        }
    }

    private fun choseDimension(mode: Int, size: Int): Int {
        return when (mode) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> size
            else -> 300
        }
    }

    private fun drawRating(canvas: Canvas?) {
        //Здесь мы можем регулировать размер нашего кольца
        val scale = radius * 0.8f
        //Сохраняем канвас
        canvas?.save()
        //Перемещаем нулевые координаты канваса в центр, так проще рисовать круг
        canvas?.translate(centerX, centerY)
        //Устанавливаем размеры под наш овал
        oval.set(0f - scale, 0f - scale, scale, scale)
        //Рисуем задний фон(Желательно его рисовать один раз в bitmap, так как он статичный)
        canvas?.drawCircle(0f, 0f, radius, circlePaint)
        //Рисуем "арки", из них и будет состоять наше кольцо + у нас тут специальный метод
        canvas?.drawArc(oval, -90f, convertProgressToDegrees(progress), false, strokePaint)
        //Востанавливаем канвас
        canvas?.restore()
    }

    private fun convertProgressToDegrees(progress: Int): Float {
        return progress * 3.6f
    }

    private fun drawText(canvas: Canvas?){
        //Форматируем текст, чтобы мы выводили дробное число с одной цифрой после точки
        val message = String.format("%.1f", progress / 10f)
        //Полсучаем ширину и высоту текста, чтобы компенсировать их при отрисовке, чтобы текст был
        //точно в центре
        val widths = FloatArray(message.length)
        digitPaint.getTextWidths(message, widths)
        var advance = 0f
        for(width in widths) advance += width
        //Рисуем наш текст
        canvas?.drawText(message, centerX - advance / 2, centerY + advance / 4, digitPaint)

    }

    private fun initPaint() {
        //Краска для колец
        strokePaint = Paint().apply {
            style = Paint.Style.STROKE
            //Сюда кладем значение из поля класса, потому как у нас краски будут изменяться
            strokeWidth = stroke
            //Цвет мы тоже будем получать в специальном методе, потому что в зависимости от рейтинга
            //мы будем менять цвет нашего кольца
            color = getPaintColor(progress)
            isAntiAlias = true
        }
        //Краска для цифр
        digitPaint = Paint().apply {
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth = 2f
            setShadowLayer(5f, 0f, 0f, Color.DKGRAY)
            textSize = scaleSize
            typeface = Typeface.SANS_SERIF
            color = getPaintColor(progress)
            isAntiAlias = true
        }
        //Краска для заднего фона
        circlePaint = Paint().apply {
            style = Paint.Style.FILL
            color = Color.DKGRAY
        }
    }

    private fun getPaintColor(progress: Int): Int {
        return when (progress) {
            in 0..25 -> Color.parseColor("#e84258")
            in 26..50 -> Color.parseColor("#fd8060")
            in 51..75 -> Color.parseColor("#fee191")
            else -> Color.parseColor("#b0d8a4")
        }
    }

    fun setProgress(pr: Int){
        //Кладем новое значение в поле нашего проегресса
        progress = pr
        //Создаем краски с новыми цветами
        initPaint()
        //Вызываем перерисовку View
        invalidate()
    }
}
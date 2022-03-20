package com.example.findfilm.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.findfilm.view.rv_viewholders.FilmViewHolder
import com.example.findfilm.R
import com.example.findfilm.domain.Film

class FilmListRecyclerAdapter(private val clickListener: OnClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FilmViewHolder -> {
                //Вызывается метод ViewHolder который сам привязывает элементы из списка
                // так как список как раз состоит элементов Film
                holder.bind(items[position])

                //Обрабатываем нажатие на весь элемент целиком(можно сделать на отдельный элемент
                //например, картинку) и вызываем метод нашего листенера, который мы получаем из
                //конструктора адаптера
                holder.itemView.findViewById<CardView>(R.id.item_container).setOnClickListener{
                    clickListener.click(items[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(list: List<Film>){
        items.clear() //Нужно бы реализовать DiffUtils
        items.addAll(list)
        //Уведомляем RV
        notifyDataSetChanged()
    }

    interface OnClickListener{
        fun click(film: Film)
    }
}
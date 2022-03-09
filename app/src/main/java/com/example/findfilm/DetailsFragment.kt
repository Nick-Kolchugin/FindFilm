package com.example.findfilm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.findfilm.databinding.FragmentDetailsBinding
import com.example.findfilm.model.Film
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Благодря использованию ViewDataBinding ниже приведенные строчки больше не используются
        /*val buttonFavorite = view.findViewById<FloatingActionButton>(R.id.details_fab_favorites)
        val buttonShare = view.findViewById<FloatingActionButton>(R.id.details_fab_share)

        //находим все нам нужные комплектующие
        val title = view.findViewById<Toolbar>(R.id.details_toolbar)
        val poster = view.findViewById<ImageView>(R.id.details_poster)
        val description = view.findViewById<TextView>(R.id.details_description)*/

        //получаем наш фильм через переданный бандл
        val film = arguments?.get("film") as Film

        //устанавливаем заголовок
        binding.detailsToolbar.title = film.title

        //устанавливаем постер
        Glide.with(binding.detailsPoster)
            .load(film.poster)
            .centerCrop()
            .into(binding.detailsPoster)
//        poster.setImageResource(film.poster) <- старая реализация добаления картинки

        //устанавливаем описание
        binding.detailsDescription.text = film.description

        binding.detailsFabFavorites.setImageResource(
            if (film.isInFavorites){
                R.drawable.ic_round_favorite_24
            } else{
                R.drawable.ic_round_favorite_border_24
            }
        )

        binding.detailsFabFavorites.setOnClickListener {
            if(!film.isInFavorites){
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_round_favorite_24)
                film.isInFavorites = true
                (requireActivity() as MainActivity).favoritesDB.addToFavorite(film)
            } else{
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_round_favorite_border_24)
                film.isInFavorites = false
                (requireActivity() as MainActivity).favoritesDB.delFromFavorites(film)
            }
        }

        binding.detailsFabShare.setOnClickListener {
            //создаем интент
            val intent = Intent()
            //указываем action с которым он запускается
            intent.action = Intent.ACTION_SEND
            //кладем данные о нашем фильме
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${film.title}\n\n${film.description}"
            )
            //указываем MIME тип, чтобы система знала, какое приложение предложить
            intent.type = "text/plain"
            //запускаем наше активити
            startActivity(Intent.createChooser(intent, "Share to:"))
        }
    }



}
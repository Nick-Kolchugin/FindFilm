package com.example.findfilm.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findfilm.view.activities.MainActivity
import com.example.findfilm.databinding.FragmentWatchLaterBinding

class WatchLaterFragment : Fragment() {

    private lateinit var binding: FragmentWatchLaterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWatchLaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Благодря использованию ViewDataBinding нажняя строчка больше не используется
        //val watchLaterFragmentRoot = view.findViewById<ConstraintLayout>(R.id.watch_later_fragment_root)
        MainActivity.AnimationHelper.performFragmentCircularRevealAnimation(
            binding.watchLaterFragmentRoot,
            requireActivity(),
            4
        )
    }
}
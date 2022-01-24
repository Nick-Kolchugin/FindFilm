package com.example.findfilm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout


class SelectionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selections, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectionsFragmentRoot = view.findViewById<ConstraintLayout>(R.id.selections_fragment_root)
        MainActivity.AnimationHelper.performFragmentCircularRevealAnimation(selectionsFragmentRoot, requireActivity(), 3)
    }
}
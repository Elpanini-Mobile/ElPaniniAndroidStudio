package be.samysah.elpanini.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import be.samysah.elpanini.R
import be.samysah.elpanini.databinding.FragmentHomeBinding
import be.samysah.elpanini.databinding.FragmentLoginBinding

class HomeFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater)
        binding.buttonDetails.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_navigation_dashboard2)
        )
        return binding.root

    }
}
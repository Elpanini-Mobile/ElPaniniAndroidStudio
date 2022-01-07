package be.samysah.elpanini

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import be.samysah.elpanini.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStartBinding.inflate(inflater)

        Handler(Looper.getMainLooper()).postDelayed({
            //Start na 2 sec
            view?.findNavController()
                ?.navigate(be.samysah.elpanini.R.id.action_startFragment_to_loginFragment)
        }, 2000)

        return binding.root
    }
}
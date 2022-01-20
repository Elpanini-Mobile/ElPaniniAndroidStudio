package be.samysah.elpanini.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import be.samysah.elpanini.R
import be.samysah.elpanini.databinding.FragmentHomeBinding
import be.samysah.elpanini.restApi.Api_Broodjes
import be.samysah.elpanini.restApi.Broodjes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater)

        binding.buttonDetails.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_navigation_dashboard2)
        )
        //avoid going back
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            showAlertDialog()
        }
        callback.isEnabled

        Api_Broodjes.retrofitService.getProperties().enqueue(object : Callback<List<Broodjes>> {
            override fun onResponse(call: Call<List<Broodjes>>, response: Response<List<Broodjes>>) {
                val allCountry = response.body()
                for (t in allCountry!!)
                    binding.tit.text = t.title
                for (p in allCountry!!)
                    binding.prijs.text = p.price
            }

            override fun onFailure(call: Call<List<Broodjes>>, t: Throwable) {
                Log.i(HomeFragment::class.simpleName, "on FAILURE!!!!")
            }
        })
        return binding.root
    }

    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialog.setTitle("Log uit?")
        alertDialog.setMessage("Wens u uit te loggen")
        alertDialog.setPositiveButton(
                "ja"
        ) { _, _ ->
            view?.findNavController()?.navigate(R.id.action_navigation_home_to_loginFragment)
        }
        alertDialog.setNegativeButton(
                "nee"
        ) { _, _ -> }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}
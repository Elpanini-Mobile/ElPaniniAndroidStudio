package be.samysah.elpanini.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import be.samysah.elpanini.R
import be.samysah.elpanini.databinding.FragmentDashboardBinding
import be.samysah.elpanini.databinding.FragmentHomeBinding
import be.samysah.elpanini.restApi.Api_Broodjes
import be.samysah.elpanini.restApi.Broodjes
import be.samysah.elpanini.ui.home.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDashboardBinding.inflate(inflater)

        Api_Broodjes.retrofitService.getProperties().enqueue(object : Callback<List<Broodjes>> {
            override fun onResponse(call: Call<List<Broodjes>>, response: Response<List<Broodjes>>) {
                val allCountry = response.body()

                for (c in allCountry!!)
                    binding.tit.text = c.title
            }

            override fun onFailure(call: Call<List<Broodjes>>, t: Throwable) {
                Log.i(DashboardFragment::class.simpleName, "on FAILURE!!!!")
            }
        })
        return binding.root
    }
}
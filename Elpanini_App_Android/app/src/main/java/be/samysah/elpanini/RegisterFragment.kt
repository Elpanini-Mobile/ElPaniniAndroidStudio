package be.samysah.elpanini


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import be.samysah.elpanini.databinding.FragmentRegisterBinding
import be.samysah.elpanini.restApi.Api_Gebruiker
import be.samysah.elpanini.restApi.Gebruiker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(inflater)

        binding.buttonRegister.setOnClickListener {
            if(binding.editTextUsername.text.isNotEmpty() &&
                binding.editTextPassword.text.isNotEmpty() &&
                binding.editTextEmailAddress.text.isNotEmpty())
                {
                    //Het definiëren van het Gebruiker-object zoals we het moeten doorgeven met de oproep
                    val gebruiker = Gebruiker(
                        username = binding.editTextUsername.text.toString(),
                        email = binding.editTextEmailAddress.text.toString(),
                        password = binding.editTextPassword.text.toString())

                    //API oproepen
                    Api_Gebruiker.retrofitService.addGebruiker(gebruiker).enqueue(object : Callback<Gebruiker> {
                        override fun onResponse(call: Call<Gebruiker>, response: Response<Gebruiker>) {
                            Toast.makeText(context,"de registratie is voltooid",Toast.LENGTH_SHORT).show()

                            //navigate to home (menu broodjes)
                            view?.findNavController()?.navigate(R.id.action_registerFragment_to_navigation_home)
                        }
                        override fun onFailure(call: Call<Gebruiker>, t: Throwable) {
                            Toast.makeText(context,"gelieve alle informatie in te vullen",Toast.LENGTH_SHORT).show()
                        }
                    })
            }
            else {
                Toast.makeText(this.context, "gelieve alle informatie in te vullen", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}
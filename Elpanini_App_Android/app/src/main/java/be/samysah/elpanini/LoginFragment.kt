package be.samysah.elpanini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import be.samysah.elpanini.databinding.FragmentLoginBinding
import be.samysah.elpanini.restApi.Api_Gebruiker
import be.samysah.elpanini.restApi.Gebruiker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater)

        //vermijd terug te gaan
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
        }
        callback.isEnabled

        binding.buttonLogin.setOnClickListener {
            if(binding.editTextEmailAddress.text.isNotEmpty() &&
                binding.editTextPassword.text.isNotEmpty()) {
                Api_Gebruiker.retrofitService.checklogin(
                    binding.editTextEmailAddress.text.toString(), binding.editTextPassword.text.toString()
                ).enqueue(
                    object : Callback<Gebruiker> {
                        override fun onResponse(
                            call: Call<Gebruiker>,
                            response: Response<Gebruiker>
                        ) {
                            // API retourneert altijd iets, zelfs als de gebruiker niet gevonden is
                            // daarom moeten we controleren of het geretourneerde bericht juist is
                             if(response.body().toString().contains("id")){
                                 Toast.makeText(context, "je bent ingelogd", Toast.LENGTH_LONG).show()
                                 val bundle = Bundle()
                                 view?.findNavController()
                                     ?.navigate(R.id.action_loginFragment_to_navigation_home,bundle)
                             }
                             else {
                                 Toast.makeText(
                                     context,
                                     "Gebruiker niet gevonden, probeer het opnieuw of maak een account aan\"",
                                     Toast.LENGTH_LONG
                                 ).show()
                             }
                        }

                        override fun onFailure(call: Call<Gebruiker>, thr: Throwable) {
                            Toast.makeText(
                                context,
                                "Gebruiker niet gevonden, probeer het opnieuw of maak een account aan\"",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
            }
            else {
                Toast.makeText(context,"Vul alle velden in, of schrijf je in!", Toast.LENGTH_LONG).show()
            }
        }
        binding.buttonGoRegister.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment)
        )
        return binding.root
    }
}


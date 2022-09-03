package com.osmanuslu.myapplication.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.osmanuslu.myapplication.R
import com.osmanuslu.myapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth= Firebase.auth

        val currentUser = auth.currentUser

        if (currentUser != null) {
            val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
            view.let {
                Navigation.findNavController(it).navigate(action)
            }
        }

        //SignIn
        binding?.signinButton?.setOnClickListener {
            val email= binding?.mailText?.text.toString()
            val password= binding?.passwordText?.text.toString()

            if(email.equals("")||password.equals("")){
                Toast.makeText(requireContext(),"lütfen mail ve şifre giriniz", Toast.LENGTH_LONG).show()

            } else{
                auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                    val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                    Navigation.findNavController(view).navigate(action)
                }.addOnFailureListener {
                    Toast.makeText(requireContext(),it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

        //SignUp
        binding?.signupButton?.setOnClickListener{
            val email= binding?.mailText?.text.toString()
            val password= binding?.passwordText?.text.toString()

            if(email == "" || password == ""){
                Toast.makeText(requireContext(),"lütfen mail ve şifre giriniz", Toast.LENGTH_LONG).show()

            }else{
                auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                    val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                    Navigation.findNavController(view).navigate(action)
                }.addOnFailureListener{
                    Toast.makeText(requireContext(),it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }


    }

}
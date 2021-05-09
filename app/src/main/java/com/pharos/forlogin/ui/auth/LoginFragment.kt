package com.pharos.forlogin.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.pharos.forlogin.databinding.FragmentLoginBinding
import com.pharos.forlogin.data.network.AuthApi
import com.pharos.forlogin.data.network.Resource
import com.pharos.forlogin.data.repository.AuthRepository
import com.pharos.forlogin.ui.base.BaseFragment
import com.pharos.forlogin.ui.enable
import com.pharos.forlogin.ui.handleApiError
import com.pharos.forlogin.ui.home.HomeActivity
import com.pharos.forlogin.ui.startNewActivity
import com.pharos.forlogin.ui.visible
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)

            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.access/*, it.value.access*/)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })

        binding.editTextTextPassword.addTextChangedListener {
            val username = binding.editTextTextUsernameAddress.text.toString().trim()
            binding.buttonLogin.enable(username.isNotEmpty() && it.toString().isNotEmpty())

        }

        binding.buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val username = binding.editTextTextUsernameAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        viewModel.login(username, password)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(
        remoteDataSource.buildApi(AuthApi::class.java), userPreferences
    )
}
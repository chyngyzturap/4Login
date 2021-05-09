package com.pharos.forlogin.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pharos.forlogin.adapter.HomeAdapter
import com.pharos.forlogin.data.network.Resource
import com.pharos.forlogin.data.network.RoomsApi
import com.pharos.forlogin.data.repository.RoomsRepository
import com.pharos.forlogin.databinding.FragmentHomeBinding
import com.pharos.forlogin.ui.base.BaseFragment
import com.pharos.forlogin.ui.visible
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, RoomsRepository>() {

    lateinit var rooms: RoomsApi
    var homeAdapter: HomeAdapter = HomeAdapter()
    var refreshTimes = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)
        updateUI()
        viewModel.getRooms()

        viewModel.rooms.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    Log.e("ololo", "onViewCreated: ${it.value}")
                    homeAdapter.addData(it.value.toMutableList())

                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
            }
        })

        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun updateUI() {
        with(binding) {
            setupRecyclerView()
            rvRooms.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )

        }
    }

    private fun setupRecyclerView() = rv_Rooms.apply {
        rv_Rooms.adapter = homeAdapter
        layoutManager = LinearLayoutManager(requireContext())

    }


    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): RoomsRepository {
        val token = runBlocking { userPreferences.tokenAccess.first() }
        val api = remoteDataSource.buildApi(RoomsApi::class.java, token)
        return RoomsRepository(api)
    }

}
package com.v15h4l.wardrobe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.v15h4l.wardrobe.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * Home Fragment to show Home screen
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val viewModel: HomeViewModel by viewModels()
    private val shirtsAdapter by lazy { ClothAdapter() }
    private val pantsAdapter by lazy { ClothAdapter() }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        viewModel.shirts.observe(viewLifecycleOwner, { shirts -> shirtsAdapter.submitList(shirts) })
        viewModel.pants.observe(viewLifecycleOwner, { pants -> pantsAdapter.submitList(pants) })

    }

    private fun initView() {

        // Init ViewPagers
        binding.viewPagerShirts.adapter = shirtsAdapter
        binding.viewPagerPants.adapter = pantsAdapter

        // Init Click Listeners
        binding.btnAddShirts.setOnClickListener { /* TODO: 21/12/20 Open image picker */ }
        binding.btnShuffle.setOnClickListener { /* TODO: 21/12/20 Shuffle selection */ }
        binding.btnFavorite.setOnClickListener { /* TODO: 21/12/20 Add to Favorites */ }
        binding.btnAddPants.setOnClickListener {/* TODO: 21/12/20 Open image picker */ }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
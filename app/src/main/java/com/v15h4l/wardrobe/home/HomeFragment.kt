package com.v15h4l.wardrobe.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.v15h4l.wardrobe.databinding.FragmentHomeBinding
import com.v15h4l.wardrobe.model.Cloth
import com.v15h4l.wardrobe.utils.showToast


/**
 * Home Fragment
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

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
    }

    private fun initView(){

        // Init ViewPagers
        binding.viewPagerShirts.adapter = ClothAdapter().apply { submitList(getDummyCloths()) }
        binding.viewPagerPants.adapter = ClothAdapter().apply { submitList(getDummyCloths()) }

        // Init Click Listeners
        binding.btnAddShirts.setOnClickListener { "Todo Open Camera or Gallery".showToast(requireContext()) }
        binding.btnShuffle.setOnClickListener { "Todo Shuffle Selection".showToast(requireContext()) }
        binding.btnFavorite.setOnClickListener { "Todo Add to Favorites".showToast(requireContext()) }
        binding.btnAddPants.setOnClickListener { "Todo Open Camera or Gallery".showToast(requireContext()) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getDummyCloths() = listOf(
        Cloth(1, "One"),
        Cloth(2, "Two"),
        Cloth(3, "Three"),
        Cloth(4, "Four"),
        Cloth(5, "Five"),
    )

}
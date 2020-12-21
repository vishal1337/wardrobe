package com.v15h4l.wardrobe.ui.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.drjacky.imagepicker.ImagePicker
import com.v15h4l.wardrobe.databinding.FragmentHomeBinding
import com.v15h4l.wardrobe.model.Cloth
import com.v15h4l.wardrobe.model.ClothType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Home Fragment to show Home screen
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val viewModel: HomeViewModel by viewModels()

    private val shirtsAdapter by lazy { ClothAdapter() }
    private val pantsAdapter by lazy { ClothAdapter() }

    // Used as a temp variable to know which type of cloth is being added
    private var newClothType: ClothType? = null

    // This property is only valid between onCreateView and onDestroyView.
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
        binding.btnAddShirts.setOnClickListener {
            newClothType = ClothType.SHIRT
            showImagePicker()
        }
        binding.btnAddPants.setOnClickListener {
            newClothType = ClothType.PANT
            showImagePicker()
        }

        binding.btnShuffle.setOnClickListener { /* TODO: 21/12/20 Shuffle selection */ }
        binding.btnFavorite.setOnClickListener { /* TODO: 21/12/20 Add to Favorites */ }
    }

    /**
     * Scroll to the last item of the list whenever a new cloth is added.
     */
    private fun scrollToLastClothingItem(clothType: ClothType) {
        lifecycleScope.launch {
            delay(100) // Add this delay to allow ViewPager2 to load item before scrolling to it
            when (clothType) {
                ClothType.SHIRT -> {
                    binding.viewPagerShirts.currentItem = shirtsAdapter.itemCount + 1
                }
                ClothType.PANT -> {
                    binding.viewPagerPants.currentItem = pantsAdapter.itemCount + 1
                }
            }
        }
    }

    /**
     * Present User with a screen to select from Camera or Gallery
     * Also ask for appropriate Permissions, if not granted already.
     */
    private fun showImagePicker() {
        ImagePicker.with(this).start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            data?.data?.let { fileUri ->
                viewModel.addClothToCatalogue(
                    Cloth(
                        imagePath = fileUri.toString(),
                        type = newClothType
                    )
                ).also { newClothType?.let { scrollToLastClothingItem(it) } }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
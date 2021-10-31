package com.example.hw5startercode

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [WallpaperFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WallpaperFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var number: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            number = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_wallpaper, container, false)
        val imageView : ImageView = view.findViewById(R.id.imageView)
        getPicture(number!!-1, requireActivity()) {url ->
            Glide.with(requireContext())
                .load(url)
                .centerCrop()
                .into(imageView)
        }

        val button: Button = view.findViewById(R.id.button)
        button.setOnClickListener() {
            val wallpaperManager: WallpaperManager =
                WallpaperManager.getInstance(activity?.applicationContext)
            try {
                imageView.invalidate()
                val drawable : BitmapDrawable = imageView.getDrawable() as BitmapDrawable
                val bitmap : Bitmap = drawable.getBitmap()
                wallpaperManager.setBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment WallpaperFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(number: Int) =
            WallpaperFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, number)
                }
            }
    }
}
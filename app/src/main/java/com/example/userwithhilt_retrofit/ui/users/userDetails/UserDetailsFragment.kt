package com.example.userwithhilt_retrofit.ui.users.userDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.userwithhilt_retrofit.R
import com.example.userwithhilt_retrofit.databinding.FragmentUserDetailsBinding
import com.example.userwithhilt_retrofit.domain.model.UserItem
import com.example.userwithhilt_retrofit.ui.users.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    @Inject
    lateinit var requestManager: RequestManager

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false);
        binding.apply {
            lifecycleOwner = this@UserDetailsFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSelectedNoteFromPreviousFragment()
    }

    private fun getSelectedNoteFromPreviousFragment() {
        val user = arguments?.let { args ->
            (args.getParcelable("user") as UserItem?)
        }

        binding.apply {
            requestManager
                .load(user?.avatarUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
            textViewName.text = user?.name
            textViewUrl.text = user?.url

        }
    }

}

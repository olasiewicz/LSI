package com.example.userwithhilt_retrofit.ui.users.noteList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.example.userwithhilt_retrofit.R
import com.example.userwithhilt_retrofit.databinding.FragmentUserListBinding
import com.example.userwithhilt_retrofit.domain.model.UserItem
import com.example.userwithhilt_retrofit.ui.UIController
import com.example.userwithhilt_retrofit.ui.users.UsersStateEvent
import com.example.userwithhilt_retrofit.ui.users.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment() {

    @Inject
    lateinit var requestManager: RequestManager

    lateinit var recyclerAdapter: UserListAdapter
    lateinit var uiController: UIController

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false);
        binding.apply {
            lifecycleOwner = this@UserListFragment
        }
        viewModel.onTriggerEvent(UsersStateEvent.GetUsersEvent)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        subscribeObservers()
    }

    private fun initRecyclerView() {

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@UserListFragment.context)

            recyclerAdapter = UserListAdapter(requestManager, ::listItemClicked)

            adapter = recyclerAdapter
        }
    }

    private fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->

            state.listOfUsers?.let { usersList ->
                recyclerAdapter.submitList(usersList)
            }

        }
        viewModel.shouldDisplayProgressBar.observe(viewLifecycleOwner) {
            displayProgressBar(it)
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        uiController.displayProgressBar(isDisplayed)
    }

    private fun listItemClicked(user: UserItem) {
       navigateToDetailsFragment(user)


    }

    private fun navigateToDetailsFragment(user: UserItem) {
        val bundle = bundleOf("user" to user)
        findNavController().navigate(
            R.id.action_noteListFragment_to_noteDetailsFragment,
            bundle
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            uiController = context as UIController
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

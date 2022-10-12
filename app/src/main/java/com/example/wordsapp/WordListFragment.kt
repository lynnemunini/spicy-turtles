package com.example.wordsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

private var _binding: FragmentLetterListBinding? = null
private val binding get() = _binding!!
private lateinit var recyclerView: RecyclerView
private lateinit var wordAdapter: WordAdapter
private var isLinearLayoutManager = true

class WordListFragment : Fragment() {
    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    // Perform any remaining setup in onViewCreated(): get a reference to the recycler view,
    // set its layout manager and adapter, and add its item decoration. You'll need to get the
    // letter from the intent. As fragments don't have an intent property and shouldn't
    // normally access the intent of the parent activity. For now, you
    // refer to activity.intent (rather than intent in DetailActivity) to get the extras.

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        val letter = activity?.intent?.getStringExtra(LETTER)
        wordAdapter = WordAdapter(letter!!, requireContext())
        recyclerView.adapter = wordAdapter
        chooseLayout()
    }

    private fun chooseLayout() {
        when (isLinearLayoutManager) {
            true -> {
                recyclerView.layoutManager = LinearLayoutManager(context)

            }
            false -> {
                recyclerView.layoutManager = GridLayoutManager(context, 4)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
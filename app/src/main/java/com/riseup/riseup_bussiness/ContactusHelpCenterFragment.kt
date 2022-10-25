package com.riseup.riseup_users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.riseup.riseup_bussiness.databinding.FragmentContactusHelpCenterBinding

class ContactusHelpCenterFragment : Fragment(){



    private var _binding : FragmentContactusHelpCenterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactusHelpCenterBinding.inflate(inflater, container, false)
        val view = binding.root





        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    companion object {
        @JvmStatic
        fun newInstance() = ContactusHelpCenterFragment()
    }
}
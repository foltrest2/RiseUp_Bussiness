package com.riseup.riseup_users


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.databinding.FragmentFaqHelpCenterBinding
import com.riseup.riseup_bussiness.util.FAQInfoBlockAdapter
import com.riseup.riseup_bussiness.viewmodel.FaqHelpCenterViewModel


class FaqHelpCenterFragment : Fragment(){

    private var _binding: FragmentFaqHelpCenterBinding? = null
    private val binding get() = _binding!!

    private val viewModel : FaqHelpCenterViewModel by activityViewModels()

    private val adapter = FAQInfoBlockAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFaqHelpCenterBinding.inflate(inflater, container, false)
        val view = binding.root

        //Recrear el estado
        var cardsRecycler = binding.recyclerViewFAQBlocks
        cardsRecycler.setHasFixedSize(true)
        cardsRecycler.layoutManager = LinearLayoutManager(activity)
        cardsRecycler.adapter = adapter

        var btnGeneral = binding.horizontalFaqSCV.findViewById<Button>(R.id.generalFAQBtn)
        var btnCuenta = binding.horizontalFaqSCV.findViewById<Button>(R.id.cuentaFAQBtn)
        var btnServicio = binding.horizontalFaqSCV.findViewById<Button>(R.id.servicioFAQBtn)
        var btnPagos = binding.horizontalFaqSCV.findViewById<Button>(R.id.pagosFAQBtn)

        viewModel.faqs.observe(viewLifecycleOwner){
            if(it.isNotEmpty()) {
                adapter.reset()
                for (faqs in it) {
                    adapter.addFaq(faqs)
                }
            }
        }

        binding.horizontalFaqSCV.findViewById<Button>(R.id.generalFAQBtn).setOnClickListener {
            resetActualBtn()
            btnGeneral.setBackgroundResource(R.drawable.faq_btn_selected)
        }

        binding.horizontalFaqSCV.findViewById<Button>(R.id.cuentaFAQBtn).setOnClickListener {
            resetActualBtn()
            btnCuenta.setBackgroundResource(R.drawable.faq_btn_selected)
        }

        binding.horizontalFaqSCV.findViewById<Button>(R.id.servicioFAQBtn).setOnClickListener {
            resetActualBtn()
            btnServicio.setBackgroundResource(R.drawable.faq_btn_selected)
        }

        binding.horizontalFaqSCV.findViewById<Button>(R.id.pagosFAQBtn).setOnClickListener {
            resetActualBtn()
            btnPagos.setBackgroundResource(R.drawable.faq_btn_selected)
        }

        return view
    }

    fun resetActualBtn(){
        binding.horizontalFaqSCV.findViewById<Button>(R.id.generalFAQBtn).setBackgroundResource(R.drawable.faq_btn_noselection)
        binding.horizontalFaqSCV.findViewById<Button>(R.id.cuentaFAQBtn).setBackgroundResource(R.drawable.faq_btn_noselection)
        binding.horizontalFaqSCV.findViewById<Button>(R.id.servicioFAQBtn).setBackgroundResource(R.drawable.faq_btn_noselection)
        binding.horizontalFaqSCV.findViewById<Button>(R.id.pagosFAQBtn).setBackgroundResource(R.drawable.faq_btn_noselection)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    companion object {
        @JvmStatic
        fun newInstance() = FaqHelpCenterFragment()

    }


}
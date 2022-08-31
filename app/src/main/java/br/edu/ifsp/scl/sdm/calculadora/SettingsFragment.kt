package br.edu.ifsp.scl.sdm.calculadora

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import br.edu.ifsp.scl.sdm.calculadora.databinding.FragmentSettingsBinding
import br.edu.ifsp.scl.sdm.calculadora.databinding.MainFragmentBinding

class SettingsFragment : Fragment() {
    private lateinit var fragmentSettingsBinding: FragmentSettingsBinding
    //private lateinit var fragmentMainBinding: MainFragmentBinding


    private var calculadoras = 0



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)


        fragmentSettingsBinding.calcBasicRb.setOnClickListener{
            calculadoras = 1

        }
        fragmentSettingsBinding.calcAdvancedRb.setOnClickListener{
            calculadoras = 2

        }


        fragmentSettingsBinding.salvarBt.setOnClickListener {
            setFragmentResult("requestKey", bundleOf("CALCULADORAS" to calculadoras))
            activity?.supportFragmentManager?.popBackStack()
        }
        fragmentSettingsBinding.fecharFragmentoBt.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        return fragmentSettingsBinding.root


    }


}
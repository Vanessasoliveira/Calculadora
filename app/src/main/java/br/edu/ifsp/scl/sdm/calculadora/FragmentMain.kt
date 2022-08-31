package br.edu.ifsp.scl.sdm.calculadora

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible

import androidx.fragment.app.setFragmentResultListener
import br.edu.ifsp.scl.sdm.calculadora.databinding.MainFragmentBinding
import net.objecthunter.exp4j.ExpressionBuilder


class FragmentMain : Fragment(), View.OnClickListener {
    private lateinit var fragmentMainBinding: MainFragmentBinding
    var calculadoras = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = MainFragmentBinding.inflate(inflater, container, false)
        Log.d("Create View", "Create the main fragment")


        if (calculadoras == 1){
            fragmentMainBinding.percentagemBt.isGone
            fragmentMainBinding.potencialBt.isGone
            fragmentMainBinding.squareRootBt.isGone
        } else{
            fragmentMainBinding.percentagemBt.isVisible
            fragmentMainBinding.potencialBt.isVisible
            fragmentMainBinding.squareRootBt.isVisible
        }

        fragmentMainBinding.sevenBt.setOnClickListener { addAnExpression("7", clearData = true) }
        fragmentMainBinding.eightBt.setOnClickListener { addAnExpression("8", clearData = true) }
        fragmentMainBinding.nineBt.setOnClickListener { addAnExpression("9", clearData = true) }
        fragmentMainBinding.sixBt.setOnClickListener { addAnExpression("6", clearData = true) }
        fragmentMainBinding.fiveBt.setOnClickListener { addAnExpression("5", clearData = true) }
        fragmentMainBinding.fourBt.setOnClickListener { addAnExpression("4", clearData = true) }
        fragmentMainBinding.threeBt.setOnClickListener { addAnExpression("3", clearData = true) }
        fragmentMainBinding.twoBt.setOnClickListener { addAnExpression("2", clearData = true) }
        fragmentMainBinding.oneBt.setOnClickListener { addAnExpression("1", clearData = true) }
        fragmentMainBinding.zeroBt.setOnClickListener { addAnExpression("0", clearData = true) }
        fragmentMainBinding.resultadoTv.findViewById<TextView>(R.id.resultadoTv)

        //operators
        fragmentMainBinding.plusBt.setOnClickListener{ addAnExpression("+", clearData = false) }
        fragmentMainBinding.multiplyBt.setOnClickListener{ addAnExpression("*", clearData = false) }
        fragmentMainBinding.divideBt.setOnClickListener{ addAnExpression("/", clearData = false) }
        fragmentMainBinding.subtractionBt.setOnClickListener{ addAnExpression("-", clearData = false) }
        fragmentMainBinding.percentagemBt.setOnClickListener{ addAnExpression("%", clearData = false) }
        fragmentMainBinding.squareRootBt.setOnClickListener{ addAnExpression("√", clearData = false) }
        fragmentMainBinding.potencialBt.setOnClickListener{ addAnExpression("^", clearData = false) }


        //Clear everything
        fragmentMainBinding.clearBt.setOnClickListener {
            fragmentMainBinding.expressionTv.text = ""
            fragmentMainBinding.resultadoTv.text = ""

        }

        //Clear each one
        fragmentMainBinding.backspaceBt.setOnClickListener {
            val string = fragmentMainBinding.expressionTv.text.toString()

            if (string.isNotBlank()){
                fragmentMainBinding.expressionTv.text = string.substring(0,string.length-1)
            }
            fragmentMainBinding.resultadoTv.text=  ""
        }

        fragmentMainBinding.dotBt.setOnClickListener{ addAnExpression(".", clearData = true) }

        //Calculating
        fragmentMainBinding.equalBt.setOnClickListener{

            try{
                val expression = ExpressionBuilder(fragmentMainBinding.expressionTv.text.toString()).build()

                val result = expression.evaluate()
                val longResult = result.toLong()

                if ( result == longResult.toDouble())
                    fragmentMainBinding.resultadoTv.text = longResult.toString()
                    else
                        fragmentMainBinding.resultadoTv.text = result.toString()

            }catch (e: Exception){


            }

        }




        setFragmentResultListener("requestKey") { requestKey, bundle ->
            when (bundle.getInt("CALCULADORAS")) {
                1 -> {
                    calculadoras = 1
                    Log.d("calculadoras", "$calculadoras")
                }
                2 -> {
                    calculadoras = 2

                    Log.d("calculadoras", "$calculadoras")

                }
                else -> {}
            }
        }
        return fragmentMainBinding.root
    }

    fun addAnExpression(string: String, clearData: Boolean) {
        if (fragmentMainBinding.resultadoTv.text.isNotEmpty()) {
            fragmentMainBinding.expressionTv.text = ""
        }

        if (clearData) {
            fragmentMainBinding.resultadoTv.text = ""
            fragmentMainBinding.expressionTv.append(string)
        } else {
            fragmentMainBinding.expressionTv.append(fragmentMainBinding.resultadoTv.text)
            fragmentMainBinding.expressionTv.append(string)
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("CALCULADORAS", calculadoras)

    }

}
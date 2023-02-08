package com.example.calc_ui_2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.RoundingMode
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        tvResult.text = ""
//        tvExpression.text = ""

//        numbers
//     val ButtonZero=findViewById<Button>(R.id.btnZero)

        var result =""
        btnZero.setOnClickListener {

            addNumberToExpression("0")
        }
        btnOne.setOnClickListener {

            addNumberToExpression("1")
        }
        btnTwo.setOnClickListener {

            addNumberToExpression("2")
        }
        btnThree.setOnClickListener {

            addNumberToExpression("3")
        }
        btnFour.setOnClickListener {

            addNumberToExpression("4")
        }
        btnFive.setOnClickListener {

            addNumberToExpression("5")
        }
        btnSix.setOnClickListener {

            addNumberToExpression("6")
        }
        btnSeven.setOnClickListener {

            addNumberToExpression("7")
        }
        btnEight.setOnClickListener {

            addNumberToExpression("8")
        }
        btnNine.setOnClickListener {

            addNumberToExpression("9")
        }

//        Dot
        var dotPressed = false
//        creating this variable to ignore redundancy of dot
        btnDot.setOnClickListener {
            if (!dotPressed) {
                if (!endsWithOperator(tvResult.text.toString())) {
                    addNumberToExpression(".")
                    dotPressed = true
                }
            }
        }

//       Mathematical Operations performed

        btnPlus.setOnClickListener {
            if (!endsWithOperator(tvResult.text.toString())) {
                addNumberToExpression("+")
                btnAnimate(btnPlus)
            }
        }
        btnMinus.setOnClickListener {
            if (!endsWithOperator(tvResult.text.toString())) {
                addNumberToExpression("-")
                btnAnimate(btnMinus)
            }
        }
        btnMultiply.setOnClickListener {
            if (!endsWithOperator(tvResult.text.toString())) {
                addNumberToExpression("*")
                btnAnimate(btnMultiply)
            }
        }
        btnDivide.setOnClickListener {
            if (!endsWithOperator(tvResult.text.toString())) {
                addNumberToExpression("/")
                btnAnimate(btnDivide)
            }
        }
/*        btnModulo.setOnClickListener {
            if (!endsWithOperator(tvExpression.text.toString()))
                addNumberToExpression("%")
        }
*/

        //      operations performed

        btnClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
            result=""
            btnAnimate(btnClear)
        }


        btnDelete.setOnClickListener {
            if(!result.isEmpty())
            {
                result=tvResult.text.toString()
                tvExpression.text=""
                tvResult.text = result.dropLast(1)
            }
            else {
                tvResult.text = ""
                tvExpression.text = tvExpression.text.dropLast(1)
                tvResult.text = tvExpression.text
            }
            btnAnimate(btnDelete)
        }
/*        btnPlusMinus.setOnClickListener {

            if (tvExpression.text.toString().startsWith("-"))
                tvExpression.text = "+" + tvExpression.text
            else
                tvExpression.text = "-" + tvExpression.text
        }*/


        btnBracket1.setOnClickListener {
            addNumberToExpression("(")
        }
        btnBracket2.setOnClickListener {
            addNumberToExpression(")")
        }


/*
* check here on
*
*
* */
        btnEquals.setOnClickListener {
            btnAnimate(btnEquals)

            try {

                val exp = tvResult.text.toString()
                tvExpression.text = exp

                if (!dotPressed) {
//                    checking if only contains digits not decimal
                    tvResult.text = ExpressionBuilder(exp)
                        .build()
                        .evaluate().toInt()
                        .toString()

                } else {

                    tvResult.text=String.format("%.8f",ExpressionBuilder(exp).build().evaluate())

                    dotPressed = false
                    /*val value = ExpressionBuilder(exp).build()
                        .evaluate()
//                    evaluate returns in double
                    tvResult.text = String.format("%.8f", value)*/
/*
//                    to round off
                    val df = DecimalFormat("#.######")
                    df.roundingMode = RoundingMode.DOWN
                    val roundoff = df.format(value)
                    tvResult.text = roundoff.toString()
*/
                }
//                 result = tvResult.text.toString()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Invalid Input", Toast.LENGTH_SHORT).show()
            }

        }



    }


    @SuppressLint("SuspiciousIndentation")
    private fun endsWithOperator(tv1: String): Boolean {
        if ((tv1.endsWith("+") || tv1.endsWith("-")) || tv1.endsWith("*") ||
            tv1.endsWith("/") || tv1.endsWith("%") || tv1.endsWith("."))
            return true

        return false
    }

    @SuppressLint("SetTextI18n")
    fun addNumberToExpression(number: String) {
        tvResult.text = tvResult.text.toString() + number
    }

    private fun btnAnimate(y: Button) {
        //Set button alpha to zero
        y.alpha = 0f

        //Animate the alpha value to 1f and set duration as 1.5 secs.
        y.animate().alpha(1f).duration = 400

    }


}
package poc.dynamictemplatepoc.views.cells

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import poc.dynamictemplatepoc.MyApplication
import poc.dynamictemplatepoc.model.Cells
import poc.dynamictemplatepoc.service.ServiceManager
import poc.dynamictemplatepoc.views.base.BaseActivity
import poc.dynamictemplatepoc.views.funds.FundsActivity
import javax.inject.Inject


class CellsActivity : BaseActivity(), CellsContract.View {


    @Inject
    lateinit var serviceManager: ServiceManager
    private var presenter: CellsContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCellsService()
    }

    private fun initCellsService() {
        presenter = CellsPresenter(serviceManager, this)
        presenter!!.loadCells()

    }

    override fun setupComponent() {
        (application as MyApplication).getAppComponent(this).injectActivity(this)
    }

    override fun populateCells(response: Cells) {
        val parentLayout = LinearLayout(this)
        parentLayout.orientation = LinearLayout.VERTICAL

        val edittext = EditText(this)
        val teleEdittext = EditText(this)
        val emailEdittext = EditText(this)
        val cbView = CheckBox(this)
        if (response.cells != null && response.cells.isNotEmpty()) {

            for (items in response.cells) {

                when (items!!.type) {
                    1 -> {
                        //Edittext
                        val editTextParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

                        if (items.typefield == 1 || items.typefield == "text" || items.typefield == 1.0) {
                            //text
                            edittext.layoutParams = editTextParams
                            edittext.hint = items.message
                            editTextParams.setMargins(0, items.topSpacing!!.toInt(), 0, 0)
                            parentLayout.addView(edittext)

                        } else if (items.typefield == 2 || items.typefield == "telnumber" || items.typefield == 2.0) {
                            teleEdittext.inputType = InputType.TYPE_CLASS_PHONE
                            teleEdittext.hint = items.message
                            val filterArray = arrayOfNulls<InputFilter>(1)
                            filterArray[0] = InputFilter.LengthFilter(10)
                            teleEdittext.filters = filterArray
                            editTextParams.setMargins(0, items.topSpacing!!.toInt(), 0, 0)
                            //PhoneNumberUtils.formatNumber(teleEdittext.text.toString());
                            parentLayout.addView(teleEdittext)
                            teleEdittext.addTextChangedListener(object : TextWatcher {
                                override fun afterTextChanged(p0: Editable?) {

                                }

                                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                }

                                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                    PhoneNumberUtils.BCD_EXTENDED_TYPE_CALLED_PARTY
                                    val formattedNumber = PhoneNumberUtils.formatNumber(teleEdittext.text.toString(), "BRA")
                                    Log.i("text watcher", "phone number $formattedNumber, raw text ${teleEdittext.text.toString()}")
                                    if (formattedNumber != null && !formattedNumber.equals(teleEdittext.text.toString())) {
                                        teleEdittext.setText(formattedNumber)
                                    }
                                }
                            })
                            //telNumber

                        } else if (items.typefield == 3 || items.typefield == "email" || items.typefield == 3.0) {
                            //email
                            emailEdittext.hint = items.message
                            emailEdittext.inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
                            editTextParams.setMargins(0, items.topSpacing!!.toInt(), 0, 0)
                            parentLayout.addView(emailEdittext)
                            emailEdittext.visibility = View.GONE
                        }


                    }
                    2 -> {
                        //textview
                        val textView = TextView(this)
                        val textViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        textView.layoutParams = textViewParams
                        textView.text = items.message
                        textViewParams.setMargins(0, items.topSpacing!!.toInt(), 0, 0)
                        parentLayout.addView(textView)
                    }
                    3 -> {
                        //image
                        val imgView = ImageView(this)
                        val imgViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        imgView.layoutParams = imgViewParams
                        imgView.setImageResource(android.R.drawable.ic_menu_add)
                        imgViewParams.setMargins(0, items.topSpacing!!.toInt(), 0, 0)
                        parentLayout.addView(imgView)

                    }
                    4 -> {
                        //checkbox

                        val cbViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        cbView.layoutParams = cbViewParams
                        cbView.isSelected = false
                        cbView.text = items.message
                        cbViewParams.setMargins(0, items.topSpacing!!.toInt(), 0, 0)
                        parentLayout.addView(cbView)
                        cbView.setOnCheckedChangeListener { _, _ ->
                            if (cbView.isChecked) {
                                emailEdittext.visibility = View.VISIBLE
                            } else {
                                emailEdittext.visibility = View.GONE
                            }
                        }

                    }
                    5 -> {
                        //Button
                        val buttonView = Button(this)
                        val buttonViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        buttonView.layoutParams = buttonViewParams
                        buttonView.text = items.message
                        buttonViewParams.setMargins(0, items.topSpacing!!.toInt(), 0, 0)
                        buttonView.setOnClickListener {
                            val intent = Intent(this, FundsActivity::class.java)
                            //verify fields are proper
                            fun requiredString(value: String): Boolean {
                                if (value == null || value.isEmpty()) return false
                                return true
                            }

                            fun isValidEmail(value: String): Boolean {
                                return value != null && value.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()
                            }

                            fun isValidMobileNumber(value: String): Boolean {
                                return value != null && value.isNotEmpty() && value.length == 10
                            }

                            val emailText = emailEdittext.text.toString()
                            val phone = teleEdittext.text.toString()
                            val name = edittext.text.toString()
                            if (!requiredString(name)) {
                                Toast.makeText(this, "name is required", Toast.LENGTH_SHORT).show()
                                return@setOnClickListener
                            }
                            if (cbView.isChecked && !isValidEmail(emailText)) {
                                Toast.makeText(this, "please provide valid email", Toast.LENGTH_SHORT).show()
                                return@setOnClickListener
                            }
                            if (!isValidMobileNumber(phone)) {
                                Toast.makeText(this, "please provide valid phone number", Toast.LENGTH_SHORT).show()
                                return@setOnClickListener
                            }
                            startActivity(intent)
                            /*if (isValid()){
                                val intent = Intent(this, FundsActivity::class.java)
                                startActivity(intent)
                            }
                            else{

                            }*/

                        }
                        parentLayout.addView(buttonView)
                    }
                }
            }
            setViews(parentLayout)
        }

    }

    private fun setViews(parentLayout: LinearLayout) {
        val parentLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        parentLayout.layoutParams = parentLayoutParams
        parentLayoutParams.setMargins(10, 10, 10, 10)
        setContentView(parentLayout, parentLayoutParams)
    }

}

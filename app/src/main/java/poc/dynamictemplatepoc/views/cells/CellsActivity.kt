package poc.dynamictemplatepoc.views.cells

import android.os.Bundle
import android.text.InputType
import android.widget.*
import poc.dynamictemplatepoc.MyApplication
import poc.dynamictemplatepoc.model.Cells
import poc.dynamictemplatepoc.service.ServiceManager
import poc.dynamictemplatepoc.views.base.BaseActivity
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



        if (response.cells != null && response.cells.isNotEmpty()) {

            for (items in response.cells) {

                when (items!!.type) {
                    1 -> {
                        //Edittext
                        val edittext = EditText(this)
                        val editTextParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        edittext.layoutParams = editTextParams
                        edittext.hint = items.message
                        if(items.typefield == 1 || items.typefield == "text") {
                            //text
                        } else if(items.typefield == 2 || items.typefield == "telnumber") {
                            edittext.inputType = InputType.TYPE_CLASS_PHONE
                            //telNumber
                        }  else if(items.typefield == 3 || items.typefield == "email") {
                            //email
                            edittext.inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
                        } else {
                            //do nothing
                        }





                        parentLayout.addView(edittext)

                    }
                    2 -> {
                        //textview
                        val textView = TextView(this)
                        val textViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        textView.layoutParams = textViewParams
                        textView.text = items.message
                        parentLayout.addView(textView)
                    }
                    3 -> {
                        //image
                        val imgView = ImageView(this)
                        val imgViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        imgView.layoutParams = imgViewParams
                        imgView.setImageResource(android.R.drawable.ic_menu_add)
                        parentLayout.addView(imgView)

                    }
                    4 -> {
                        //checkbox
                        val cbView = CheckBox(this)
                        val cbViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        cbView.layoutParams = cbViewParams
                        cbView.isSelected = false;
                        cbView.text = items.message
                        parentLayout.addView(cbView)

                    }
                    5 -> {
                        //Button
                        val buttonView = Button(this)
                        val buttonViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        buttonView.layoutParams = buttonViewParams
                        buttonView.text = items.message
                        parentLayout.addView(buttonView)
                    }
                }


            }
            setViews(parentLayout)
        }

    }

//    private fun addValidations(type:Any,view: View) {
//
//        when(type) {
//            //type for text field.
//            1 -> {
//                view.setOnClickListener {
//
//                }
//            }
//            "text" -> {
//
//            }
//
//
//            2 -> {
//
//            }
//            "telNumber" -> {
//
//            }
//
//
//            3 -> {
//
//            }
//            "email" -> {
//
//            }
//        }
//    }

    private fun setViews(parentLayout: LinearLayout) {
        val parentLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        parentLayout.layoutParams = parentLayoutParams
        setContentView(parentLayout, parentLayoutParams)
    }

}

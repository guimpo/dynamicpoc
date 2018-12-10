package poc.dynamictemplatepoc.views.cells

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
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
                Log.e("Current item",items!!.message)
                val imgView = TextView(this)
                val imgViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                imgView.layoutParams = imgViewParams
                imgView.text = items.message

                parentLayout.addView(imgView)

            }
            setViews(parentLayout)
        }

    }


    fun setViews(parentLayout: LinearLayout) {
        val parentLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        parentLayout.layoutParams = parentLayoutParams
        setContentView(parentLayout, parentLayoutParams)
    }

}

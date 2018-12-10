package poc.dynamictemplatepoc.views.funds

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.TextView
import poc.dynamictemplatepoc.MyApplication
import poc.dynamictemplatepoc.model.DownInfoItem
import poc.dynamictemplatepoc.model.Funds
import poc.dynamictemplatepoc.model.InfoItem
import poc.dynamictemplatepoc.service.ServiceManager
import poc.dynamictemplatepoc.views.base.BaseActivity
import poc.dynamictemplatepoc.views.funds.adapter.DownInfoAdapter
import poc.dynamictemplatepoc.views.funds.adapter.InfoRecyclerAdapter
import javax.inject.Inject

class FundsActivity : BaseActivity(), FundsContract.View {

    @Inject
    lateinit var serviceManager: ServiceManager
    private var presenter: FundsContract.Presenter? = null
    var infoadapter: InfoRecyclerAdapter? = null
    var downInfoadapter: DownInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter()
    }

    private fun setPresenter() {
        presenter = FundsPresenter(serviceManager, this)
        presenter!!.loadFunds()
    }

    override fun setupComponent() {
        (application as MyApplication).getAppComponent(this).injectActivity(this)
    }


    override fun populateCells(response: Funds) {
        val parentLayout = LinearLayout(this)
        parentLayout.orientation = LinearLayout.VERTICAL

        if (response.screen != null) {
            val textViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            //title
            val titleTextView = TextView(this)
            titleTextView.layoutParams = textViewParams
            titleTextView.text = response.screen.title
            parentLayout.addView(titleTextView)

            //fundName
            val fundTextView = TextView(this)
            fundTextView.layoutParams = textViewParams
            fundTextView.text = response.screen.fundName
            parentLayout.addView(fundTextView)

            //what is
            val whatsTextView = TextView(this)
            whatsTextView.layoutParams = textViewParams
            whatsTextView.text = response.screen.whatIs
            parentLayout.addView(whatsTextView)

            //definition
            val defenitionTextView = TextView(this)
            defenitionTextView.layoutParams = textViewParams
            defenitionTextView.text = response.screen.definition
            parentLayout.addView(defenitionTextView)

            //riskTitle
            val riskTitleTextView = TextView(this)
            riskTitleTextView.layoutParams = textViewParams
            riskTitleTextView.text = response.screen.riskTitle
            parentLayout.addView(riskTitleTextView)

            //risk
            val riskTextView = TextView(this)
            riskTextView.layoutParams = textViewParams
            riskTextView.text = response.screen.risk.toString()
            parentLayout.addView(riskTextView)

            //infoTitle
            val infoTitleTextView = TextView(this)
            infoTitleTextView.layoutParams = textViewParams
            infoTitleTextView.text = response.screen.infoTitle
            parentLayout.addView(infoTitleTextView)

            //inforecyclerView
            var inforecyclerView = RecyclerView(this)
            inforecyclerView.layoutManager = LinearLayoutManager(this)
            infoadapter = InfoRecyclerAdapter(this, response.screen.info as List<InfoItem>)
            inforecyclerView.adapter = infoadapter
            //inforecyclerView.isNestedScrollingEnabled = false
            parentLayout.addView(inforecyclerView)

            //downinforecyclerView
            var downInfoRecyclerView = RecyclerView(this)
            downInfoRecyclerView.layoutManager = LinearLayoutManager(this)
            downInfoadapter = DownInfoAdapter(this, response.screen.downInfo as List<DownInfoItem>)
            downInfoRecyclerView.adapter = infoadapter
            //inforecyclerView.isNestedScrollingEnabled = false
            parentLayout.addView(downInfoRecyclerView)

        }
        setViews(parentLayout)
    }

    private fun setViews(parentLayout: LinearLayout) {
        val parentLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        parentLayout.layoutParams = parentLayoutParams
        parentLayoutParams.setMargins(10,10,10,10)
        setContentView(parentLayout, parentLayoutParams)
    }
}

package poc.dynamictemplatepoc.views.funds

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import poc.dynamictemplatepoc.model.Funds

class FundsActivity : AppCompatActivity(), FundsContract.View {
    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun populateCells(response: Funds) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(err: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

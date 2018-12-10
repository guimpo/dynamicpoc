package poc.dynamictemplatepoc.views.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity : AppCompatActivity() {

    lateinit var progressDialog : ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initComponents()
        setupComponent()
    }

    private fun initComponents() {
        progressDialog = ProgressDialog(this)

    }

    abstract fun setupComponent ()

    fun showLoading() {
        progressDialog.setMessage("Please wait !!..")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    fun hideLoading() {
        if(progressDialog.isShowing) {
            progressDialog.hide()
        }
    }

    fun showError(err:String) {
        Toast.makeText(this,err,Toast.LENGTH_LONG).show()
    }
}

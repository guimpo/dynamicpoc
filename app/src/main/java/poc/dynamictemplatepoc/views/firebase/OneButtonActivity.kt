package poc.dynamictemplatepoc.views.firebase


import android.os.Bundle
import android.widget.Toast
import poc.dynamictemplatepoc.R
import poc.dynamictemplatepoc.views.base.BaseActivity

class OneButtonActivity : BaseActivity(), OneButtonContract.View {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_button)
    }

    override fun setupComponent() {

    }

    override fun showUserNotAvailable() {
        Toast.makeText( this, "User not available.", Toast.LENGTH_LONG ).show();
    }
}

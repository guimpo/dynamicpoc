package poc.dynamictemplatepoc.views.firebase

class OneButtonPresenter(internal var model: OneButtonContract.Model) : OneButtonContract.Presenter {

    internal var view: OneButtonContract.View? = null

    override fun setView(view: OneButtonContract.View) {
        this.view = view
    }

//    fun loginButtonClicked() {
//
//
//        if (view != null) {
//
//            if (view!!.getFirstName().trim().equals("") || view!!.getLastName().equals("")) {
//
//                view!!.showInputError()
//            } else {
//
//
//                model.createUser(view!!.getFirstName(), view!!.getLastName())
//                view!!.showUserSaved()
//            }
//
//        }
//
//    }
//
    override fun getCurrentUser() {

        if(model.getUser() == null) {
            view?.showUserNotAvailable()
        }
    }
}

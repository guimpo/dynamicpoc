package poc.dynamictemplatepoc.views.firebase

import poc.dynamictemplatepoc.model.User

interface OneButtonContract {

    interface Model {
        fun getUser(): User
        fun createUser(user: User)
    }

    interface View {
        fun showUserNotAvailable()

    }

    interface Presenter {
        fun setView(view: OneButtonContract.View)
        fun getCurrentUser()
    }
}

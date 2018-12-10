package poc.dynamictemplatepoc.views.funds

import poc.dynamictemplatepoc.model.Funds

interface FundsContract {
    interface View {
        fun populateCells(response: Funds)
        fun showError(err:String)
    }

    interface Presenter {
        fun loadFunds()
    }
}
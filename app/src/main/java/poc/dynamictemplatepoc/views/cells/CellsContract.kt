package poc.dynamictemplatepoc.views.cells

import poc.dynamictemplatepoc.model.Cells


interface CellsContract {


    interface View {
        fun showLoading()
        fun hideLoading()
        fun populateCells(response:Cells)
        fun showError(err:String)
    }

    interface Presenter {
        fun loadCells()
    }
}
package poc.dynamictemplatepoc.views.cells

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import poc.dynamictemplatepoc.service.ServiceManager

/**
 * Created by Imran on 10/12/18
 */

class CellsPresenter(private val serviceManager: ServiceManager, private val activity: CellsActivity) : CellsContract.Presenter {

    override fun loadCells() {

        activity.showLoading()

        serviceManager.initCells()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { throwable ->
                    activity.showError(throwable.localizedMessage)
                }
                .subscribe({ response ->
                    activity.hideLoading()

                    activity.populateCells(response)
                },
                        { throwable ->
                            activity.hideLoading()
                            Log.e("t", throwable.localizedMessage)
                        })
    }

}
package poc.dynamictemplatepoc.views.funds

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import poc.dynamictemplatepoc.service.ServiceManager

class FundsPresenter(private val serviceManager: ServiceManager, private val activity: FundsActivity) : FundsContract.Presenter {


    override fun loadFunds() {
        activity.showLoading()

        serviceManager.initFunds()
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
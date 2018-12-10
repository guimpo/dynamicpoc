package poc.dynamictemplatepoc.service;

import io.reactivex.Single;
import poc.dynamictemplatepoc.model.Cells;
import poc.dynamictemplatepoc.model.Funds;
import retrofit2.http.GET;

public interface ServiceManager {

    @GET(ServiceConstants.CELLS)
    Single<Cells> initCells();

    @GET(ServiceConstants.FUNDS)
    Single<Funds> initFunds();
}
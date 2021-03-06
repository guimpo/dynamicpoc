package poc.dynamictemplatepoc.dagger;

import javax.inject.Singleton;

import dagger.Component;
import poc.dynamictemplatepoc.MyApplication;
import poc.dynamictemplatepoc.views.cells.CellsActivity;
import poc.dynamictemplatepoc.views.funds.FundsActivity;


@PerApplication
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    //inject Application
    void injectApp(MyApplication application);

    //inject Activities
    void injectActivity(CellsActivity mainActivity);
    void injectActivity(FundsActivity fundsActivity);
}

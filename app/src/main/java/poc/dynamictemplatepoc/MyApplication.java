package poc.dynamictemplatepoc;


import android.app.Application;
import android.content.Context;

import poc.dynamictemplatepoc.dagger.AppComponent;
import poc.dynamictemplatepoc.dagger.DaggerAppComponent;
import poc.dynamictemplatepoc.dagger.NetModule;

public class MyApplication extends Application {

    AppComponent component;


    @Override
    public void onCreate() {
        super.onCreate();

    component = DaggerAppComponent.builder()
            .netModule(new NetModule()).build();
        component.injectApp(this);



    }

    public AppComponent getAppComponent(Context context) {
        return ((MyApplication) context.getApplicationContext()).component;
    }
}

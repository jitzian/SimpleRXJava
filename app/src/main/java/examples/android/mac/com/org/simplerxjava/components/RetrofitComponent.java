package examples.android.mac.com.org.simplerxjava.components;

import dagger.Component;
import examples.android.mac.com.org.simplerxjava.MainActivity;
import examples.android.mac.com.org.simplerxjava.modules.RetrofitModule;

/**
 * Created by User on 11/27/2016.
 */

@Component(modules = {RetrofitModule.class})
public interface RetrofitComponent {
    void inject(MainActivity mainActivity);
}

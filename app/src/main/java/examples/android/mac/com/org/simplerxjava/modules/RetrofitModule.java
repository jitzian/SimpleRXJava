package examples.android.mac.com.org.simplerxjava.modules;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 11/27/2016.
 */

@Module
public class RetrofitModule {
    private String baseURL;

    public RetrofitModule(String baseURL) {
        this.baseURL = baseURL;
    }

    @Provides
    Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL)
                .build();
    }

}

package examples.android.mac.com.org.simplerxjava.rest;

import java.util.ArrayList;

import examples.android.mac.com.org.simplerxjava.model.GithubResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by User on 11/27/2016.
 */

public interface RetrofitRest {
    @GET("users/{user}/repos")
    Observable<ArrayList<GithubResult>> getRepositories(@Path("user")String username);
}

package examples.android.mac.com.org.simplerxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import examples.android.mac.com.org.simplerxjava.components.DaggerRetrofitComponent;
import examples.android.mac.com.org.simplerxjava.model.GithubResult;
import examples.android.mac.com.org.simplerxjava.modules.RetrofitModule;
import examples.android.mac.com.org.simplerxjava.rest.RetrofitRest;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.mEditText)
    EditText mEditText;

    @BindView(R.id.mButtonSearch)
    Button mButtonSearch;

    @BindView(R.id.mTextView)
    TextView mTextView;

    @BindView(R.id.mTextView2)
    TextView mTextView2;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        ButterKnife.bind(this);

        DaggerRetrofitComponent.builder()
                .retrofitModule(new RetrofitModule("https://api.github.com/"))
                .build()
                .inject(this);

        final RetrofitRest retrofitRest = retrofit.create(RetrofitRest.class);

        RxTextView.textChanges(mEditText)
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        Log.d(TAG, ":" + charSequence);
                        if(charSequence.length() >= 3){
                            Observable<ArrayList<GithubResult>> arrayListObservable = retrofitRest
                                    .getRepositories(String.valueOf(charSequence));

                            arrayListObservable.subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Subscriber<ArrayList<GithubResult>>() {
                                        @Override
                                        public void onCompleted() {
                                            Log.d(TAG, "onCompleted");
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.d(TAG, "onError::" + e.getMessage());
                                        }

                                        @Override
                                        public void onNext(ArrayList<GithubResult> githubResults) {
                                            Log.d(TAG, "onNext::" + githubResults.size());
                                            String content = "";
                                            for(GithubResult obj : githubResults){
                                                content += obj.getFullName() + "\n";
                                            }
                                            mTextView.setText(content);
                                        }
                                    });
                        }
                    }
                });

        RxView.clicks(mButtonSearch).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Log.d(TAG, "Button Clicked");
                mTextView2.setText("New tag");
            }
        });
    }
}

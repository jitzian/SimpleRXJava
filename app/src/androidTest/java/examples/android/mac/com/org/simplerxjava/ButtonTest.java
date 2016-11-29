package examples.android.mac.com.org.simplerxjava;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by User on 11/29/2016.
 */

@RunWith(AndroidJUnit4.class)
public class ButtonTest {
    @Rule
    public ActivityTestRule<MainActivity>mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testClick(){
        onView(withId(R.id.mButtonSearch)).perform(click());
        onView(withId(R.id.mTextView2)).check(matches(withText("New_tag")));
    }

}

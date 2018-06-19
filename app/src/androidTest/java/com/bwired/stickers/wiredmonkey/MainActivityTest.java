/*
 * Copyright ©2017 Bwired Technologies Inc. All Rights Reserved
 *
 * We design, build & support a large volume, highly scalable and engaging digital products.
 * We have aligned ourselves with best-in-class technologies across Web, Mobile and Cloud Computing.
 * We work with clients who share our passion for innovative solutions. Let’s build something great together.
 *
 * https://bwired.ca/
 *
 * This class is for testing
 *
 * Developed by Riya Varghese
 */

package com.bwired.stickers.wiredmonkey;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.bwired.stickers.wiredmonkey.view.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Before
    public void setUp() throws Exception {
        //Before Test case execution
    }

    @Test
    public void launchActivity()
    {
        assertTrue(mActivityRule.getActivity() != null);
    }

    @Test
    public void imageDisplayed()
    {
        onView(withId(R.id.view_pager)
        ).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        //After Test case Execution
    }
}
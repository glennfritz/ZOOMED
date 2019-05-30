package zuoix.com.zoomed.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zuoix.com.zoomed.R;

public class WelcomeActivity extends AppCompatActivity {

    private SharedPref prefManager;
    private int[] layouts;
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private Button btnNext, btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Checking for first time launch - before calling setContentView
        prefManager = new SharedPref(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        } else {

            //Make notification bar transperent
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            }

            setContentView(R.layout.activity_welcome);

            if (prefManager.getDefaultSim() == -1) {
                final SmsManager[] smsManager = {null};
                List<String> simNames = new ArrayList<>();
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                    //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    SubscriptionManager subscriptionManager = (SubscriptionManager) getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
                    @SuppressLint("MissingPermission") final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
                    for (int i = 1; i < subscriptionInfoList.size() + 1; i++) {
                        simNames.add("Sim " + i);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setCancelable(false);
                    builder.setTitle(R.string.select_sim)
                            .setItems(simNames.toArray(new String[simNames.size()]), new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                                public void onClick(DialogInterface dialog, int pos) {
                                    // The 'which' argument contains the index position
                                    // of the selected item
                                    int subId = subscriptionInfoList.get(pos).getSubscriptionId();// change index to 1 if you want to get Subscrption Id for slot 1.
                                    smsManager[0] = SmsManager.getSmsManagerForSubscriptionId(subId);
                                    prefManager.setDefaultSim(smsManager[0].getSubscriptionId());
                                    Log.d("SmsManager", String.valueOf(smsManager[0].getSubscriptionId()));
                                }
                            });
                    builder.create();
                    builder.show();

                } else {
                    smsManager[0] = SmsManager.getDefault();
                }

            }

            viewPager = findViewById(R.id.view_pager);
            dotsLayout = findViewById(R.id.layoutDots);
            btnNext = findViewById(R.id.btn_next);
            btnSkip = findViewById(R.id.btn_skip);

            // layouts of all welcome sliders
            // add few more layouts if you want
            layouts = new int[]{
                    R.layout.welcome_slide0,
                    R.layout.welcome_slide1,
                    R.layout.welcome_slide3,
                    R.layout.welcome_slide5,
                    R.layout.welcome_slide6
            };

            // adding bottom dots
            addBottomDots(0);

            // making notification bar transparent
            changeStatusBarColor();

            MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter();
            viewPager.setAdapter(myViewPagerAdapter);
            viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int current = getItem(+1);
                    if (current < layouts.length) {
                        // move to next screen
                        viewPager.setCurrentItem(current);
                    } else {
                        launchHomeScreen();
                    }
                }
            });
            btnSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchHomeScreen();
                }
            });
        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}


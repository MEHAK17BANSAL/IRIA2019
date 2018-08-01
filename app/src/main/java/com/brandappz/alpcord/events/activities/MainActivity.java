package com.brandappz.alpcord.events.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.constants.NavigationPositions;
import com.brandappz.alpcord.events.fragments.HomeFragment;
import com.brandappz.alpcord.events.fragments.OrganizingCommitteeFragment;
import com.brandappz.alpcord.events.fragments.GalleryFragment;
import com.brandappz.alpcord.events.fragments.HotelFragment;
import com.brandappz.alpcord.events.fragments.QuizFragment;
import com.brandappz.alpcord.events.fragments.QuizLeaderBoard;
import com.brandappz.alpcord.events.fragments.SpeakersFragment;
import com.brandappz.alpcord.events.fragments.SupportFragment;
import com.brandappz.alpcord.events.fragments.TabmolaFragment;
import com.brandappz.alpcord.events.fragments.VideoFragment;
import com.brandappz.alpcord.events.helper.PrefManager;
import com.brandappz.alpcord.events.pushbotsHandler;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.pushbots.push.Pushbots;

public class MainActivity extends AppCompatActivity {

    public ImageView menuicon;
    private DrawerLayout drawer;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private PrefManager pref;
    private String[] navigationTitles;
    private CharSequence drawerTitle;
    private CharSequence title;
    private TextView htitle;
    private FrameLayout frameLayout;
    private Spinner spinner;
    private boolean isDualPane;
    private LinearLayout quiz, main, agenda, doanddont, speaker, photovideo, hotallocation, tambola, flightticket,contactus,abs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
       /* ServerManager serverManager = new ServerManager(this);
        serverManager.checkVersion();*/
        Pushbots.sharedInstance().init(this);
        final YouTubeInitializationResult result = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this);

        if (result != YouTubeInitializationResult.SUCCESS) {
            //If there are any issues we can show an error dialog.
            result.getErrorDialog(this, 0).show();
        }
        menuicon = (ImageView) findViewById(R.id.menuicon);
        Pushbots.sharedInstance().registerForRemoteNotifications();
        Pushbots.sharedInstance().setCustomHandler(pushbotsHandler.class);
        MultiDex.install(this);
        title = drawerTitle = getTitle();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("DashBoard", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("success", "true");
        editor.commit();

        htitle = (TextView) findViewById(R.id.title);
        spinner=findViewById(R.id.btn_play_radio);
        frameLayout=findViewById(R.id.layout_container_activity_main);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        main = (LinearLayout) findViewById(R.id.menu);
        quiz = (LinearLayout) findViewById(R.id.quiz);
        agenda = (LinearLayout) findViewById(R.id.agenda);
        doanddont = (LinearLayout) findViewById(R.id.doanddont);
        speaker = (LinearLayout) findViewById(R.id.speaker);
        photovideo = (LinearLayout) findViewById(R.id.photovideo);
        hotallocation = (LinearLayout) findViewById(R.id.hotallocation);
        flightticket = (LinearLayout) findViewById(R.id.flightticket);
        contactus=(LinearLayout) findViewById(R.id.contactus);
        tambola = (LinearLayout) findViewById(R.id.tambola);
        abs=(LinearLayout) findViewById(R.id.abstracts);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(Gravity.LEFT);
                positions(1);
            }
        });
        doanddont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(Gravity.LEFT);
                positions(2);
            }
        });
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(Gravity.LEFT);
                positions(3);
            }
        });
        photovideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(Gravity.LEFT);
                positions(4);
            }
        });
        hotallocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(Gravity.LEFT);
                positions(5);
            }
        });
        flightticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(Gravity.LEFT);
                positions(6);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(Gravity.LEFT);
                positions(7);
            }
        });
        tambola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(Gravity.LEFT);
                positions(8);
            }
        });
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(Gravity.LEFT);
              positions(11);
            }
        });
        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positions(12);
            }
        });


        menuicon.setBackgroundResource(R.drawable.menu22);
        positions(1);
    }


    public void setContent(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fragment)
                .commit();
    }

    public void setDetails(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.details, fragment)
                .commit();
    }

    public Drawable setTint(int color) {
        Drawable wrappedDrawable = getResources().getDrawable(R.drawable.ic_menu_white_24dp);
        DrawableCompat.setTint(wrappedDrawable, color);
        return wrappedDrawable;
    }
    public  Drawable getTintedDrawable(int color) {
        Drawable wrapDrawable =  getResources().getDrawable(R.drawable.ic_menu_white_24dp);
        DrawableCompat.setTint(wrapDrawable, color);
        DrawableCompat.setTintMode(wrapDrawable, PorterDuff.Mode.SRC_IN);
        return wrapDrawable;
    }
    public void cpos(int pos){
        if(pos==1){
            menuicon.setBackgroundResource(R.drawable.menu22);
        }else if(pos==2){
            menuicon.setBackgroundResource(R.drawable.menu22);
        }else if(pos==3){
            menuicon.setBackgroundResource(R.drawable.menu22);
        }else if(pos==4){
            menuicon.setBackgroundResource(R.drawable.menu22);
        }else if(pos==5){
            menuicon.setBackgroundResource(R.drawable.menu22);
        }else if(pos==6){
            menuicon.setBackgroundResource(R.drawable.menu22);
        }else if(pos==7){
            menuicon.setBackgroundResource(R.drawable.menu22);
        }else if(pos==8){
            menuicon.setBackgroundResource(R.drawable.menu22);
        }
        else if(pos==11){
            menuicon.setBackgroundResource(R.drawable.menu22);
        }

    }
    public void positions(int position) {
        Fragment content = null;
        switch (position) {
            case 1:
                menuicon.setBackgroundResource(R.drawable.menu22);
                //  menuicon.setColorFilter(getResources().getColor(R.color.blue));
                content = new HomeFragment();
                frameLayout.setVisibility(View.INVISIBLE);
                htitle.setText("WELCOME MESSAGE");
                break;
            case 2:
                menuicon.setBackgroundResource(R.drawable.menu22);
                frameLayout.setVisibility(View.VISIBLE);
                content = new OrganizingCommitteeFragment();
                htitle.setText("ORGANIZING COMMITTEE");
                break;
            case 3:
                menuicon.setBackgroundResource(R.drawable.menu22);
                content = new SpeakersFragment();
                htitle.setText("REGISTRATION/SIGN IN");
                break;
            case 4:
                menuicon.setBackgroundResource(R.drawable.menu22);
                htitle.setText("Event Photos");
                content = new NewGalary();
                break;
            /*case NavigationPositions.QUIZ:
                content = new QuizFragment();
                break;
*/
            case 5:
                menuicon.setBackgroundResource(R.drawable.menu22);
                content = new HotelFragment();
                menuicon.setColorFilter(getResources().getColor(R.color.nblue), PorterDuff.Mode.SRC_ATOP);
                htitle.setText("Hotel and its Location");
                break;
            case 6:
                menuicon.setBackgroundResource(R.drawable.menu22);
                content = new OfficeBearersFragment();
                htitle.setText("OFFICE BEARERS");
                break;
            case 7:
                menuicon.setBackgroundResource(R.drawable.menu22);
                content = new QuizFragment();
                htitle.setText("Quiz");
                break;
            case 8:
                menuicon.setBackgroundResource(R.drawable.menu22);
                content = new TabmolaFragment();
                htitle.setText("Accommodation");
                break;

            case 9:
                content = new GalleryFragment();
                htitle.setText("Flight tickets");
                break;

            case 10:
                content = new SupportFragment();
                break;
            case 11:
                menuicon.setBackgroundResource(R.drawable.menu22);
                content = new ContactUs();
                htitle.setText("CONTACT US");
                break;
            case 12:
               Intent intent=new Intent(MainActivity.this,AbstractActivity.class);
               startActivity(intent);
                break;


        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, content)
                .commit();
    }

    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure close the App");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
                moveTaskToBack(true);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private class NavigationClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Intent intent;
            Fragment content = null;
            Fragment details = null;
            if (position == 0)
                return;
            switch (position) {

                case NavigationPositions.AGENDA:
                    content = new HomeFragment();
                    break;
                case NavigationPositions.QUIZ:

                    content = new QuizFragment();
                    break;
                case NavigationPositions.SPEAKERS:
                    content = new SpeakersFragment();
                    break;
                case NavigationPositions.GALLERY:
                    //startActivity(new Intent(getApplicationContext(), SplashActivity.class));
                    content = new GalleryFragment();
                    break;
                case NavigationPositions.QUIZLeaderBoard:
                    content = new QuizLeaderBoard();
                    break;
                case NavigationPositions.VIDEO:
                    content = new VideoFragment();
                    break;
                case NavigationPositions.SUPPORT:
                    content = new SupportFragment();
                    break;
                case NavigationPositions.DODONTS:
                    content = new OrganizingCommitteeFragment();
                    break;
                case NavigationPositions.HOTEL:
                    content = new HotelFragment();
                    break;


            }
            setContent(content);
            if (details != null)
                setDetails(details);

            drawerList.setItemChecked(position, true);
            setTitle(navigationTitles[position - 1]);
            drawer.closeDrawer(drawerList);
        }
    }

}

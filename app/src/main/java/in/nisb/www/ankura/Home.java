package in.nisb.www.ankura;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ListView;


public class Home extends AppCompatActivity {

    //to toggle the drawer
    private ActionBarDrawerToggle actionBarDrawerToggle;
    //reference to the drawer layout
    private DrawerLayout drawerLayout;
    //reference to the list to be displayed when drawer is opened
    private ListView navList;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //for the main drawer layout
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        //for the list view
        navList = (ListView)findViewById(R.id.navlist);

        //to listen to drawer response
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.opendrawer,R.string.closedrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //for the icons to be displayed for the drawer
        ActionBar actionBar = getSupportActionBar();
        //for displaying the hamberburger icon when the drawer is closed
        actionBar.setDisplayShowHomeEnabled(true);
        //for showing the back icon when the drawer is opened
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
}

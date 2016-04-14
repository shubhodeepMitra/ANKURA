package in.nisb.www.ankura;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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
        setContentView(R.layout.activity_main);

        //for the main drawer layout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //for the list view
        navList = (ListView) findViewById(R.id.navlist);

        /**
         * the array containing the fragments
         */
        ArrayList<String> navArray = new ArrayList<String>();
        navArray.add("Home");
        navArray.add("Fragment 1");
        navArray.add("Fragment 2");
        navArray.add("Fragment 3");


        //to choose only one item from the list
        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // simple_list_item_activated is used to retain the choice made by the user
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, navArray);
        navList.setAdapter(adapter);

        //to listen the item click from the list
        navList.setOnItemClickListener(this);


        //to listen to drawer response
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.opendrawer, R.string.closedrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //for the icons to be displayed for the drawer
        ActionBar actionBar = getSupportActionBar();
        //for displaying the hamberburger icon when the drawer is closed
        actionBar.setDisplayShowHomeEnabled(true);
        //for showing the back icon when the drawer is opened
        actionBar.setDisplayHomeAsUpEnabled(true);

        fragmentManager = getSupportFragmentManager();

        //by default we need to be in the home activity hence we are sending 0
        loadSelection(0);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        // to close the drawer when its opened and vice versa
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(navList)) {
                drawerLayout.closeDrawer(navList);
            } else {
                drawerLayout.openDrawer(navList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override

    /**
     * to save time when the setting is changed from potrait to landscape and vice versa
     */
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     *
     * choose the fragment from the option which is selected
     */
    private void loadSelection(int i) {
        navList.setItemChecked(i, true);
        switch (i) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, homeFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                MyFragment1 myFragment1 = new MyFragment1();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, myFragment1);
                fragmentTransaction.commit();
                break;
            case 2:
                MyFragment2 myFragment2 = new MyFragment2();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, myFragment2);
                fragmentTransaction.commit();
                break;
            case 3:
                MyFragment3 myFragment3 = new MyFragment3();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,myFragment3);
                fragmentTransaction.commit();
        }
    }

    /**
     * to overcome compatibility error as faced from inheritance
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadSelection(position);

        //to dismiss the drawer after its been loaded
        drawerLayout.closeDrawer(navList);
    }
}

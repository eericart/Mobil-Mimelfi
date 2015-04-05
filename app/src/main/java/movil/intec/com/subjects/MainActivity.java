package movil.intec.com.subjects;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.shamanland.fab.FloatingActionButton;
import com.shamanland.fab.ShowHideOnScroll;

import java.util.ArrayList;

import movil.intec.com.subjects.helper.DatabaseHelper;
import movil.intec.com.subjects.model.Subject;


public class MainActivity extends ActionBarActivity {
    //private SQLiteDatabase sql;
    private DatabaseHelper db;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        db = DatabaseHelper.getInstance(getApplicationContext());

        Subject s1 = new Subject("Espanol", "Liguia");
        Subject s2 = new Subject("Espanol2", "Liguia");
        Subject s3 = new Subject("Espanol3", "Liguia");
        Subject s4 = new Subject("Espanol4", "Liguia");
        Subject s5 = new Subject("Espanol5", "Liguia");
        Subject s6 = new Subject("Espanol6", "Liguia");
        Subject s7 = new Subject("Espanol7", "Liguia");
        Subject s8 = new Subject("Espanol8", "Liguia");

        Log.e("S Count", "S count: " + db.getSubjectsCount());

        db.createSubject(s1,new int[] {});
        db.createSubject(s2,new int[] {});
        db.createSubject(s3,new int[] {});
        db.createSubject(s4,new int[] {});
        db.createSubject(s5,new int[] {});
        db.createSubject(s6,new int[] {});
        db.createSubject(s7,new int[] {});
        db.createSubject(s8,new int[] {});

        Log.e("S Count", "S count: " + db.getSubjectsCount());

        show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void show(){
        ArrayList<Subject> s=db.getAllSubjects();

        ListView list = (ListView) findViewById(R.id.listView);
        CustomAdapter cursorAdapter = new CustomAdapter(getApplicationContext(),s);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        list.setOnTouchListener(new ShowHideOnScroll(fab));

        if (s.isEmpty()){
            showMessage("Error", "No records found");
            return;
        }
        else{
            list.setAdapter(cursorAdapter);
            Log.e("info", "records found");
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("FAB", "Add Click ");
                Intent i = new Intent(MainActivity5.this, AddSubjectsActivity.class);
                startActivity(i);

            }
        });
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

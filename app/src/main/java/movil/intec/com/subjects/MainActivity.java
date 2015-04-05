package movil.intec.com.subjects;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.software.shell.fab.ActionButton;

import java.util.ArrayList;

import movil.intec.com.subjects.helper.DatabaseHelper;
import movil.intec.com.subjects.model.Subject;


public class MainActivity extends ActionBarActivity {
    //private SQLiteDatabase sql;
    private DatabaseHelper db;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        db = DatabaseHelper.getInstance(getApplicationContext());

        ArrayList<Subject> array = db.getAllSubjects();

        if (array.isEmpty()){
            Subject sub = new Subject("Introduccion a la vida", "Marcos Gimian");
            db.createSubject(sub,new int[] {});
        }
        show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent i;

        switch (id){
            case R.id.menuSubject:
                i = new Intent(MainActivity.this, AddSubjectsActivity.class);
                startActivity(i);
                break;
            case R.id.quit:
                this.finish();
                break;
            case R.id.menuShowCalendar:
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }


    private void show(){
        ArrayList<Subject> s=db.getAllSubjects();

        ListView list = (ListView) findViewById(R.id.listView);
        CustomAdapter cursorAdapter = new CustomAdapter(getApplicationContext(),s);

        final ActionButton fab = (ActionButton) findViewById(R.id.action_button);

        if (s.isEmpty()){
            showMessage("Error", "No records found");
            return;
        }
        else{
            list.setAdapter(cursorAdapter);
            Log.e("info", "records found");
        }

        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if(mLastFirstVisibleItem<firstVisibleItem)
                {
                    Log.i("SCROLLING DOWN", "TRUE");
                    fab.hide();
                }
                if(mLastFirstVisibleItem>firstVisibleItem)
                {
                    Log.i("SCROLLING UP","TRUE");
                    fab.show();
                }
                mLastFirstVisibleItem=firstVisibleItem;

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fab.setShowAnimation(ActionButton.Animations.FADE_IN);
                Log.e("FAB", "Add Click ");

                Intent i = new Intent(MainActivity.this, AddSubjectsActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_fade_in);
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

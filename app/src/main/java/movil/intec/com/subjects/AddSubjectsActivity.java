package movil.intec.com.subjects;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AddSubjectsActivity extends ActionBarActivity {

    LinearLayout ll;
    Button btnFecha;
    int _intMyLineCount;
    private Toolbar mToolbar;


    private List<Spinner> spinnerList = new ArrayList<Spinner>();
    private List<EditText> etStart =new ArrayList<EditText>();
    private List<EditText> etEnd =new ArrayList<EditText>();
    private List<LinearLayout> linearlayoutList=new ArrayList<LinearLayout>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(mToolbar);


        ll=(LinearLayout) findViewById(R.id.ll);
        btnFecha=(Button) findViewById(R.id.btnFecha);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.addView(linearlayout(_intMyLineCount));
                _intMyLineCount++;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    private Spinner daySpinner(int _intID) {
        final String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,days);
        Spinner spinner = new Spinner(this);
        spinner.setAdapter(dataAdapter);
        spinner.setId(_intID);
        spinner.setBackgroundColor(Color.CYAN);
        spinnerList.add(spinner);
        return spinner;
    }

    private EditText editTextEnd(int _intID) {
        final EditText editText = new EditText(this);
        editText.setId(_intID);
        editText.setHint("My lines");
        editText.setWidth(180);
        editText.setBackgroundColor(Color.CYAN);
        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddSubjectsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editText.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        etEnd.add(editText);
        return editText;
    }
    private EditText editTextStart(int _intID) {
        final EditText editText = new EditText(this);
        editText.setId(_intID);
        editText.setHint("My lines");
        editText.setWidth(180);
        editText.setBackgroundColor(Color.CYAN);
        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddSubjectsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editText.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        etStart.add(editText);
        return editText;
    }

    private LinearLayout linearlayout(int _intID)
    {
        LinearLayout LLMain=new LinearLayout(this);
        LLMain.setId(_intID);
        LLMain.addView(daySpinner(_intID));
        LLMain.addView(editTextStart(_intID));
        LLMain.addView(editTextEnd(_intID));
        LLMain.setOrientation(LinearLayout.HORIZONTAL);
        linearlayoutList.add(LLMain);
        return LLMain;
    }

}

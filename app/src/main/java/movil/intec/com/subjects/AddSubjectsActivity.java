package movil.intec.com.subjects;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import movil.intec.com.subjects.helper.DatabaseHelper;
import movil.intec.com.subjects.model.Subject;


public class AddSubjectsActivity extends ActionBarActivity {

    private DatabaseHelper db;
    LinearLayout ll;
    Button btnFecha, btnLimpiar, btnAgregar;
    EditText asigT, profesorT;
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

        db = DatabaseHelper.getInstance(getApplicationContext());

        mToolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(mToolbar);


        ll=(LinearLayout) findViewById(R.id.ll);

        btnFecha=(Button) findViewById(R.id.btnFecha);
        btnLimpiar=(Button) findViewById(R.id.btnLimpiar);
        btnAgregar = (Button) findViewById(R.id.btnAñadir);

        asigT = (EditText) findViewById(R.id.asigText);
        profesorT = (EditText) findViewById(R.id.profText);


        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.addView(linearlayout(_intMyLineCount));
                _intMyLineCount++;
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View w){
                asigT.setText("");
                profesorT.setText("");
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View e){
               if(nameValidate(asigT.getText().toString(),profesorT.getText().toString())){

                   Subject sub = new Subject(asigT.getText().toString(), profesorT.getText().toString());
                   db.createSubject(sub,new int[] {});
                   Toast toast1 =
                           Toast.makeText(getApplicationContext(),
                                   "ASIGNATURA AGREGADA", Toast.LENGTH_LONG);
                   toast1.show();
               }
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


    public boolean nameValidate(String asig, String prof) {
        String error1 = "-El nombre de la asignatura (" + asig + ") no es valido.\n\t";
        String error2 = "-El nombre del profesor (" + prof + ") no es valido.\n";
        String error = "";
        int con = 0;

        if (asig.length() == 0) {
            error += error1;
        } else {
            while (con < asig.length()) {

                int code = Character.valueOf(asig.charAt(con));
                con++;

                if ((code < 65 || code > 90) && (code < 97 || code > 122) && (code != 32)) {

                    error += error1;
                    con = asig.length();

                }
            }
        }

        con=0;

        if (prof.length() == 0) {
            error += error2;
        } else {
            while (con < prof.length()) {

                int code = Character.valueOf(prof.charAt(con));
                con++;

                if ((code < 65 || code > 90) && (code < 97 || code > 122) && (code != 32)) {

                    error += error2;
                    con = prof.length();

                }
            }
        }

        if (error.length() == 0) {
            return true;
        } else {
            String out = "Error al procesar los nombres. \n\nDetalles:\n\t" + error;
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            out, Toast.LENGTH_LONG);
            toast1.show();

            return false;
        }

    }


}

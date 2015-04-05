package movil.intec.com.subjects;

/**
 * Created by ernest on 04/04/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import movil.intec.com.subjects.model.Subject;

//@SuppressWarnings("serial")
public class CustomAdapter extends ArrayAdapter<Subject> implements Serializable {

    public CustomAdapter(Context context, ArrayList<Subject> s) {
        super(context, 0, s);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Subject subject = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subjects, parent, false);
        }

        convertView.setTag(subject.getId());


        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvProfesor = (TextView) convertView.findViewById(R.id.tvHometown);
        // Populate the data into the template view using the data object
        tvName.setText(subject.getName());
        tvProfesor.setText(subject.getProfesor());
        // Return the completed view to render on screen
        return convertView;
    }
}
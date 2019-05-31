package zuoix.com.zoomed.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.TimeZone;

import zuoix.com.zoomed.R;


public class SettingFragment extends Fragment {

    Spinner mSpinnerGeneration;
    SearchableSpinner mSpinnerTimeZone;
    Spinner mSpinnerLanguage;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        mSpinnerGeneration = view.findViewById(R.id.set_generation);
        mSpinnerLanguage = view.findViewById(R.id.set_language);
        mSpinnerTimeZone = view.findViewById(R.id.set_timezone);

        setUpTimeZoneSpinner();
        setUpLanguageSpinner();
        setUpGenerationSpinner();
        return view;
    }

    private void setUpGenerationSpinner() {

        final ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_layout
                , new String[]{"1G", "2G", "4G"});
        mSpinnerGeneration.setAdapter(adapter);

        mSpinnerGeneration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String generation = (String) adapter.getItem(position);
                // TODO update the generation in shared prefs
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpLanguageSpinner() {
        final ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_layout
                , new String[]{"English", "French", "Dutch"});
        mSpinnerLanguage.setAdapter(adapter);

        mSpinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String language = (String) adapter.getItem(position);
                // TODO do what has to be done to change langauge of app
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpTimeZoneSpinner() {


        //populate spinner with all timezones
        String[] idArray = TimeZone.getAvailableIDs();
        final ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_layout,
                idArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerTimeZone.setAdapter(adapter);

        // now set the spinner to default timezone from the time zone settings
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).equals(TimeZone.getDefault().getID())) {
                mSpinnerTimeZone.setSelection(i);
            }
        }

        // when ever a different time zone is selected the shared pref value is updated
        mSpinnerTimeZone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String timeZoneId = (String) adapter.getItem(position);
                // TODO save the timeZoneId in sharedPreferences whenever time zone is needed use it to get time zone
                // like this: TimeZone.getTimeZone(timeZoneId)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


}

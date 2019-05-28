package zuoix.com.zoomed.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zuoix.com.zoomed.AutoFitGridRecyclerView;
import zuoix.com.zoomed.BaseApplication;
import zuoix.com.zoomed.CommandAdapter;
import zuoix.com.zoomed.R;
import zuoix.com.zoomed.activities.SharedPref;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommandFragment extends Fragment {
    AutoFitGridRecyclerView commandView;
    RecyclerView.Adapter adapter;


    public CommandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_command, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        commandView = view.findViewById(R.id.command_list);
        adapter = new CommandAdapter(getContext());
        commandView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}

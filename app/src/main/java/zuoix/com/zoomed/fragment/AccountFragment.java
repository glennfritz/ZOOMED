package zuoix.com.zoomed.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zuoix.com.zoomed.R;
import zuoix.com.zoomed.models.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private User user;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Log.d("Firstname", user.getFirstname() + ": "+ user.getPhone_number());
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    public void setCurrentUser(User user) {
        this.user = user;
    }
}

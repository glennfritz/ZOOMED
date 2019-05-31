package zuoix.com.zoomed.activities;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zuoix.com.zoomed.R;
import zuoix.com.zoomed.models.TrackableDevice;
import zuoix.com.zoomed.models.User;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText mEtUserName;
    TextInputEditText mEtPassword;
    TextInputLayout usernameLayout;
    TextInputLayout passwordLayout;

    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Setup Demo users
        mEtUserName = findViewById(R.id.username);
        mEtPassword = findViewById(R.id.password);
        usernameLayout = findViewById(R.id.usernameLayout);
        passwordLayout = findViewById(R.id.passwordLayout);

        userList.addAll(User.setup());
        System.out.print(userList);

    }

    private JSONObject getFakeResponse() {
        return null;
    }

    private void setUserInfoInSharedPrefs(User user){
        // try to extract relevant data from response and save in shared preferences
        MainActivity.setUserData(user);
        // login the user after successful authentication
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void showErrorMessage(String s) {
        Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
    }

    private void hideProgressBar() {
        // TODO hide the progress bar here
    }

    private void showProgressBar() {
        // TODO show the progress bar here
    }

    private String getUrl(String name, String password) {

        return buildTheUrl(name, password);
    }

    private String buildTheUrl(String name, String password) {
        // TODO: build the url to be used to request for the users information here
        return "";
    }

    public void logUserIn(View view) {
        if (!TextUtils.isEmpty(mEtUserName.getText().toString()) && !TextUtils.isEmpty(mEtPassword.getText().toString())){
            //Both fields have been filled
            String username = mEtUserName.getText().toString();
            String password = mEtPassword.getText().toString();

            for (User user : userList) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    //User is a valid user Log user in
                    setUserInfoInSharedPrefs(user);
                    break;
                }
                else {
                    //user is not a valid user
                    Toast.makeText(LoginActivity.this, "Invalid user", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if(TextUtils.isEmpty(mEtUserName.getText().toString())){
            usernameLayout.setError("Please enter username");
        }
        else if (TextUtils.isEmpty(mEtPassword.getText().toString())) {
            passwordLayout.setError("Password is incorrect");
        }
    }
}
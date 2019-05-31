package zuoix.com.zoomed.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import zuoix.com.zoomed.R;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    TextInputEditText mEtUserName;
    @BindView(R.id.password)
    TextInputEditText mEtPassword;
    @BindView(R.id.login)
    Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mBtnLogin = findViewById(R.id.login);
        mEtUserName = findViewById(R.id.username);
        mEtPassword = findViewById(R.id.password);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // for now we login with fake data
                //setUserInfoInSharedPrefs(getFakeResponse());
                startActivity(new Intent(LoginActivity.this, MainActivity.class));

//
//
//
//                String name = mEtUserName.getText().toString();
//                String password = mEtPassword.getText().toString();
//
//                if (TextUtils.isEmpty(name)) {
//                    Snackbar.make(v, "Sorry, you can't login without a name", Snackbar.LENGTH_SHORT);
//                    return;
//                }
//
//                if (TextUtils.isEmpty(name)) {
//                    Snackbar.make(v, "Sorry, you can't login without a password", Snackbar.LENGTH_SHORT);
//                    return;
//                }
//
//                // make the progress bar visible because we about to start a query to server
//                showProgressBar();
//
//                String url = getUrl(name, password);
//
//
//                new JsonObjectRequest(Request.Method.GET, url, null
//                        , new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // hide the progress bar because response is available
//                        hideProgressBar();
//
//                        try {
//
//                            setUserInfoInSharedPrefs(response);
//
//                        } catch (JSONException e) {
//
//                            showErrorMessage("An unexpected Error while loading your subscriptions details");
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // hide the progress bar because response is available
//                        hideProgressBar();
//                        showErrorMessage("Please check your internet connectivity and try again");
//                    }
//                });
            }
        });

    }

    private JSONObject getFakeResponse() {
        return null;
    }

    private void setUserInfoInSharedPrefs(JSONObject response) throws JSONException {
        // try to extract relevant data from response and save in shared preferences
        new SharedPref(getBaseContext()).setUserInformation(response);
        // login the user after successful authentication
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
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
}
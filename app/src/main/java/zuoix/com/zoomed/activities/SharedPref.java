package zuoix.com.zoomed.activities;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zuoix.com.zoomed.models.Car;

public class SharedPref {
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private static final String FIRST_NAME = "first name";
    private static final String LAST_NAME = "last name";
    private static final String PHONE_NUMBER = "phone number";
    private static final String EMAIL = "email";
    private static final String CARS = "cars";
    public static final String CAR_MATRICULE = "car matricule";
    public static final String CAR_MODEL = "car model";
    // shared pref mode
    int PRIVATE_MODE = 0;
    // Shared preferences name
    //private static final String PREF_NAME = "zoomed-welcome";
    private static final String PREF_NAME = "pref";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public SharedPref(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sp.edit();
    }

    public void setGeneration(int generation){
        editor.putInt("generation",generation);
        editor.commit();
    }

    public int getDefaultSim(){
        return sp.getInt("sim",-1);
    }

    public String getDestinationNumber(){
        return sp.getString("number","653251366");

    }
    public void setDestinationNumber(String number){
        editor.putString("number",number);
        editor.commit();
    }

    public void setDefaultSim(int id){
        editor.putInt("sim", id);
        editor.commit();
    }

    public Double getLatitude(){
        return Double.parseDouble(sp.getString("latitude","37.4220041"));
    }

    public Double getLongitude(){
        return Double.parseDouble(sp.getString("longitude","-122.0862515"));
    }
    public void setLatitude(String latitude){
        editor.putString("latitude",latitude);
        editor.commit();
    }

    public void setLongitude(String longitude){
        editor.putString("longitude",longitude);
        editor.commit();
    }

    public void setCountry(String country){
        editor.putString("country",country);
        editor.commit();
    }


    public String getCountry(){
        return sp.getString("country","Cameroon");
    }


    public int getGeneration(){
        return sp.getInt("generation",1);
    }

    //First Time Launch Methods
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean istrue(){
        return true;
    }


    public boolean isFirstTimeLaunch() {
        return sp.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setUserInformation(JSONObject response) throws JSONException {
        String firstName;
        String lastName;
        String email;
        String password;
        String destPhoneNumb;

        // get the user's first name and set in shared prefs: optional
        firstName = response.optString(FIRST_NAME);
        editor.putString(FIRST_NAME, firstName);
        // get the user's last name and set in shared prefs: optional
        lastName = response.optString(LAST_NAME);
        editor.putString(LAST_NAME, lastName);
        // get the user's phone number and set in shared prefs: mandatory so the use of getString instead of optString
        destPhoneNumb = response.getString(PHONE_NUMBER);
        editor.putString(PHONE_NUMBER, destPhoneNumb);
        // get the email and set in shared prefs
        email = response.optString(EMAIL);
        editor.putString(EMAIL, email);
        // get the cars json array convert to string and set in shared pref: again it is mandatory hence the use of get.. inst or opt..
        JSONArray cars = response.getJSONArray(CARS);
        editor.putString(CARS, cars.toString());

    }

    public String getUserFirstName() {
        return sp.getString(FIRST_NAME, "");
    }

    public String getUsersLastName() {
        return sp.getString(LAST_NAME, "");
    }

    public String getDestPhoneNumber() {
        return sp.getString(PHONE_NUMBER, "");
    }

    public String getEmail() {
        return sp.getString(EMAIL, "");
    }

    public List<Car> getCars() {

        List<Car> cars = new ArrayList<>();

        String carsJsonArrayString = sp.getString(CARS, "");

        try {

            JSONArray carsJson = new JSONArray(carsJsonArrayString);

            for (int i = 0; i < carsJson.length(); i++) {

                JSONObject carJson = carsJson.getJSONObject(i);

                cars.add(new Car(carJson.optString(CAR_MATRICULE)
                        , carJson.optString(CAR_MODEL)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return cars;
    }











}





package com.example.prosp.er24.users;

/**
 * Created by prosp on 9/24/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prosp.er24.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private static final String REGISTER_URL = "http://www.protech.co.zw/sos/user_register.php";

    //public static final String KEY_USERNAME = "username";
    //public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_ALLERGY = "allergy";
   // public static final String KEY_AGE = "age";
    public static final String KEY_BLOODTYPE = "bloodtype";
    public static final String KEY_MEDICALAID = "medicalAid";
    public static final String KEY_SUFFIX = "suffix";
    public static final String KEY_EMERGENCYCONTACT = "emergencyContact";


//preference
private TextView txtEmail;
    // private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;

    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextAddress;
    private Spinner genda;
   // private Spinner age_rang;
    private Spinner btype;
    private EditText allerg;
    private EditText medcal;
    private EditText suff;
    private EditText emergency;

    private Button buttonRegister;
    private Button btnLogin;

    //validator variables
    private Pattern pattern;
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        editTextEmail= (EditText) findViewById(R.id.editText3mail);
        editTextPhone = (EditText) findViewById(R.id.editText2);
        editTextAddress = (EditText) findViewById(R.id.editText3);
        allerg = (EditText) findViewById(R.id.allergies);
        medcal = (EditText) findViewById(R.id.med);
        suff = (EditText) findViewById(R.id.suff);
        emergency = (EditText) findViewById(R.id.editText4);
        genda = (Spinner) findViewById(R.id.gender);
       // age_rang = (Spinner) findViewById(R.id.age);
        btype = (Spinner) findViewById(R.id.spinner);

        //initialise spinner
        final String[] type=new String[]{"Select Gender","Male", "Female"};
        ArrayAdapter<String> adpterDepart= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,type);
        genda.setAdapter(adpterDepart);

        /*initialise spinner
        final String[] age_range=new String[]{"Select Age Range","7-12", "13-19", "20-30","31-40", "41-70"};
        ArrayAdapter<String> adpterAge= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,age_range);
        age_rang.setAdapter(adpterAge);*/

        //initialise spinner
        final String[] bloodtype=new String[]{"Select BloodType","A+", "B+", "AB+","AB-", "O+","O-"};
        ArrayAdapter<String> adpterBloodtype= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,bloodtype);
        btype.setAdapter(adpterBloodtype);



        buttonRegister = (Button) findViewById(R.id.button3);

        buttonRegister.setOnClickListener(this);


        //preference

        //txtName = (TextView) findViewById(R.id.name);
       // txtEmail = (TextView) findViewById(R.id.textView8);
        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());
        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
           // logoutUser();
        }
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();
        // String name = user.get("name");
        String email = user.get("email");
        // Displaying the user details on the screen
        //txtName.setText(name);
        editTextEmail.setText(email);







    }


    private void registerUser() {

        final String email = editTextEmail.getText().toString().trim();
        if (!isValidEmail(email)) {
            editTextEmail.setError("Invalid Email");
        }
        final String phone = editTextPhone.getText().toString().trim();
        if (phone.equals("")) {
            editTextPhone.setError("can't be blank");
        }
        final String address = editTextAddress.getText().toString().trim();
        if (address.equals("")) {
            editTextAddress.setError("can't be blank");
        }

        final String bloodtype = btype.getSelectedItem().toString().trim();
        //final String age = age_rang.getSelectedItem().toString().trim();
        final String gender = genda.getSelectedItem().toString().trim();

        final String allergy = allerg.getText().toString().trim();
        if (allergy.equals("")) {
            allerg.setError("can't be blank");
        }

        final String medicalAid = medcal.getText().toString().trim();
        if (medicalAid.equals("")) {
            medcal.setError("can't be blank");
        }
        final String suffix = suff.getText().toString().trim();
        if (suffix.equals("")) {
            suff.setError("can't be blank");
        }

        final String emergencyContact = emergency.getText().toString().trim();
        if (emergencyContact.equals("")) {
            emergency.setError("can't be blank");
        }


        else {


            StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                           // Toast.makeText(Register.this, error.toString(), Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put(KEY_EMAIL, email);
                     params.put(KEY_PHONE, phone);
                    params.put(KEY_ADDRESS, address);
                    params.put(KEY_GENDER, gender);
                    //params.put(KEY_AGE, age);
                    params.put(KEY_BLOODTYPE, bloodtype);
                    params.put(KEY_ALLERGY, allergy);

                    params.put(KEY_MEDICALAID, medicalAid);
                    params.put(KEY_SUFFIX, suffix);
                    params.put(KEY_EMERGENCYCONTACT, emergencyContact);
                    return params;
                }



            };




            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
            Intent log =new Intent(Register.this,Login.class);
            startActivity(log);

        }}

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String password) {
        String PASSWORD_PATTERN =
                "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
       /* if (password != null && password.length() > 6) {
            return true;
        }
        return false;*/
    }






    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();

        }

    }

}
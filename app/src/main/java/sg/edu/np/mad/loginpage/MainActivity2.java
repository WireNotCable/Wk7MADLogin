package sg.edu.np.mad.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// Create account page
public class MainActivity2 extends AppCompatActivity {
    String title = "Main Activity 2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
/*
    public String GLOBAL_PREFS = "myPrefs";
    public String MY_USERNAME = "MyUserName";
    public String MY_PASSWORD = "MyPassword";
    SharedPreferences sharedPreferences;*/

    @Override
    protected void onStart(){
        super.onStart();
        Log.v(title, "Create new user page");
        EditText etUsername = findViewById(R.id.editTextText3);
        EditText etPassword = findViewById(R.id.editTextText4);

        Button createButton = findViewById(R.id.button2);
        Button cancelButton = findViewById(R.id.button3);

        //create user in shared preference
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(MY_USERNAME, etUsername.getText().toString());
//                editor.putString(MY_PASSWORD, etPassword.getText().toString());
//                editor.commit();
//                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
//                startActivity(intent);

                UserData userData = dbHandler.findUser(etUsername.getText().toString());
                if(userData == null){
                    String dbUsername = etUsername.getText().toString();
                    String dbPassword = etPassword.getText().toString();
                    UserData dbUserData = new UserData(dbUsername, dbPassword);
                    dbHandler.addUser(dbUserData);
                }
            }
        });

        // go back to login page if user cancels
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
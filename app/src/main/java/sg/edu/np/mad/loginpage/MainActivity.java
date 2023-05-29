package sg.edu.np.mad.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//Login Page
public class MainActivity extends AppCompatActivity {
    String title = "Main Activity";

    private TextView newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v(title,"Starting App");

        newUser = findViewById(R.id.textView4);
        newUser.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return false;
            }
        });

        Button loginButton = findViewById(R.id.button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etUsername = findViewById(R.id.editTextText);
                EditText etPassword = findViewById(R.id.editTextText2);

                if (isValidCredential(etUsername.getText().toString(),etPassword.getText().toString())){
                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid Username/ Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
/*
    public String GLOBAL_PREFS = "myPrefs";
    public String MY_USERNAME = "MyUserName";
    public String MY_PASSWORD = "MyPassword";
    SharedPreferences sharedPreferences;*/

    MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
    public boolean isValidCredential(String username, String password){
     /*   sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        String sharedUsername = sharedPreferences.getString(MY_USERNAME, "");
        String sharePassword = sharedPreferences.getString(MY_PASSWORD, "");

        if (sharedUsername.equals(username) && sharePassword.equals(password)){
            return true;
        }
        return false; */

        UserData dbData = dbHandler.findUser(username);
        if(dbData.getUsername().equals(username) && dbData.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
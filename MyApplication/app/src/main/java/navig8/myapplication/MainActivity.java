package navig8.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button answerYesButton, answerNoButton;
    private EditText usersNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerYesButton = (Button) findViewById(R.id.answerYesButton);
        answerNoButton = (Button) findViewById(R.id.answerNoButton);
        usersNameEditText = (EditText) findViewById(R.id.usersNameEditText);

    }


    public void onYesButtonClick(View view) {

        String usersName = String.valueOf(usersNameEditText.getText());

        String yourYesResponse = "That is great " + usersName;

        Toast.makeText(this, yourYesResponse, Toast.LENGTH_SHORT).show();

    }

    public void onNoButtonClick(View view) {
        String usersName = String.valueOf(usersNameEditText.getText());

        String yourNoResponse = "That's a shame " + usersName;

        Toast.makeText(this, yourNoResponse, Toast.LENGTH_LONG).show();

    }
}

package database.shankar.com.databasedemo;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseManager databaseManager;
    private Button insertButton;

    private EditText nameEditText;
    private EditText lnameEditText;
       private TextView dataTextView;


    //in the mainactivity initilizing two edittext and a button when i click on insert button the values gets stored in the database dmp
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManager = new DatabaseManager(this);

        insertButton = (Button) findViewById(R.id.insertButton);

        insertButton.setOnClickListener(this);

        nameEditText = (EditText) findViewById(R.id.name);
        lnameEditText = (EditText) findViewById(R.id.lname);


       //this is used for seeing the  inserted output result
        dataTextView = (TextView) findViewById(R.id.outputty);



    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.insertButton:
                String name = nameEditText.getText().toString();
                String lname = lnameEditText.getText().toString();
                databaseManager.open(false);
                databaseManager.insertEmployee(name, lname);
                Toast.makeText(getApplicationContext(), "1 row updated", Toast.LENGTH_SHORT).show();

                // databaseManager.close();

                nameEditText.setText("");
                lnameEditText.setText("");


                // cursor i used to iterate through the table rows
                Cursor cursor = databaseManager.getEmployees();


                StringBuilder stringBuilder = new StringBuilder();

                do {
                    String nameData = cursor.getString(cursor.getColumnIndex(AcadGildDatabaseOpenHelper.NAME));
                    String lnameData = cursor.getString(cursor.getColumnIndex(AcadGildDatabaseOpenHelper.LNAME));
                    String idInt = String.valueOf(cursor.getInt(cursor.getColumnIndex(AcadGildDatabaseOpenHelper._ID)));
                    stringBuilder.append("ID: " + idInt + " Name: " + nameData + " LNAME: " + lnameData + '\n');

                } while (cursor.moveToNext());

                cursor.close();
                databaseManager.close(); //after performing insert operation close the data base


                dataTextView.setText(stringBuilder.toString());  //we can see the after appending the values
                break;


            // break;

        }


    }

}


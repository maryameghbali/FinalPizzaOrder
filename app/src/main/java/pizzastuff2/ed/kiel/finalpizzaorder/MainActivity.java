package pizzastuff2.ed.kiel.finalpizzaorder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btnOrder;
    private EditText name;
    private EditText address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View getStartedButton = findViewById(R.id.btn_Order);
        getStartedButton.setOnClickListener(this);
        name = (EditText)findViewById(R.id.name);
        address = (EditText)findViewById(R.id.address);


    }
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_Order:
                    {
                        //Toast.makeText(this, "Order Pizza", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this, OrderPizza.class);
                        i.putExtra("name", name.getText().toString());
                        i.putExtra("address", address.getText().toString());
                        startActivity(i);


                    }
                    break;
                // More buttons go here (if any) ...
            }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                return true;
            case R.id.contact:
                 startActivity(new Intent(MainActivity.this, Contact.class));
                return true;
            case R.id.help:
                new AlertDialog.Builder(this).setTitle("Help").setMessage("Enter your Name and postal address, then choose your favorite pizza,it will deliver in 30 minutes  ").setCancelable(false)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                return true;
            case R.id.exit:
                new AlertDialog.Builder(this).setTitle("Exit").setMessage("Are you sure you want to exit?").setCancelable(true)
                        .setNeutralButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                return true;
        }
        return false;
    }

}

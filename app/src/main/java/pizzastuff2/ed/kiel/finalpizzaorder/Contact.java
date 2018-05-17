package pizzastuff2.ed.kiel.finalpizzaorder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Contact extends AppCompatActivity  implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        View btnBack = findViewById(R.id.btn_googlemap);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_googlemap: {
                startActivity(new Intent(this, MapsActivity.class));
                break;
            }

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
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.contact:
                startActivity(new Intent(Contact.this, Contact.class));
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

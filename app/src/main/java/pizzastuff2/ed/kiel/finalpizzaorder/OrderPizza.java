package pizzastuff2.ed.kiel.finalpizzaorder;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class OrderPizza  extends AppCompatActivity implements View.OnClickListener {

    private ViewFlipper flip;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_pizza);
        View btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        View btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        View btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        View btnOrder = findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(this);
        View btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(this);
        View btnCancel2 = findViewById(R.id.btnCancel2);
        btnCancel2.setOnClickListener(this);
        flip=(ViewFlipper)findViewById(R.id.flip);

        //when a view is displayed
     //   flip.setInAnimation(this,android.R.anim.fade_in);
        //when a view disappears
      //  flip.setOutAnimation(this, android.R.anim.fade_out);
    }
    @Override
    public void onClick(View v)
    {

        switch (v.getId()) {
            case R.id.btnNext:
            {
                flip.showNext();
                break;
            }
            case R.id.btnBack:
                {
                    flip.showPrevious();
                    break;
            }
            case R.id.btnCancel:
            {
                Toast.makeText(this, "Cancel Order", Toast.LENGTH_LONG).show();
                finish();
                break;
            }
            case R.id.btnOrder:
            {
                generateSummary();
                break;
            }
            case R.id.btnCancel2:
            {
                Toast.makeText(this, "Cancel Order", Toast.LENGTH_LONG).show();
                finish();
                break;
            }
            case R.id.btnConfirm:
            {
                Toast.makeText(this, "Thank you for your order", Toast.LENGTH_LONG).show();
                purchaseConfirm();
                // finish();
                break;
            }

            // More buttons go here (if any) ...
        }


    }
    private void generateSummary()
    {
        String valueCrust="";
        String valuePizza="";
        StringBuffer valueTopping = new StringBuffer();
        String name="",address="";
        StringBuffer result = new StringBuffer();

        flip.showNext();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            name = bundle.getString("name");
            address = bundle.getString("address");
        }
        result.append("Name: ").append(name).append("\n");
        result.append("Delivery Address: ").append(address).append("\n\n--------------------\n\n");

        rg = (RadioGroup) findViewById(R.id.rg_crust);
        int selectedId = rg.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        valueCrust = radioButton.getText().toString();
        rg = (RadioGroup) findViewById(R.id.rg_pizza);
        selectedId = rg.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        valuePizza = radioButton.getText().toString();
        CheckBox ch = (CheckBox) findViewById(R.id.ch_mushrooms);
        if ((ch).isChecked())
        {
            valueTopping.append(ch.getText().toString()).append("   ");
        }
         ch = (CheckBox) findViewById(R.id.ch_spinach);
        if ((ch).isChecked())
        {
            valueTopping.append(ch.getText().toString()).append("   ");
        }
         ch = (CheckBox) findViewById(R.id.ch_olive);
        if ((ch).isChecked())
        {
            valueTopping.append(ch.getText().toString()).append("   ");
        }
         ch = (CheckBox) findViewById(R.id.ch_peper);
        if ((ch).isChecked())
        {
            valueTopping.append(ch.getText().toString()).append("   ");
        }


        result.append("Selected Pizza : ").append(valuePizza);
        result.append("\nCrust : ").append(valueCrust);
        result.append("\nTopping : ").append(valueTopping);

        TextView txtSummary = (TextView)findViewById(R.id.txtSummary);
        txtSummary.setText(result);

    }
    private void purchaseConfirm()
    {
        new SendMail().execute("");

    }
    private class SendMail extends AsyncTask<String, Integer, Void> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(OrderPizza.this, "Please wait", "Sending mail", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        protected Void doInBackground(String... params) {
            Mail m = new Mail("Maryam.eghbali2015@gmail.com", "password");

            String[] toArr = {"Maryam.eghbali2015@gmail.com"};
            m.setTo(toArr);
            m.setFrom("Maryam.eghbali2015@gmail.com");
            m.setSubject("This is an email sent using my Mail JavaMail wrapper from an Android device.");
            m.setBody("Email body.");

            try {
                if(m.send()) {
                    Toast.makeText(OrderPizza.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(OrderPizza.this, "Email was not sent.", Toast.LENGTH_LONG).show();
                }
            } catch(Exception e) {
                Log.e("MailApp", "Could not send email", e);
            }
            return null;
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
                startActivity(new Intent(OrderPizza.this, Contact.class));
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

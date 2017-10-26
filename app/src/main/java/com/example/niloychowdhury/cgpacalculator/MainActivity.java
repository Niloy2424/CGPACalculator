package com.example.niloychowdhury.cgpacalculator;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niloychowdhury.cgpacalculator.Model.Result;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private TextView finalCGPATV;
    private EditText gpaET;
    private EditText creditET;
    private ListView gpaCalCalcutionList;
    ArrayList<Result>results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        finalCGPATV= (TextView) findViewById(R.id.finalCGPATV);
        gpaET= (EditText) findViewById(R.id.gpaET);
        creditET= (EditText) findViewById(R.id.creditET);
        gpaCalCalcutionList= (ListView) findViewById(R.id.gpaCalCalcutionList);
        results= new ArrayList<>();
       gpaCalCalcutionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
               final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
               View v = getLayoutInflater().inflate(R.layout.dialog_layout,null);
               final EditText creditEtDialog= (EditText) v.findViewById(R.id.creditETDialog);
               final EditText gpaETDialog= (EditText) v.findViewById(R.id.gpaETDialog);
               Button updateButton= (Button) v.findViewById(R.id.updateButton);
               Button cancelButton= (Button) v.findViewById(R.id.cancelButton);
               TextView positionTV= (TextView) v.findViewById(R.id.positionTVForDialog);
               creditEtDialog.setText(String.valueOf(results.get(position).getCredit()));
               gpaETDialog.setText(String.valueOf(results.get(position).getGpa()));
               positionTV.setText(String.valueOf(position+1));
               builder.setView(v);
               final AlertDialog dialog=builder.create();
               dialog.show();
               updateButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       double credit=Double.valueOf(creditEtDialog.getText().toString());
                       double gpa=Double.valueOf(gpaETDialog.getText().toString());
                       if (credit<=4.00 && gpa<=4.00)
                       {
                           double total = credit*gpa;
                           results.get(position).setGpa(gpa);
                           results.get(position).setCredit(credit);
                           results.get(position).setTotal(total);
                           CaluculateFinalCGpa();
                           dialog.cancel();
                       }
                       else
                       {
                           Toast.makeText(MainActivity.this, "Credit And GPA Can't Exceed 4.00", Toast.LENGTH_SHORT).show();
                       }

                   }

               });
               cancelButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialog.dismiss();
                   }
               });
              /* builder.setView(v);
               AlertDialog dialog=builder.create();
               dialog.show();*/

           }
       });

    }


    public void addButton(View view) {

        if ((!creditET.getText().toString().trim().isEmpty())||(!gpaET.getText().toString().trim().isEmpty()))
        {

            double credit=Double.valueOf(creditET.getText().toString());
            double gpa=Double.valueOf(gpaET.getText().toString());
            if (credit<=4.00 && gpa<=4.00)
            {
                double total = (credit*gpa);
                Result aResult=new Result(credit,gpa,total);
                results.add(aResult);
                ResultAdapter adapter=new ResultAdapter(this,results);
                gpaCalCalcutionList.setAdapter(adapter);
            }

            else
            {
                Toast.makeText(this, "CREDIT And GPA can't Exceed 4.00 ", Toast.LENGTH_SHORT).show();
            }

            CaluculateFinalCGpa();
        }
  else
        {
            Toast.makeText(this, "Please Fill All Requirment Correctly", Toast.LENGTH_SHORT).show();
        }



    }

    private void CaluculateFinalCGpa() {
        double TCGPA=0;
        double tCredit=0;
        for (Result cgpa : results
                ) {
            TCGPA =cgpa.getTotal()+TCGPA;
            tCredit=cgpa.getCredit()+tCredit;
        }
       // int element=results.size();
        double finalCGPA=TCGPA/tCredit;
      //  Toast.makeText(this, ""+element, Toast.LENGTH_SHORT).show();
        finalCGPATV.setText(new DecimalFormat("##.##").format(finalCGPA));

    }


}


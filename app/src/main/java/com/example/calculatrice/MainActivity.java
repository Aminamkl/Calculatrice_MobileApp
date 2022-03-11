package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.txt_out);


        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.result).equals(result.getText().toString())){
                    result.setText("");
                }
            }
        });
    }

    private void updateText(String txtToAdd){
        String oldtxt=result.getText().toString();
        int cursorPos=result.getSelectionStart();
        String leftxt=oldtxt.substring(0, cursorPos);
        String righttxt=oldtxt.substring( cursorPos);
        if(getString(R.string.result).equals(result.getText().toString())){
            result.setText(txtToAdd);
            result.setSelection(cursorPos+1);
        }
        else {
            result.setText(String.format("%s%s%s", leftxt,txtToAdd,righttxt));
            result.setSelection(cursorPos+1);
        }

    }

    public void zeroBTN(View v){
        updateText("0");
    }
    public void oneBTN(View v){
        updateText("1");
    }
    public void twoBTN(View v){
        updateText("2");
    }
    public void threeBTN(View v){
        updateText("3");
    }
    public void fourBTN(View v){
        updateText("4");
    }
    public void fiveBTN(View v){
        updateText("5");
    }
    public void sixBTN(View v){
        updateText("6");
    }
    public void sevenBTN(View v){
        updateText("7");
    }
    public void eightBTN(View v){
        updateText("8");
    }
    public void nineBTN(View v){
        updateText("9");
    }
    public void acBTN(View v){
        result.getText().clear();
    }
    public void plusBTN(View v){
        updateText("+");
    }
    public void substractBTN(View v){ updateText("-");}
    public void divideBTN(View v){
        updateText("÷");
    }
    public void multiplyBTN(View v){
        updateText("*");
    }
    public void enterBTN(View v){
        String userExp = result.getText().toString();
        userExp=userExp.replaceAll("÷","/");
        Expression exp=new Expression(userExp);
        String rslt= String.valueOf(exp.calculate());
        result.setText(rslt);
        result.setSelection(result.length());
    }
    public void puissBTN(View v){
        updateText("^");
    }
    public void parBTN(View v){
        int cursorPos= result.getSelectionStart();
        int openPar =0;
        int closedPar= 0;
        int textLen=result.getText().length();
        for(int i=0; i<cursorPos; i++){
            if(result.getText().toString().substring(i, i+1).equals("(")){
                openPar +=1;
            }
            if(result.getText().toString().substring(i, i+1).equals(")")){
                closedPar +=1;
            }
        }

        if(openPar==closedPar || result.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
        }

        else if(closedPar < openPar && !result.getText().toString().substring(textLen-1,textLen).equals(")")){
            updateText(")");
        }
        result.setSelection(cursorPos +1);
    }
    public void dotBTN(View v){
        updateText(".");
    }

    public void backspaceBTN(View v){
        int cursorPos= result.getSelectionStart();
        int textLen=result.getText().length();
        if(cursorPos !=0 && textLen !=0){
            SpannableStringBuilder selection= (SpannableStringBuilder) result.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            result.setText(selection);
            result.setSelection(cursorPos - 1);
        }
    }

}

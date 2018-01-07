package com.mkgroupe.root.coolcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String valeus;
    boolean lastBtnIsOperation;
    ArrayList<String> operation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valeus="";
        lastBtnIsOperation = true;
        operation = new ArrayList<>();
        Button btn0 = findViewById(R.id.num0);
        Button btn1 = findViewById(R.id.num1);
        Button btn2 = findViewById(R.id.num2);
        Button btn3 = findViewById(R.id.num3);
        Button btn4 = findViewById(R.id.num4);
        Button btn5 = findViewById(R.id.num5);
        Button btn6 = findViewById(R.id.num6);
        Button btn7 = findViewById(R.id.num7);
        Button btn8 = findViewById(R.id.num8);
        Button btn9 = findViewById(R.id.num9);

        ImageButton addIBtn = findViewById(R.id.add_button);
        ImageButton minusIBtn = findViewById(R.id.minus_button);
        ImageButton divIBtn = findViewById(R.id.div_button);
        ImageButton multIBtn = findViewById(R.id.mult_button);
        ImageButton equalIBtn = findViewById(R.id.equal_button);

        final Button clearIBtn = findViewById(R.id.clear_button);
        final TextView result_TV = findViewById(R.id.result_TV);
        final TextView expression_TV = findViewById(R.id.expressionTV);

        View.OnClickListener onClickNum = new View.OnClickListener() {
            //ArrayList<String> op = new ArrayList<>();
            @Override
            public void onClick(View view) {
                Button b = (Button) view;

                if(result_TV.getText().toString().equals("0") || lastBtnIsOperation)
                {
                    //result_TV.setText(b.getText().toString());
                    valeus = b.getText().toString();
                    lastBtnIsOperation = false;
                    // op.clear();
                    // op.addAll(operation);
                    // op.add(valeus);
                }
                else
                {
                    //result_TV.append(b.getText().toString());
                    valeus += b.getText().toString();
                    //if(op.size()>0)
                    //{
                    //    Log.d("med", "onClick: num"+op.toString());
                    //    op.set(op.size()-1,valeus);
                    //}

                }
                expression_TV.append(b.getText().toString());
                //result_TV.setText(String.valueOf(calculate(op)));
            }
        };

        addIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!lastBtnIsOperation) {
                    //operation.add(result_TV.getText().toString());
                    operation.add(valeus);
                    operation.add("+");
                }
                else
                {
                    int lastId = operation.size()-1;
                    if(!operation.get(lastId).equals("+"))
                    {
                        operation.set(lastId,"+");
                    }
                }
                expression_TV.setText(arrayToString(operation));
                lastBtnIsOperation = true;
            }
        });

        minusIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!lastBtnIsOperation) {
                    //operation.add(result_TV.getText().toString());
                    operation.add(valeus);
                    operation.add("-");
                }
                else
                {
                    int lastId = operation.size()-1;
                    if(!operation.get(lastId).equals("-"))
                    {
                        operation.set(lastId,"-");
                    }
                }
                expression_TV.setText(arrayToString(operation));
                lastBtnIsOperation = true;
            }
        });

        multIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!lastBtnIsOperation) {
                    //operation.add(result_TV.getText().toString());
                    operation.add(valeus);
                    operation.add("*");
                }
                else
                {
                    int lastId = operation.size()-1;
                    if(!operation.get(lastId).equals("*"))
                    {
                        operation.set(lastId,"*");
                    }
                }
                expression_TV.setText(arrayToString(operation));
                lastBtnIsOperation = true;
            }
        });

        divIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!lastBtnIsOperation) {
                    //operation.add(result_TV.getText().toString());
                    operation.add(valeus);
                    operation.add("/");
                }
                else
                {
                    int lastId = operation.size()-1;
                    if(!operation.get(lastId).equals("/"))
                    {
                        operation.set(lastId,"/");
                    }
                }
                expression_TV.setText(arrayToString(operation));
                lastBtnIsOperation = true;
            }
        });

        equalIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastBtnIsOperation) {
                    operation.remove(operation.size()-1);
                }
                else
                {
                    Log.d("med", "onClick: not last button");
                    //operation.add(result_TV.getText().toString());
                    operation.add(valeus);
                }
                int res = calculate(operation);
                result_TV.setText(String.valueOf(res));
                valeus = String.valueOf(res);
                operation.clear();
                //operation.add(String.valueOf(res));
                Log.d("med", "onClick: ="+operation.toString());


                lastBtnIsOperation = false;
            }
        });


        clearIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result_TV.setText("0");
                valeus = "0";
                operation.clear();
                expression_TV.setText(arrayToString(operation));
            }
        });



        btn0.setOnClickListener(onClickNum);
        btn1.setOnClickListener(onClickNum);
        btn2.setOnClickListener(onClickNum);
        btn3.setOnClickListener(onClickNum);
        btn4.setOnClickListener(onClickNum);
        btn5.setOnClickListener(onClickNum);
        btn6.setOnClickListener(onClickNum);
        btn7.setOnClickListener(onClickNum);
        btn8.setOnClickListener(onClickNum);
        btn9.setOnClickListener(onClickNum);

    }
    private int calculate(ArrayList<String> operation)
    {
        Log.d("med", "clculate: "+operation.toString());
        if(operation.size()==0)
            return 0;
        if(operation.size() == 1)
            return Integer.parseInt(operation.get(0));

        while (operation.contains("*") || operation.contains("/"))
        {
            int mult = operation.indexOf("*");
            int div = operation.indexOf("/");
            if((mult < div && mult != -1)||div==-1)
            {
                int val1 = Integer.parseInt(operation.get(mult-1));
                int val2 = Integer.parseInt(operation.get(mult+1));
                int res = val1*val2;
                operation.set(mult-1,String.valueOf(res));
                operation.remove(mult+1);
                operation.remove(mult);
            }
            else if((mult > div && div != -1)||mult==-1)
            {
                int val1 = Integer.parseInt(operation.get(div-1));
                int val2 = Integer.parseInt(operation.get(div+1));
                int res = val1/val2;
                operation.set(div-1,String.valueOf(res));
                operation.remove(div+1);
                operation.remove(div);
            }
        }

        while (operation.contains("+") || operation.contains("-"))
        {
            int add = operation.indexOf("+");
            int minus = operation.indexOf("-");
            if((add < minus && add != -1)||minus==-1)
            {
                int val1 = Integer.parseInt(operation.get(add-1));
                int val2 = Integer.parseInt(operation.get(add+1));
                Log.d("med", "clculate: "+val1+" "+val2);
                int res = val1+val2;
                operation.set(add-1,String.valueOf(res));
                operation.remove(add+1);
                operation.remove(add);
            }
            else if((add > minus && minus != -1)||add==-1)
            {
                int val1 = Integer.parseInt(operation.get(minus-1));
                int val2 = Integer.parseInt(operation.get(minus+1));
                int res = val1-val2;
                operation.set(minus-1,String.valueOf(res));
                operation.remove(minus+1);
                operation.remove(minus);
            }
        }
        if(operation.size()>1)
            Log.d("med", "clculate: Errors"+ operation.size());
        return  Integer.parseInt(operation.get(0));

    }
    private String arrayToString(ArrayList<String> list)
    {
        String str="";
        if(list.size()==0)
            return "";
        int i =0;
        while(i<list.size())
        {
            if(list.get(i) == "*")
                str+="x";
            else
                str+= list.get(i);
            i++;
        }
        return str;
    }
}

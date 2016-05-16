package com.example.symboliccomputation;



import com.example.symboliccomputation.R;

import semat2.parse.Parser;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SymbolicComputation extends Activity {
	
	EditText str1,str2;
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symbolic_computation);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        str1 = (EditText) findViewById(R.id.vr1);
        str2 = (EditText) findViewById(R.id.vr2);
        
    }
   
    public void next(View v){
    	String st1 = str1.getText().toString();
        Parser p=new Parser(st1);
		try
		{
			String str=p.parse();
	        str2.setText(str);
		}
		catch(Exception E)
		{
			str2.setText("Invalid Expression");
		}
        
    }
    public void server_send(View v){
        String st1 = str1.getText().toString();
		try
		{
			new Server(this.getApplication(),str2).execute(st1);
		}
		catch(Exception E)
		{
			str2.setText("Connection Failed");
		}
        
    }
    public void add_indefint(View v){
    	String st1="indefint()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_defint(View v){
    	String st1="defint[,]()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_combi(View v){
    	String st1="combi[,]";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_permu(View v){
    	String st1="permu[,]";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_diff(View v){
    	String st1="deriv[]()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_expo(View v){
    	String st1="2.718";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_ln(View v){
    	String st1="ln()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_logb(View v){
    	String st1="logb[]()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_nroot(View v){
    	String st1="nroot[]()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_sroot(View v){
    	String st1="sroot()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_product(View v){
    	String st1="prod[,,]()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_sum(View v){
    	String st1="sum[,,]()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_cosi(View v){
    	String st1="icos()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_coseci(View v){
    	String st1="icosec()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_coti(View v){
    	String st1="icot()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_sini(View v){
    	String st1="isin()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_tani(View v){
    	String st1="itan()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_cosech(View v){
    	String st1="cosech()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_cosh(View v){
    	String st1="cosh()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_coth(View v){
    	String st1="coth()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_sinh(View v){
    	String st1="sinh()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_tanh(View v){
    	String st1="tanh()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_seci(View v){
    	String st1="isec()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    public void add_sech(View v){
    	String st1="sech()";
    	int start =str1.getSelectionStart();
    	str1.getText().insert(start, st1);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
}

package com.example.symboliccomputation;

import com.example.symboliccomputation.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SymbolicComputation(View v)
    {
    	Intent intent= new Intent(this,com.example.symboliccomputation.SymbolicComputation.class);
    	startActivity(intent);
    }
    
    public void aboutUs(View v)
    {
    	Intent intent= new Intent(this, com.example.symboliccomputation.AboutUs.class);
    	startActivity(intent);
    }
    public void help(View v)
    {
    	Intent intent= new Intent(this, com.example.symboliccomputation.Help.class);
    	startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

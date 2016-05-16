package com.example.symboliccomputation;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

public class Server extends AsyncTask<String,Void,String>{
	private EditText res1;
	   public Server(Context context, EditText res) {
	      this.res1 = res;
	   }

	   protected void onPreExecute(){
		   this.res1.setText("Sending....");
	   }
	   protected void onPostExecute(String result){
	      this.res1.setText(result);
	   }

	@Override
	protected String doInBackground(String... arg0) {
		try{
         /*   String n1 = (String)arg0[0];
            String link = "http://10.0.2.2/SCserver.php?num1="+n1;
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);
            */
			String equation=arg0[0];
			String link="http://192.168.193.1/ShanaulHaque/SCserver.php";
            String data  = URLEncoder.encode("equation", "UTF-8") 
            + "=" + URLEncoder.encode(equation, "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection(); 
            conn.setDoOutput(true); 
            OutputStreamWriter wr = new OutputStreamWriter
            (conn.getOutputStream()); 
            wr.write( data ); 
            wr.flush(); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Read Server Response      
            while((line = reader.readLine()) != null)
            {
               sb.append(line);
               break;
            }
            return sb.toString();
      }catch(Exception e){	
    	 return new String("Connection Failed.");
        // return new String("Exception: " + e.getMessage());
     }
	}	
}

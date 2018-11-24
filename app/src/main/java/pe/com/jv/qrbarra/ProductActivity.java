package pe.com.jv.qrbarra;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ProductActivity extends AppCompatActivity {

    Button myBtnNewProduct, myBtnSalir;
    TextView nameProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        myBtnNewProduct = (Button)findViewById(R.id.btnNuevoProducto);
        myBtnSalir = (Button)findViewById(R.id.btnSalir);

        myBtnNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        myBtnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nameProd = (TextView) findViewById(R.id.productName);
        getData();
    }

    public void getData(){
        String idProd = getIntent().getStringExtra("codigoX");
        String baseUrl = "https://restcountries.eu/rest/v2/name/" + idProd;
        String baseUrlAll = "https://restcountries.eu/rest/v2/all";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection connection;


            try {
                url = new URL(baseUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String  inputLine;

                StringBuffer response = new StringBuffer();
                String json = "";

                while ((inputLine = in.readLine()) != null ) {
                    response.append(inputLine);
                }

                json = response.toString();
                JSONArray jsonArray = null;
                jsonArray = new JSONArray(json);
                String nameP = "";
                for(int i = 0 ; i<jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //Log.d("SALIDA ", jsonObject.optString("name"));
                    nameP += "Salida: " + i + "  " + jsonObject.optString("name") + "\n" + "Capital: " + jsonObject.optString("capital")                   ;
                }
                nameProd.setText(nameP);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


    }
}

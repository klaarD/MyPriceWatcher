/**
 * My Price Watcher --- program to watch prices over one specified item
 * @author Klara Dvorakova
 *
 */

package edu.utep.cs.cs4330.mypricewatcher.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.utep.cs.cs4330.mypricewatcher.Model.Item;
import edu.utep.cs.cs4330.mypricewatcher.R;
//AppCompatActivity
public class MainActivity extends AppCompatActivity {

    public String Source = "https://www.amazon.com/Roadmaster-Mens-Granite-Peak-Bike/dp/B07595ZKJ7?ref_=Oct_BSellerC_1265458011_1&pf_rd_p=7d71bf68-7381-557e-9029-f80176f72fa9&pf_rd_s=merchandised-search-6&pf_rd_t=101&pf_rd_i=1265458011&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=MCTWQ50N5B1GHARSP65X&pf_rd_r=MCTWQ50N5B1GHARSP65X&pf_rd_p=7d71bf68-7381-557e-9029-f80176f72fa9";

    private static final String STATE_ITEM = "items";


    private TextView InitialPrice; // textView to store InitialPrice of the item
    private TextView CurrentPrice; // textView to store and set CurrentPrice of the item
    private TextView PriceChange; // textView to store and set PriceChange of the item
    private EditText SourceText;  // EditText to store and set source of the item
    private Button BtnCalculate; // Button used to calculate new current price of the item
    private Button BtnWeb;
    private ImageView image;
    private ArrayList<Item> items; // list of the items - just one item

    /**
     * Method Sets initial values to the variable
     * Displays set values
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String action =getIntent().getAction();
        String type = getIntent().getType();
        if (Intent.ACTION_SEND.equalsIgnoreCase(action) && type != null && ("text/plain".equals(type))){
            Source  = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        }

        setContentView(R.layout.activity_main);

        InitialPrice = findViewById(R.id.textViewInitial);
        CurrentPrice = findViewById(R.id.textViewCurrent);
        PriceChange = findViewById(R.id.textViewChange);
        SourceText = findViewById(R.id.editTextSource);
        SourceText.setText(Source);

        image = findViewById(R.id.imageView);
        image.setVisibility(View.VISIBLE);

        BtnWeb = findViewById(R.id.btnWeb);
        BtnWeb.setOnClickListener(this::WebClicked);

        BtnCalculate = findViewById(R.id.buttonCalculate);
        BtnCalculate.setOnClickListener(this::CalculateClicked);

        if (savedInstanceState != null) {
            items = (ArrayList<Item>) savedInstanceState.getSerializable(STATE_ITEM);
        }
        else {
            items = new ArrayList<>();
            items.add(new Item(Source));
        }
        SetPrices();
    }

    /**
     * Method saves items so the app can continue with the same values
     * when the rotation of the display is changed
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STATE_ITEM, items);
    }

    /**
     * Method sets variables InitialPrice, CurrentPrice and PriceChange
     * sets them to current value of the values saved in Item instance
     */
    private void SetPrices(){
        InitialPrice.setText("Initial Price: $" + items.get(0).getInitialPrice());
        CurrentPrice.setText("Current Price: $" + items.get(0).getCurrentPrice());


        if(items.get(0).ChangePositive()) {
            PriceChange.setTextColor(Color.rgb(200, 0, 0));
            PriceChange.setText("Price change: +" + items.get(0).getChangePrice() + "%");
        }
        else {
            PriceChange.setTextColor(Color.rgb(0,200,0));
            PriceChange.setText("Price change: " + items.get(0).getChangePrice() + "%");
        }
    }

    /**
     * Method calculates new CurrentPrice of the item
     * @param view
     */
    protected void CalculateClicked(View view){
        items.get(0).CalculateNewPrice();
        SetPrices();
    }

    protected void WebClicked(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Source));
        startActivity(browserIntent);
    }

}

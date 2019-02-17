/**
 * My Price Watcher --- program to watch prices over one specified item
 * @author Klara Dvorakova
 *
 */

package edu.utep.cs.cs4330.mypricewatcher;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String Source = "amazon.com/mountain_bike";

    private static final String STATE_ITEM = "items";


    private TextView InitialPrice;
    private TextView CurrentPrice;
    private TextView PriceChange;
    private EditText SourceText;
    private Button BtnCalculate;

    private ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String action =getIntent().getAction();
        String type = getIntent().getType();
        if (Intent.ACTION_SEND.equalsIgnoreCase(action) && type != null && ("text/plain".equals(type))){
            String url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            Source  = url;
        }

        setContentView(R.layout.activity_main);

        InitialPrice = findViewById(R.id.textViewInitial);
        CurrentPrice = findViewById(R.id.textViewCurrent);
        PriceChange = findViewById(R.id.textViewChange);
        SourceText = findViewById(R.id.editTextSource);
        SourceText.setText(Source);

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
        outState.putSerializable(STATE_ITEM, items);
    }

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

    protected void CalculateClicked(View view){
        items.get(0).CalculateNewPrice();
        SetPrices();
    }

}

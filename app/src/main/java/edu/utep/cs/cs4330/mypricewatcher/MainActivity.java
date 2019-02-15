package edu.utep.cs.cs4330.mypricewatcher;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String Source = "amazon.com/mountain_bike";
    private TextView InitialPrice;
    private TextView CurrentPrice;
    private TextView PriceChange;
    private EditText SourceText;
    private Button BtnCalculate;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitialPrice = findViewById(R.id.textViewInitial);
        CurrentPrice = findViewById(R.id.textViewCurrent);
        PriceChange = findViewById(R.id.textViewChange);
        SourceText = findViewById(R.id.editTextSource);
        SourceText.setText(Source);

        BtnCalculate = findViewById(R.id.buttonCalculate);
        BtnCalculate.setOnClickListener(this::CalculateClicked);
        item = new Item(Source);


        SetPrices();
    }

    private void SetPrices(){
        InitialPrice.setText("Initial Price: $" + item.getInitialPrice());
        CurrentPrice.setText("Current Price: $" + item.getCurrentPrice());


        if(item.ChangePositive()) {
            PriceChange.setTextColor(Color.rgb(200, 0, 0));
            PriceChange.setText("Price change: +" + item.getChangePrice() + "%");
        }
        else {
            PriceChange.setTextColor(Color.rgb(0,200,0));
            PriceChange.setText("Price change: " + item.getChangePrice() + "%");
        }
    }

    protected void CalculateClicked(View view){
        item.CalculateNewPrice();
        SetPrices();
    }


}

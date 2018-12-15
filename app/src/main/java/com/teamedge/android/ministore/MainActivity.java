package com.teamedge.android.ministore;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    int numberOfHoodies = 0;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {

        CheckBox figurineCheckbox = (CheckBox) findViewById(R.id.figurine);
        boolean hasFigurine = figurineCheckbox.isChecked();
        CheckBox keychainCheckbox = (CheckBox) findViewById(R.id.keychain);
        boolean hasKeychain = keychainCheckbox.isChecked();
        CheckBox animeCheckbox = (CheckBox) findViewById(R.id.anime);
        boolean hasAnime = animeCheckbox.isChecked();
        EditText nameEditText = (EditText) findViewById(R.id.name);


        displayQuantity(numberOfHoodies);
        int price = calculatePrice(hasFigurine, hasKeychain, hasAnime);
        displayPrice(price);



        String orderMessage = "Name: " + nameEditText.getText().toString() + "\n" +
                "Add Figurine?" + hasFigurine + "\n" +
                "Add Keychain?" + hasKeychain + "\n" +
                "Add Anime Bundle?" + hasAnime + "\n" +
                "Number of Hoodies: " + numberOfHoodies + "\n" +
                "Total: $" + price;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "FairyTail Hoodie あなたのためのアニメ Order");
        intent.putExtra(Intent.EXTRA_TEXT, orderMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
    }


    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }


    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("$" + number);


    }


    public void increase(View view) {
        numberOfHoodies++;
        displayQuantity(numberOfHoodies);
        displayPrice(numberOfHoodies * 20);
    }


    public void decrease(View view) {
        if(numberOfHoodies <=0) {
            return;
        }
        numberOfHoodies --;
        displayQuantity(numberOfHoodies);
        displayPrice(numberOfHoodies * 20);
    }

    private int calculatePrice(boolean addFigurine, boolean addKeychain, boolean addAnime) {
        int price = numberOfHoodies * 20;

        if (addFigurine == true) {
            price += 13;
        }
        if (addKeychain == true) {
            price +=  5;
        }
        if (addAnime == true) {
            price+= 90;
        }
        return price;
    }

}


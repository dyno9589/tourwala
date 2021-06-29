package com.botarmy.tourwala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.botarmy.tourwala.Utility.NetworkChangeListener;

public class hotelbook extends AppCompatActivity {
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    Button bookhotel,nearbyhotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelbook);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //Hooks
        bookhotel=(Button)findViewById(R.id.buttonBookHotel);
        nearbyhotels=(Button)findViewById(R.id.buttonHotels);

        bookhotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.makemytrip.com/hotels");
            }
        });

        nearbyhotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movetohotel = new Intent(hotelbook.this,hotel.class);
                startActivity(movetohotel);
            }
        });


    }
    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}
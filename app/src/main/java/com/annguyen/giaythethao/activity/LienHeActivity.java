package com.annguyen.giaythethao.activity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.annguyen.giaythethao.R;

public class LienHeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView textViewFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);

        toolbar = findViewById(R.id.toolbarLienHe);
        textViewFacebook = findViewById(R.id.textViewLienHeFacebook);

        //set cho sự kiện khi click vào TextView có link Facebook
        textViewFacebook.setMovementMethod(LinkMovementMethod.getInstance());

        setMyActionBar();
    }

    private void setMyActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_media_previous);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

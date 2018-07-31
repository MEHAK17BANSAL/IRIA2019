package com.brandappz.dhfl.event.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.brandappz.dhfl.event.R;

public class OrganizingCommitteeActivity extends AppCompatActivity {
      ImageView menu;

      void initviews(){
          menu=findViewById(R.id.menu21);
          menu.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(OrganizingCommitteeActivity.this,MenuActivity.class);
                  startActivity(intent);
              }
          });
      }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizing_committee);
        initviews();
    }
}

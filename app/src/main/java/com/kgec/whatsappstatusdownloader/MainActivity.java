package com.kgec.whatsappstatusdownloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager myViewPager;
    private TabLayout myTablayout;
    private TabsAccessorAdapter tabsAccessorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("WhatsApp status download");

        myViewPager=findViewById(R.id.main_tabs_pager);
        myTablayout=(TabLayout) findViewById(R.id.main_tabs);

        checkPermission();



        tabsAccessorAdapter=new TabsAccessorAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(tabsAccessorAdapter);
        myTablayout.setupWithViewPager(myViewPager);


    }


    private  void checkPermission(){

        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {

            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {

                if (!report.areAllPermissionsGranted()){

                    checkPermission();
                }

            }
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {


            }
        }).check();
    }
}
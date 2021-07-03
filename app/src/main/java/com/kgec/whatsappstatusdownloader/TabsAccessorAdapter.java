package com.kgec.whatsappstatusdownloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kgec.whatsappstatusdownloader.Fragments.ImageFragment;
import com.kgec.whatsappstatusdownloader.Fragments.VideosFragment;

public class TabsAccessorAdapter extends FragmentPagerAdapter {

    public TabsAccessorAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:

                ImageFragment imageFragment=new ImageFragment();
                return imageFragment;

            case 1:

                VideosFragment videosFragment=new VideosFragment();
                return videosFragment;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int i) {

        switch (i){

            case 0:
                return "Images";

            case 1:
                return "Videos";

            default:
                return null;
        }
    }
}

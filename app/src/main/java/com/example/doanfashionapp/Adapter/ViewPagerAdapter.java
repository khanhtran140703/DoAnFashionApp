package com.example.doanfashionapp.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.doanfashionapp.DTO.HangSanPham;
import com.example.doanfashionapp.Fragment.Fragment_4MEN;
import com.example.doanfashionapp.Fragment.Fragment_Highway;
import com.example.doanfashionapp.Fragment.Fragment_Routine;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_4MEN();
            case 1:
                return new Fragment_Highway();
            case 2:
                return new Fragment_Routine();
            default:
                return new Fragment_4MEN();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="4 Men";
                break;
            case 1:
                title="Highway";
                break;
            case 2:
                title="Routine";
                break;
            default:
                title = "4 Men";
                break;
        }
        return title;
    }
}

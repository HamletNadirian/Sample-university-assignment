package com.ntukhpi.otp.nadirian.lab05;

import java.util.Comparator;

public class ComparatorByClassRevers implements Comparator<Product> {


    @Override
    public int compare(Product a, Product b) {
        return b.getName().compareTo(a.getName());
    }
}
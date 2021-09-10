package com.ntukhpi.otp.nadirian.lab05;

import java.util.Comparator;

public class ComparatorByclass implements Comparator<Product> {


    @Override
    public int compare(Product a, Product b) {
    return a.getName().compareTo(b.getName());
    }
}

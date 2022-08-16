/**
 * Lucas Forcier
 *
 * The program adds band information from a text file into an ArrayList. The program will prompt the user to search the
 * list for a band name or a set time. The program will sort the list alphabetically by band name and use a binary search
 * to find the users request. If the user requests a set time the program will sort the ArrayList by numerical order.
 * The program will return the band with the set time closest to the time indicated by the user
 */

package com.csc420;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {

        boolean run = true;
        ArrayList<Band> bands = new ArrayList<Band>();
        File file = new File("bandinfo.txt");
        Scanner in = new Scanner(file);
        while (in.hasNext()) {
            String temp = in.nextLine();
            String[] tempArray = temp.split("\\|");
            float time = Float.parseFloat(tempArray[1]);
            bands.add(new Band(tempArray[0], time));
        }

        // prompt user if they want to search by name or set time
        while (run) {
            System.out.println("Search by Band Name(1) or Set List(2)");
            Scanner input = new Scanner(System.in);
            int entry = input.nextInt();
            switch (entry) {
                case 1: {
                    input.nextLine();
                    bands = sortAlphabetically(bands);
                    System.out.println("Enter the band name you are looking for: ");
                    String name = input.nextLine();
                    int result = searchBandName(name, bands, 0, bands.size() - 1);
                    if (result == -1) {
                        System.out.println(name + " was not found!");
                    } else {
                        System.out.println(bands.get(result));
                    }
                    break;
                }
                case 2: {
                    bands = sortNumerically(bands);
                    input.nextLine();
                    System.out.println();
                    System.out.println("Enter the set time you are looking for: ");
                    float value = input.nextInt();
                    Band b = searchSetTime(value, bands);
                    System.out.println(b);
                    break;
                }
            } // end switch
        } // end while

    }

    public static Band searchSetTime(Float f, ArrayList<Band> b) {
        // calculate the difference between the entered value and the set time of the first element in b
        float difference = Math.abs(f - b.get(0).getSetTime());
        Band band = b.get(0);
        // loop through all set times to find the smallest difference in time
        for (int i = 1; i < b.size(); i++) {
            float current = Math.abs(f - b.get(i).getSetTime());
            if (current < difference) {
                difference = current;
                band = b.get(i);
            }
        }
        // return the band with the smallest difference in set time
        return band;
    }

    public static int searchBandName(String name, ArrayList<Band> b, int l, int r) {
       // use a binary search to determine if the band name is in the ArrayList
        if (l > r) {
            return -1;
        }
        int middle = (l + r) / 2;
        if (b.get(middle).getBandName().equals(name)) {
            return middle;
        } else if (b.get(middle).getBandName().compareTo(name) < 0) {
            return searchBandName(name, b, middle + 1, r);
        } else {
            return searchBandName(name, b, l, middle);
        }
    }

    public static ArrayList<Band> sortAlphabetically(ArrayList<Band> bands) {
        for (int i = 0; i < bands.size(); i++) {
            for (int j = i + 1; j < bands.size(); j++) {
                if (bands.get(i).getBandName().compareTo(bands.get(j).getBandName()) > 0) {
                    Collections.swap(bands, i, j);
                }
            }
        }
        return bands;
    }

    public static ArrayList<Band> sortNumerically(ArrayList<Band> bands) {
        for (int i = 0; i < bands.size(); i++) {
            for (int j = i + 1; j < bands.size(); j++) {
                if (bands.get(i).getSetTime().compareTo(bands.get(j).getSetTime()) > 0) {
                    Collections.swap(bands, i, j);
                }
            }
        }
        return bands;
    }
}

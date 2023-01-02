/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irina
 */
public class AOCDay25 {
    
    public static void countHeatOutput () {
        try {
            Scanner in = new Scanner(System.in);
  
            System.out.println("Please enter a file path with your puzzle input:");
            String filePath = in.next(); 
            // "C:\\Users\\irina\\OneDrive\\NetBeansProjects\\inputFile.txt"
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String lineRead;
            long finalDecimalSum = 0;
            while ( (lineRead = reader.readLine()) != null) {   
                finalDecimalSum += fromSnafuToDecimal(lineRead);
                //System.out.println(fromSnafuToDecimal(lineRead));
            }
            fromDecimaltoSnafu(finalDecimalSum);
        } catch (IOException ex) {
            Logger.getLogger(AdventOfCode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static long fromSnafuToDecimal(String lineRead) {
        long decimalResult = 0;
        int lengthOfLineRead = lineRead.length();
        int [] charToInt = new int[lengthOfLineRead];
        for (int i = 0; i < lineRead.length(); i++) {
            switch (lineRead.charAt(i)) {
                case '2' -> charToInt[i] = 2;
                case '1' -> charToInt[i] = 1;
                case '0' -> charToInt[i] = 0;
                case '-' -> charToInt[i] = -1;
                case '=' -> charToInt[i] = -2;
            }
        }
        for (int i = 0; i < charToInt.length; i++) {
            decimalResult += charToInt[i] * Math.pow(5, lengthOfLineRead - i - 1);
        }
        return decimalResult;
    }
    

    private static void fromDecimaltoSnafu(long finalDecimalSum) {
        List<Long> quinarySum = fromDecimalToQuinary(finalDecimalSum);
        System.out.print(fromQuinaryToSnafu(quinarySum));
    }

    private static List<Long> fromDecimalToQuinary(long finalDecimalSum) {
        List<Long> quinary = new ArrayList<>();
        long rest;        
        while (finalDecimalSum >= 5) {
            rest = finalDecimalSum / 5;
            quinary.add(finalDecimalSum - rest * 5);
            finalDecimalSum = rest;
        }
        quinary.add(finalDecimalSum);
        
        return quinary;
    }

    private static List<Character> fromQuinaryToSnafu(List<Long> quinarySum) {
        List<Long> result = new ArrayList<>();
        long carry = 0;
        for (int i = 0; i < quinarySum.size(); i++) {    
            if ((quinarySum.get(i) == 1) || (quinarySum.get(i) == 2) || (quinarySum.get(i) == 0)) {
               result.add(checkOverFlow(quinarySum.get(i)+carry));              
               if ( (quinarySum.get(i)+carry) > 2) {
                   carry = 1;
               }
               else carry = 0;
            }
            if (quinarySum.get(i) == 3) {
               
               result.add(checkOverFlow(-2+carry));
               carry = 1;
            }
            if (quinarySum.get(i) == 4) {  
               result.add(checkOverFlow(-1+carry));
               carry = 1;
            }
        }
        if (carry != 0) {
            result.add(carry);
        }
        return toSnafu(result);
    }

    private static long checkOverFlow(Long number) {
        if ( number == 3 ) {
            return -2;
        }
        if ( number == 4 ) {
            return -1;
        }
        return number;
    }

    private static List<Character> toSnafu(List<Long> result) {
        List<Character> resultChar = new ArrayList<>();
        for (int i = result.size()-1; i >= 0; i--) {
            if (result.get(i) == -2) {
                resultChar.add('=');
            }
            if (result.get(i) == -1) {
                resultChar.add('-');
            }
            if (result.get(i) == 0 || result.get(i) == 1 || result.get(i) == 2) {
                resultChar.add((char)(result.get(i)+'0'));
            }
        }
        return resultChar;
    }
}

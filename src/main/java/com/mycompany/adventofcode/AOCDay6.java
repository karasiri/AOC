/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irina
 */
public class AOCDay6 {
    
        public static int findSequenceOfNCharacters () {  
            System.out.println("Please enter a length of sequence:");
            Scanner in = new Scanner(System.in);
            int lengthOfSequence = in.nextInt();
            char[] sequenceOfNCharacters = new char[lengthOfSequence];
            char[] sequenceOfNextNCharacters = new char[lengthOfSequence];
            int result = lengthOfSequence;
            System.out.println("Please enter a file path with your puzzle input:");
            String filePath = in.next();   
            try {
                Reader reader = new BufferedReader(new FileReader(filePath));
                reader.read(sequenceOfNCharacters, 0, lengthOfSequence);
                if (checkSequence(sequenceOfNCharacters)) {
                    return result;
                }
                int charRead;
                while ( (charRead = reader.read()) != -1) { 
                    result++;
                    System.arraycopy(sequenceOfNCharacters, 1, sequenceOfNextNCharacters, 0, lengthOfSequence-1); 
                    sequenceOfNextNCharacters[lengthOfSequence-1] = (char)charRead;
                    if ( checkSequence(sequenceOfNextNCharacters) ) {
                        return result;
                    }
                    sequenceOfNCharacters = sequenceOfNextNCharacters.clone();
                }

            } catch (IOException ex) {
                Logger.getLogger(AdventOfCode.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
    }
    
    
    
    private static Boolean checkSequence(char [] sequenceOfFourCharacters) {
        Set<Character> uniqCharacters = new HashSet<>();
        for (char character : sequenceOfFourCharacters) {
            if (uniqCharacters.add(character) == false) {
                return false;
            }
        }
        return true;
    }
}

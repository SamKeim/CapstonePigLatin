/*
 * @Author Sam Keim - github @ SamTheEnby
 */

import java.util.Scanner;
import java.util.regex.*;


public class PigLatinMain {

	public String toPigLatin(String phrase) {
		
		boolean isUpper = phrase.equals(phrase.toUpperCase()); // check if phrase is in all caps, stores boolean and applies to end of loop
		
		// Checks for punctuation at end of string, stores to variable, modifies string and appends punctuation to end
		String regPunct = "[.?!]";
		String punctuation = "";
		if (regexInt(phrase, regPunct) == phrase.length() - 1) {
			punctuation = phrase.substring(regexInt(phrase, regPunct));
			phrase = phrase.substring(0,regexInt(phrase, regPunct));
		}
		
		//set up arrays, variables, regex
		String[] phraseArray = phrase.split(" ");
		String[] finalArray = new String[phraseArray.length];
		String finalSentence = "";
		String regNumSym = "[0-9@#$%^&*<>.,:;]";
		String regVowels = "[aeiouAEIOU]";
		
		// Checks if string is blank
		if (phrase.length() < 1) {
			return "Please try again.";
		}
		
		// Loops through array of words and makes adjustments
		for (int x = 0; x < phraseArray.length; x++) {
			
			// Check if there is a symbol or number, leaves the word unmodified
			if (regexInt(phraseArray[x],regNumSym) > 0) {
				finalArray[x] = phraseArray[x] + " ";
				continue;
			}
			
			// Translates current word in loop to pig latin
			int indx = regexInt(phraseArray[x], regVowels);
				if (indx == 0) {
					finalArray[x] = phraseArray[x] + "way ";
				} else {
					finalArray[x] = phraseArray[x].substring(indx) + phraseArray[x].substring(0,indx) + "ay ";
			}	
		}
		
		// Converts array to string
		for (String fin : finalArray) {
			finalSentence += fin;
		}
		
		// Trims off end space
		finalSentence = finalSentence.trim();
	
		// If the submitted phrase was in uppercase, modifies sentence to uppercase (for -ay and -hay suffixes)
		if (isUpper) {
			finalSentence = finalSentence.toUpperCase();
		}
		
		// Appends punctuation if available
		finalSentence += punctuation;
		
		return finalSentence;		
	}
	
	public static int regexInt(String phrase, String theRegex) {	
		// Honestly I don't understand these objects very well yet
		Pattern checkRegex = Pattern.compile(theRegex);
		Matcher regexMatcher = checkRegex.matcher(phrase);
		
		// Checks if regex character is available and returns starting index
		while(regexMatcher.find()) {
			return regexMatcher.start();
		}
		
		// Returns -1 if unavailable
		return -1;
	}
	
	public static void main(String[] args) {
		PigLatinMain mthd = new PigLatinMain();
		Scanner scnr = new Scanner(System.in);
		String userChoice = "Y";
		
		// Do loop will ask for phrase, and continue while userChoice begins with "Y"
		do{
			if (userChoice.toUpperCase().startsWith("Y")) { // If user chooses "Y"
				System.out.println("Please enter a phrase.");
				String phrase = scnr.nextLine();
				System.out.println(mthd.toPigLatin(phrase));
			} else if (userChoice.toUpperCase().startsWith("N")) { // Exits if user chooses "N"
				System.out.println("Thank you, goodbye.");
				break;
			} else { // Alerts user if invalid selection is made
				System.out.println("Please try again.");
			}
			
			// Asks for user to continue
			System.out.println("Would you like to enter another phrase? (Y/N)");
			userChoice = scnr.nextLine();
			
		} while (true); // I know there has to be a better way to do this, but I do not know it yet
//		System.out.println("hello in piglatin is " + mthd.toPigLatin("Hello"));
	}
}

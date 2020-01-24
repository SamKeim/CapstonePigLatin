/*
 * @Author Sam Keim - github @ SamTheEnby
 */

import java.util.Scanner;
import java.util.regex.*;


public class PigLatinMain {

	public String toPigLatin(String phrase) {
		boolean isUpper = phrase.equals(phrase.toUpperCase());
		//phrase = phrase.toLowerCase();
		String[] phraseArray = phrase.split(" ");
		String[] vowelArray = {"a", "e", "i", "o", "u", "A", "E", "I", "O", "U"};
		String[] finalArray = new String[phraseArray.length];
		String finalSentence = "";
		String regNumSym = "[a-z]";
		
		for (int x = 0; x < phraseArray.length; x++) {
			if (phraseArray[x].contains(regNumSym)) {
				return phrase;
			}
			if (phrase.length() < 1) {
				return "Please try again.";
			}
			int indx = phraseArray[x].length();
			for (int i = 0; i < vowelArray.length; ++i) {
					if (phraseArray[x].indexOf(vowelArray[i]) != -1 && phraseArray[x].indexOf(vowelArray[i]) < indx) {
						indx = phraseArray[x].indexOf(vowelArray[i]);
					}
				}
				
				if (indx == 0) {
					finalArray[x] = phraseArray[x] + "way ";
				} else {
					finalArray[x] = phraseArray[x].substring(indx) + phraseArray[x].substring(0,indx) + "ay ";
				}
				finalSentence += finalArray[x];
			}
		finalSentence = finalSentence.trim();
		if (isUpper) {
			finalSentence = finalSentence.toUpperCase();
		}
		return finalSentence;		
	}
	
//	public static int regexVowels(String phrase) {		
//		Pattern checkRegex = Pattern.compile("[aeiou]");
//		Matcher regexMatcher = checkRegex.matcher(phrase);
//		while(regexMatcher.find()) {
//			return regexMatcher.start();
//		}
//		return 0;
//	}
	
	public static void main(String[] args) {
		PigLatinMain mthd = new PigLatinMain();
		Scanner scnr = new Scanner(System.in);
		boolean userChoice = true;
		do{
			System.out.println("Please enter a phrase.");
			String phrase = scnr.nextLine();
			System.out.println(mthd.toPigLatin(phrase));
			System.out.println("Would you like to enter another phrase? (Y/N)");
			userChoice = scnr.nextLine().toUpperCase().startsWith("Y");
		} while (userChoice);
		System.out.println("Thank you, goodbye.");
	}
}

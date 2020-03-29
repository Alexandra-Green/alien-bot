package main;
import javafx.scene.control.Label;
import rita.*;
/*
 * This class recognizes verbs based on PENN part-of-speech tags
 * Corrects the verb to be displayed by changing the postfix/verb tense
 * Displays the Alien's answers
 */
public class POSTagging extends Chatroom {

	 //Searches for verb tense and conjugates "be" accordingly or just adds I.
	public static void verb(String input)
	{
		String help = "What are you talking about earthling? Maybe you should ask for help.";
		String presentTense = "vbg";
		String singularPresent = "vbp";
		String[] s = RiTa.getPosTags(input.replaceAll("[^A-Za-z ]+",""));
			if(s[s.length-1].equals(presentTense)&& al.parse(input)!= help)
			{
				String[] t = RiTa.getPosTags(al.parse(input));
				labelList.add(new Label(alias + ": " + "I am " + corrections(input)));
			}
			else if(s[s.length-1].equals(singularPresent) && al.parse(input)!= help)
			{
				String[] t = RiTa.getPosTags(al.parse(input));
				labelList.add(new Label(alias + ": " + "I " + corrections(input)));
			}
			else
			{
				labelList.add(new Label(alias + ": " + al.parse(input)));
			}
	}
	
	//Corrects the verb in the Alien's response to reflect verb tense used by human
	public static String corrections(String input)
	{
		String myString = al.parse(input);
		String arr[] = myString.split(" ", 2);
		String correction = arr[0];
		String theRest = arr[1];
		String[] t = RiTa.getPosTags(al.parse(input));
		String[] s = RiTa.getPosTags(input.replaceAll("[^A-Za-z ]+",""));
		
		if(s[s.length-1].equals("vbg") && t[0].equals("vb")
				||s[s.length-1].equals("vbg") && t[0].equals("vbp")
				||s[s.length-1].equals("vbg") && t[0].equals("nn"))
		{
			correction = arr[0].concat("ing ");
			myString = correction + theRest;
		}
		return myString;
	}
}

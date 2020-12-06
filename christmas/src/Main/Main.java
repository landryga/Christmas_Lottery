package Main;
// File Name SendEmail.java

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.*;

import GUI.Gui;

public class Main {

   public static void main(String [] args) {    
      
	   Gui gui = new Gui();
	   
	   /*
	   List<String> recipients = new ArrayList<>();
	   
	   String topic = "<<<<<<<<<<<<<<<<LOSOWANIE CHRISTMAS 2017>>>>>>>>>>>>>>>>>>>>>>>>>>";
	   String body, recipient;
	   
	   recipients.add("kuba.walenda@gmail.com");
	   int[] array = {0, 1, 2, 3, 4};
	   String[] names = {"Magda Walenda", "Adrien Jaeck", "Kuba Walenda", "Kasia Walenda", "Basia Walenda"};
	   
	   shuffleArray(array);
	   
	   
	   
	   
	   try {
		
		   for(int i = 0; i<array.length; i++) {
			   if(i==4) {
				   body = "GRATULACJE wylosowalas " + recipients.get(array[i]);
				   recipient = recipients.get(array[0]);
				   
			   }
			   else {
				   body = "GRATULACJE wylosowalas " + recipients.get(array[i]);
				   recipient = recipients.get(array[i+1]);
				   
			   }
			   
			   Sender sender = new Sender();
				sender.sendEmail(body, topic, recipient);
			   
		   }
		   
		
	   } catch (UnsupportedEncodingException | MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	
	*/
	   
   }
}
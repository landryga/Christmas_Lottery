package Main;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ParticipantMaintainer {

	private static LinkedList<Participant> participants = null;
	
	public ParticipantMaintainer() {
		if(participants == null) {
			participants = new LinkedList<Participant>();
		}
	}
	
	public LinkedList<Participant> retrieveParticipants() {
		return this.participants;
	}
	
	//Retrieve participants with lottery result assigned to each of them
	public HashMap<Participant, String > retrieveLotteryResult() {
		
		LinkedList<Participant> partList = new LinkedList<Participant>();
		
		partList = shuffleParticipants(participants);
		
		HashMap<Participant,String> lotteryResult = new HashMap<Participant, String> ();
		
		for(int i = 0; i<partList.size(); i++) {
			   if(i==partList.size()-1) {
				   lotteryResult.put(partList.get(i), partList.get(0).getName() + " " + partList.get(0).getSurname());
			   }
			   else {
				   lotteryResult.put(partList.get(i), partList.get(i+1).getName() + " " + partList.get(i+1).getSurname());
			   } 
		}
		
		return lotteryResult;
	}
	
	public boolean addParticipant(Participant participant) {
		
		participant.setId(setParticipantId());
		
		if (!checkParticipant(participant)) {
			System.out.println("ERROR ADDING PARTICIPANT");
			return false;
		}
		else {
			participants.add(participant);
			return true;
		}
	}
	
	public void removeParticipant(Participant participant) {
		participants.remove(participant);
	}
	
	public Participant getParticipant(int index) {
		for(int i = 0; i< participants.size(); i++) {
			Participant part_check = new Participant();
			if (part_check.id == index) {
				return part_check;
			}
		}
		return null;
	}
	
	private static boolean checkParticipant(Participant participant) {
		if(participant.getName().isEmpty() || participant.getSurname().isEmpty() || participant.getEmail().isEmpty() || participant.getId()==0) {
			return false;
		}
		
		return true;
	}
	
	private static int setParticipantId() {
		int currId = 1;
		if(participants == null) {
			return currId;
		}
		
		for(int i=0; i< participants.size(); i++) {
			Participant participant = participants.get(i);
			currId = participant.getId();
			if(currId<=participant.getId()) {
				currId = participant.getId()+1;
			}
		}
		
		return currId;
	}
	
	private static LinkedList<Participant> shuffleParticipants(LinkedList<Participant> partList) {
			// If running on Java 6 or older, use `new Random()` on RHS here
			Random rnd = ThreadLocalRandom.current();
			for (int i = partList.size()-1; i > 0; i--)
			{
			   int index = rnd.nextInt(i + 1);
			   
			   // Simple swap
			   Participant a = new Participant();
			   a = partList.get(index);
			   partList.set(index, partList.get(i));
			   partList.set(i, a);
			}
			
			return partList;
		}
	
}

package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Main.Participant;
import Main.ParticipantMaintainer;
import email.Sender;

public class SenderTab extends JPanel implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8654257658010431503L;
	//private static final long serialVersionUID = 1L;
	private JPanel participantPanel, emailPanel;
	private JButton addParticipantButton, sendEmailButton;
	private JLabel emailContentLabel, emailSubjectLabel, participantsLabelsList;
	private JTextArea emailContent;
	private JTextField emailSubject;
	private static GridBagConstraints c ;
	private static String text;
	
	public SenderTab() {
		
		participantPanel = new JPanel();
		emailPanel = new JPanel();
		addParticipantButton = new JButton("Dodaj uczestnika losowania");
		addParticipantButton.setSize(10, 10);
		sendEmailButton = new JButton("Wyślij email");
		emailContentLabel = new JLabel();
		emailContent = new JTextArea(10, 20);
		emailSubjectLabel = new JLabel();
		emailSubject = new JTextField(20);
		
		
		//Set labels values
		emailContentLabel.setText("Treść maila");
		emailSubjectLabel.setText("Tytuł maila");

		//Design panels
		this.setLayout(new GridLayout());
		participantPanel.setLayout(new GridBagLayout());
		emailPanel.setLayout(new GridBagLayout());
		emailPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		participantPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		
		//design buttons
		Dimension buttonDimension = new Dimension(200, 20);
		sendEmailButton.setPreferredSize(buttonDimension);
		sendEmailButton.setActionCommand("send");
		sendEmailButton.addActionListener(this);
		addParticipantButton.setPreferredSize(buttonDimension);
		addParticipantButton.setActionCommand("addPart");
		addParticipantButton.addActionListener(this);
		
		//design input fields
		emailSubject.setSize(new Dimension(100, 10));
		emailContent.setSize(new Dimension(100, 10));
		JScrollPane scroll = new JScrollPane(emailContent);
		
		//Set container components size
		//this.setSize(600, 500);
		participantPanel.setMinimumSize(new Dimension(400, 250));
		emailPanel.setSize(300, 250);
		//tPaneMain.setSize(600, 500);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		
		//Add component
		c.gridx = 0;
		c.gridy = 2;
		participantPanel.add(addParticipantButton, c);
		
		participantsLabelsList = new JLabel();

		participantPanel.add(participantsLabelsList);
		
		//c.anchor = GridBagConstraints.SOUTH;
		
		c.gridx = 1;
		c.gridy = 1;
		emailPanel.add(emailSubjectLabel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		emailPanel.add(emailSubject, c);
		
		c.gridx = 1;
		c.gridy = 3;
		emailPanel.add(emailContentLabel, c);
		
		c.gridx = 1;
		c.gridy = 4;
		emailPanel.add(scroll, c);
		
		c.gridx = 1;
		c.gridy = 5;
		emailPanel.add(sendEmailButton, c);
		
		c.gridx = -1;
		c.gridy = 0;
		this.add(participantPanel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		this.add(emailPanel, c);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if(command == "addPart") {
			AddParticipantForm partForm = new AddParticipantForm();
			partForm.addWindowListener(this);
		}
		else if (command == "send") {
			ParticipantMaintainer maintainer = new ParticipantMaintainer();
			HashMap<Participant, String> lotteryResult = new HashMap<Participant, String>();
			lotteryResult = maintainer.retrieveLotteryResult();
			
			Sender sender = new Sender();
			sender.sendEmails(lotteryResult, emailSubject.getText(), emailContent.getText());
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {

		
	}

	@Override
	public void windowClosing(WindowEvent e) {

		this.refreshParticipants();
		this.revalidate();
		this.repaint();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
	
	private void refreshParticipants() {
		//Add participants
				ParticipantMaintainer maintainer = new ParticipantMaintainer();
			
				LinkedList<Participant> participants = new LinkedList<Participant>();
				participants = maintainer.retrieveParticipants();
				c.gridx = 0;
				
				text = "<html>";
				
				for(int i= 0; i < participants.size(); i++) {
					Participant participant = participants.get(i);
					text += participant.getName() + " " + participant.getSurname() + ", " + participant.getEmail() + "<br>" ;
				}
				
				text += "</html>";
				
				participantsLabelsList.setText(text);
	}

} 

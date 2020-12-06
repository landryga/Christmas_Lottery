package GUI;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Main.Participant;
import Main.ParticipantMaintainer;

public class AddParticipantForm extends JDialog implements ActionListener {

	private JPanel mainPanel;
	private JButton addButton, cancelButton;
	private JLabel nameLbl, surnameLbl, emailLbl;
	private Participant participant;
	private HashMap<JComponent,JComponent> components;
	private Field[] fields;
	
	AddParticipantForm() {
		
		this.setMinimumSize(new Dimension(200, 200));
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		
		participant = new Participant();
		
		components = new HashMap<JComponent,JComponent>();
		
		components = generateFormFields(participant);
		
		Iterator it = components.entrySet().iterator();

		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			mainPanel.add((JComponent)pair.getKey(), c);
			c.gridy++;
			mainPanel.add((JComponent)pair.getValue(), c);
			c.gridy++;
		}
		
		addButton = new JButton("OK");
		addButton.addActionListener(this);
		addButton.setActionCommand("Add");
		cancelButton = new JButton("Cancel");
		c.gridy++;
		mainPanel.add(addButton, c);
		c.gridy++;
		mainPanel.add(cancelButton, c);
		
		//check all of the attributes of Participant and generate appropriate form for it
		this.add(mainPanel);
		
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Add" : {
			
				Class<?> c = participant.getClass();

				Iterator it = components.entrySet().iterator();

				while(it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					JComponent searchComponent = (JComponent)pair.getValue();
					//TODO - what if we have two attributes with same names and of different types??
					if(searchComponent instanceof JTextField) {
						JTextField txtfld = (JTextField) searchComponent;
						try {
							Field f = c.getDeclaredField(txtfld.getName());
							try {
								f.set(participant, txtfld.getText());
							} catch (IllegalArgumentException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IllegalAccessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (NoSuchFieldException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SecurityException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
	
				LinkedList<JComponent> compList = new LinkedList<JComponent>();
				
				ParticipantMaintainer maintainer = new ParticipantMaintainer();
				
				maintainer.addParticipant(participant);	
				
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
		
		case "Cancel" : { 
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
		}
	}
	
	private HashMap<JComponent, JComponent> generateFormFields(Participant participant) {
		HashMap<JComponent, JComponent> componentsMap = new HashMap<JComponent, JComponent>();
		fields = participant.getClass().getDeclaredFields();
		for(Field field: fields) {
			System.out.println(field.getType().getTypeName());
			String tpName = field.getType().getTypeName();
			if(tpName == "java.lang.String") {
				JLabel lbl = new JLabel(field.getName());
				JTextField txtFld = new JTextField(10);
				txtFld.setName(field.getName());
				componentsMap.put(lbl, txtFld);
			}
		}
		return componentsMap;
	}
}

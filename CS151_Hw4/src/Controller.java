import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller {
	private Model model;
	private JLabel monthandYear, date;
	private JFrame frame;
	private JPanel panel;
	private int dayOfMonth;

	public Controller(Model model) {
		dayOfMonth= model.getDate().getDayOfMonth();
		System.out.println("Testing: " + dayOfMonth);
		this.setModel(model);
		setFrame();
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setFrame() {
		frame = new JFrame();
		panel = new JPanel();
		frame.setSize(800, 250);
		frame.setLayout(new BorderLayout());
		/* First at the top will be next, previous, exit and create button */
		JPanel panelTop = new JPanel();
		//panelTop.setLayout(new BorderLayout());
		panelTop.setBackground(Color.green);
		JButton createButton = new JButton("Create");
		// working create Button
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JTextField newName = new JTextField();
				JTextField starting = new JTextField();
				JTextField ending = new JTextField();

				Object[] texts = { "Selected date: " + model.getDate().toString(), "New Event Name", newName,
						"begin at MM:HH", starting, "Ending at MM:HH", ending };
				int response = JOptionPane.showConfirmDialog(null, texts, "Sign Up new Event!",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (response == 0) {
					// 0 mean the user accept to create an event
					Event event = new Event(newName.getText(), starting.getText(), ending.getText());
					model.getEventlist().add(event);
				}
			}
		});

		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dayOfMonth+=1;
				model.nextDay();
				dayOfMonth = model.getDate().getDayOfMonth();
				model.update();
				//dayOfMonth+=1;
			}
		});

		JButton preButton = new JButton("Previous");
		preButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.lastDay();
				dayOfMonth = model.getDate().getDayOfMonth();
				model.update();
				
			}
		});

		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1); // can using dispose here but System.exit will//
								// help to terminate the entire program
			}
		});

		panelTop.add(createButton);
		panelTop.add(preButton);
		panelTop.add(nextButton);
		panelTop.add(exitButton);

		//leftPanel has day and year, and list of dates
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		monthandYear = new JLabel(model.monthAr[model.getDate().getMonthValue() - 1] + " " + model.getDate().getYear());
		leftPanel.add(monthandYear, BorderLayout.NORTH);
		System.out.println("Checking "+dayOfMonth);
		panel = monthView();
		leftPanel.add(panel, BorderLayout.SOUTH);
		//leftPanel.setBackground(Color.orange);
		
		//rightPanel has text area and 
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		date = new JLabel(model.dayOfWeek[model.getDate().getDayOfWeek().getValue()] + " " + model.getDate().getMonthValue() + "/" + model.getDate().getDayOfMonth()); 
		rightPanel.add(date,BorderLayout.NORTH);
		JTextArea area = new JTextArea();
		area.setBackground(Color.cyan);
		rightPanel.add(area,BorderLayout.CENTER);
		

		frame.add(rightPanel);
		frame.add(leftPanel, BorderLayout.WEST);
		frame.add(panelTop, BorderLayout.NORTH);
		frame.setTitle("CS 151 Convenience Calendar");
		
		model.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				monthView();
				frame.validate();
				System.out.println("rePaint");
				frame.repaint();
				

			}
		});
		
		model.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				monthandYear.setText(model.monthAr[model.getDate().getMonthValue() - 1] + " " + model.getDate().getYear());
				monthandYear.repaint();

			}
		});
		
		model.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int temp;
				if(model.getDate().getDayOfWeek().getValue() ==7) {
					temp =0;
				}else {
					temp=model.getDate().getDayOfWeek().getValue();
				}
				date.setText(model.dayOfWeek[temp] + " " + model.getDate().getMonthValue() + "/" + model.getDate().getDayOfMonth());
				//System.out.println("Date: " + model.getDate().toString());
				date.repaint();

			}
		});
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setLocationRelativeTo(null); // set the calendar GUI at the center of the screen
		frame.setVisible(true);
		
	}

	public JPanel monthView() {
		panel = new JPanel();
		panel.removeAll(); // repaint each time show the monthview with a new set of month
		panel.setLayout(new GridLayout(0, 7));

		for (int i = 0; i < model.dayOfWeek.length; i++) {
			JButton button = new JButton(model.dayOfWeek[i]);
			panel.add(button);
		}

		LocalDate temp = LocalDate.of(model.getDate().getYear(), model.getDate().getMonthValue(), 1);

		// System.out.println("ship: "+ (temp.getDayOfWeek().getValue()));
		for (int i = 0; i < temp.getDayOfWeek().getValue(); i++) {
			JButton button = new JButton("");
			//button.setBackground(Color.WHITE);
			panel.add(button);
		}
		

		for (int i = 1; i < model.getDate().lengthOfMonth() + 1; i++) {
			JButton button = new JButton("" + i);
			//System.out.println("Yes : " + model.getDate().getDayOfMonth()
			//System.out.println("OutIf: " + dayOfMonth+ " " + i);
					//);
			if (dayOfMonth == i) {
				System.out.println("Yes: " + dayOfMonth);
				button.setBackground(Color.PINK);
				button.setOpaque(true);
			}
			panel.add(button);
		}
		return panel;
	}

}

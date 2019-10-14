import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*First I draw add button on top, JtextArea in the center and JtextField at bottom
 * add one more function in Model.java to generate upcoming new texts(save and print all)
 * create changeListener and ActionListener and add them to frame
 * */
public class MVCTester {
	private static final int AREA_SIZE=40;
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		//JLabel label = new JLabel();
		final Model model = new Model();
		JButton addButton = new JButton("ADD");
		final JTextArea area = new JTextArea(AREA_SIZE,0);
		final JTextField textField = new JTextField();
		
		ChangeListener listener = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				area.setText(model.printLine());
			}
			
		};
		model.addChangeListener(listener);
		// after having ChangeListener, we need actionListener for click button add
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String text = textField.getText();
				model.addText(text);
				
			}
			
		});
		// the BorderLayout decision is based on the example picture
		frame.add(addButton,BorderLayout.NORTH);
		frame.add(area,BorderLayout.CENTER);
		frame.add(textField, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		

	}

}

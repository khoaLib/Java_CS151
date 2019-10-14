import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * if the user click zoom in, the car will be bigger
 * otherwise it getting smaller*/
public class ZoomTester {
	private static final int CAR_WIDTH= 100;
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		final ZoomShape shape = new CarShape(100,50,CAR_WIDTH);
		//Shape
		JButton zoomIn = new JButton("Zoom in");
		JButton zoomOut = new JButton("Zoom out");
		
		ShapeIcon obg = new ShapeIcon(shape,400,100);
		final JLabel label = new JLabel(obg);
		frame.setLayout(new FlowLayout());
		frame.add(label);
		
		
		frame.add(zoomIn);
		frame.add(zoomOut);
		
		zoomIn.addActionListener(event -> {
			shape.zoomin();
			label.repaint();
		});
		
		zoomOut.addActionListener(event -> {
			shape.zoomout();
			label.repaint();
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		

	}
	

}

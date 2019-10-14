import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * This program implements an animation that moves a car shape.
 */
public class AnimationTester1 {
	private static final int ICON_WIDTH = 400;
	private static final int ICON_HEIGHT = 300;
	private static final int CAR_WIDTH = 100;
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		//MoveableShape []list = new MoveableShape(NUMBER_OF_SHAPES);
		frame.setSize(600, 600);
		ArrayList<MoveableShape> list = new ArrayList<MoveableShape>();
		final MoveableShape shape = new CarShape(0, 0, CAR_WIDTH);
		list.add(shape);
		final MoveableShape shape2 = new CarShape(0, 100, CAR_WIDTH);
		list.add(shape2);
		final MoveableShape shape3 = new CarShape(0, 200, CAR_WIDTH);
		list.add(shape3);
		ShapeIcon icon = new ShapeIcon(list, ICON_WIDTH, ICON_HEIGHT);

		final JLabel label = new JLabel(icon);
		frame.setLayout(new FlowLayout());
		frame.add(label);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setVisible(true);

		final int DELAY = 100;
		// Milliseconds between timer ticks
		Timer t = new Timer(DELAY, event -> {
			
			for(int i=0;i<list.size();i++) {
				list.get(i).move();
			}
			label.repaint();
		});
		t.start();
	}


}

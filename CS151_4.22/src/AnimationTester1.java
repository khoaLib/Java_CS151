import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This program implements an animation that moves a car shape.
 */
public class AnimationTester1 {
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		final MoveableShape shape = new CarShape(0, 50, CAR_WIDTH);

		ShapeIcon icon = new ShapeIcon(shape, ICON_WIDTH, ICON_HEIGHT);

		final JLabel label = new JLabel(icon);
		frame.setLayout(new FlowLayout());
		frame.add(label);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		final int DELAY = 100;
		// Milliseconds between timer ticks
		Timer t = new Timer(DELAY, event -> {

			if (shape.getX() < ICON_WIDTH) { // if car still remain not dissapear from frame
				shape.move(); // keep moving
			} else { // out of frame
				shape.translate(-ICON_WIDTH, 0); // get it back to the starting location and keep running
			}
			label.repaint();
		});
		t.start();
	}

	private static final int ICON_WIDTH = 300;
	private static final int ICON_HEIGHT = 200;
	private static final int CAR_WIDTH = 100;
}

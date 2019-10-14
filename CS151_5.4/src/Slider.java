import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * I read about JSlider and changeListerner in the following website:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/slider.html?fbclid
 * =IwAR3g6k1xGzGevkuIv9_C_uFhGrWQT1pwrLYqDSyMP0IUWRcjXqN9PiGPIgI
 * It just explain what the function work*/
public class Slider {
	private static int sliderValue = 0;

	public static void main(String[] args) {
		CarIcon c = new CarIcon(100);
		JFrame frame = new JFrame();
		JLabel label = new JLabel(c);
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 200, 0); //set the horizontal site of Slider,
																	//from 0->200,start at 0

		frame.setSize(500, 600);
		frame.add(label);
		slider.setMajorTickSpacing(10);//maximum tickValue
		slider.setMinorTickSpacing(1);//minumum tick value
		slider.setPaintTicks(true); 
		slider.setPaintLabels(true);
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {
				JSlider newValue = (JSlider) event.getSource();
				int num = newValue.getValue();
				if (sliderValue < num) {
					c.zoomin();
					sliderValue =num;
				} else {
					c.zoomout();
					sliderValue=num;
				}
				label.repaint();
			}

		});

		frame.add(slider, BorderLayout.SOUTH); //SLider appears at the bottom of frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setVisible(true);
	}

}

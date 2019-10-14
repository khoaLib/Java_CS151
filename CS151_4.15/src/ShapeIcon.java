import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

public class ShapeIcon implements Icon {
	private int width;
	private int high;
	private ZoomShape zooms;
	public ShapeIcon(ZoomShape a, int width, int height) {
		this.width =width;
		this.high=height;
		this.zooms =a;
	}

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return high;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public void paintIcon(Component arg0, Graphics g, int arg2, int arg3) {
		Graphics2D g2 = (Graphics2D) g;
		zooms.draw(g2);
		
	}

}

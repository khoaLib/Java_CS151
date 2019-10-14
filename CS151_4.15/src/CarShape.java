import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
/*
 * CarShape class code from book codes*/
public class CarShape implements ZoomShape{
	private int x;
	private int y;
	private int width;
	
	public CarShape(int x,int y,int width) {
		this.x=x;
		this.y=y;
		this.width=width;
	}
	
	public void zoomin() {
		width-=5;
	}
	
	public void zoomout() {
		width+=5;
	}
	public void draw(Graphics2D g) {
		Rectangle2D.Double body = new Rectangle2D.Double(x,y+width/6,width-1,width/6);
		Ellipse2D.Double frontTire = new Ellipse2D.Double(x+width/6, y+width/3, width/6, width/6);
		Ellipse2D.Double rearTire = new Ellipse2D.Double(x+width*2/3, y+width/3, width/6, width/6);
		
		Point2D.Double r1 = new Point2D.Double(x+width/6, y+width/6);
		Point2D.Double r2 = new Point2D.Double(x+width/3,y);
		Point2D.Double r3 = new Point2D.Double(x+width*2/3,y);
		Point2D.Double r4 = new Point2D.Double(x+width*5/6,y+width/6);
		
		Line2D.Double frontWindshield = new Line2D.Double(r1,r2);
		Line2D.Double roofTop = new Line2D.Double(r2, r3);
		Line2D.Double rearWindshield  = new Line2D.Double(r3, r4);
		
		
		g.draw(body);
		g.draw(frontTire);
		g.draw(rearTire);
		g.draw(frontWindshield);
		g.draw(roofTop);
		g.draw(rearWindshield);
	}

}

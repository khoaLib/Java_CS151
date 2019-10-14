  import java.awt.*;
  public interface MoveableShape
{
     void draw(Graphics2D g2);
     void move();
     void translate(int a, int b);
     int getX();
}

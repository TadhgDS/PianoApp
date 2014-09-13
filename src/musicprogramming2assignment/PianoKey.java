package musicprogramming2assignment;




import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;


public class PianoKey extends Component {
    private Color color;
    private final Color defaultColor;
    private int height;
    private int width;
    private final int x;
    private final int y;
    private final float frequency;
    private final int unique;
    public boolean pressed;

    public PianoKey(Color col, int midiKey, int X, int Y, int w, int h) {
        pressed = false;
        unique = midiKey;
        x = X;
        y = Y;
        height = h;
        width = w;
        defaultColor = color = col;
        frequency = 440.0f * (float)Math.pow(2.0,(midiKey - 69.0) / 12.0);
    }

    public float frequency() {
        return frequency;
    }

    public Color color() {
        return color;
    }

    public void setColor(Color col) {
        color = col;
    }

    public void resetColor() {
        color = defaultColor;
    }

    public int getId() {
        return unique;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    @Override
    public boolean contains(Point p) {
        return (p.x >= x) && (p.x < (x + width)) && (p.y >= y) && (p.y < (y + height));
    }
}

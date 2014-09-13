package musicprogramming2assignment;



import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionAdapter;
import musicprogramming2assignment.synthesizer.*;


public class Piano extends JPanel implements MouseListener, KeyListener {
    public boolean mouseOver = false;

    public final static int MAX_PIANO_KEYS = 88;
    public final static int MIDI_FIRST_NOTE = 21;

    private final static int KEY_WIDTH = 16;
    private final static int KEY_HEIGHT = 80;

    public static int selectedkey = -1;
    public static int SCALETYPE = -1;
    public static int[] cArray;
    
    public static PianoKey[] keys = new PianoKey[MAX_PIANO_KEYS];

    public Piano() {
        setLayout(new BorderLayout());
        int midiNote = MIDI_FIRST_NOTE;
        int x = 0;
        int y = 0;
      
        ArrayList<PianoKey> allKeysOrdered = new ArrayList<PianoKey>();
        
        for (int i=0; i<MAX_PIANO_KEYS; i++) {
            if (i != 1 && i != 4 && i != 6 && i != 9 && i != 11 && i != 13 && i != 16 && i != 18 && i != 21 && i != 23 && i != 25 && i != 28 && i != 30 && i != 33 && i != 35 && i != 37 && i != 40 && i != 42 && i != 45 && i != 47 && i != 49 && i != 52 && i != 54 && i != 57 && i != 59 && i != 61 && i != 64 && i != 66 && i != 69 && i != 71 && i != 73 && i != 76 && i != 78 && i != 81 && i != 83 && i != 85) {
        
                allKeysOrdered.add( new PianoKey(Color.WHITE, midiNote, x, y, KEY_WIDTH, KEY_HEIGHT) );
                x += KEY_WIDTH;
            } else {
         
                allKeysOrdered.add( new PianoKey(Color.BLACK, midiNote, x - KEY_WIDTH/2, y, KEY_WIDTH - KEY_WIDTH/2, KEY_HEIGHT/2) );
            }
            midiNote++;
        }
        
     
        for (int i = 0; i < allKeysOrdered.size(); i++){
        	keys[i] = allKeysOrdered.get(i);
        }

        addKeyListener(this);
        addMouseListener(this);
        setSize(80, 9999);
        setFocusable(true);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
                public void mouseMoved(MouseEvent e) {
                    if (!mouseOver) {
                        return;
                    }
                    releaseAll();
                    for (int i= keys.length - 1; i >= 0; i--) {
                        if (keys[i].contains(e.getPoint()) ) {
                            pressedSimple(i);
                            break;
                        }
                    }
                }
        });
    }

    @Override
    public void paint(Graphics g) {
        for (int i=0; i<MAX_PIANO_KEYS; i++) {
            keys[i].paint(g);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (mouseOver) {
            return;
        }
        
        for (int i= keys.length - 1; i >= 0; i--) {
            if (keys[i].contains(e.getPoint()) ) {
                if(keys[i].pressed) {
                    release(i);
                } else {
                   pressed(i);
                   selectedkey = i;
                }
                break;
            }
        }
    }
    
    public void mouseReleased(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {
        if(mouseOver) {
            releaseAll();
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A) pressed(19);
        if(key == KeyEvent.VK_S) pressed(20);
        if(key == KeyEvent.VK_D) pressed(21);
        if(key == KeyEvent.VK_F) pressed(22);
        if(key == KeyEvent.VK_G) pressed(23); 
        if(key == KeyEvent.VK_H) pressed(24);
        if(key == KeyEvent.VK_J) pressed(25);
        if(key == KeyEvent.VK_K) pressed(26);
        if(key == KeyEvent.VK_L) pressed(27);
    }
    

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A) release(19);
        if(key == KeyEvent.VK_S) release(20);
        if(key == KeyEvent.VK_D) release(21);
        if(key == KeyEvent.VK_F) release(22);
        if(key == KeyEvent.VK_G) release(23);
        if(key == KeyEvent.VK_H) release(24);
        if(key == KeyEvent.VK_J) release(25);
        if(key == KeyEvent.VK_K) release(26);
        if(key == KeyEvent.VK_L) release(27);
    }

    public void release(int num) {
        
    	
        keys[num].resetColor();
        keys[num].pressed = false;
        this.repaint();
    }
    public static double[] note(double hz, double duration, double amplitude) {
        int N = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[N+1];
        for (int i = 0; i <= N; i++)
            a[i] = amplitude * Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
        return a;
    }

    public void pressed(int num) {
        
     
        int steps = keys[num].getId();
        keys[num].pressed = true;
        keys[num].setColor(Color.CYAN);
       
        double hz = 16.35 * Math.pow(2, steps/ 12.0);
        StdAudio.play(note(hz, 0.75, 2.0));
        System.out.println(hz);
        
        
        
        this.repaint();
    }

    private void pressedSimple(int num) {


    	int steps = keys[num].getId();
        keys[num].pressed = true;
        keys[num].setColor(Color.CYAN);
        
        double hz = 16.35 * Math.pow(2, steps/ 12.0);
        StdAudio.play(note(hz, 0.4, 2.0));
        
        
        
        this.repaint();
    }

    public void releaseAll() {
        for(int i=0; i<keys.length; i++) {
            keys[i].pressed = false;
            keys[i].resetColor();
        }
      
        repaint();
    }

    public void pressAll() {
        for(int i=0; i<keys.length; i++) {
            keys[i].pressed = true;
            keys[i].setColor(Color.CYAN);
            
            int steps = keys[i].getId();
            
            double hz = 16.35 * Math.pow(2, steps/ 12.0);
            StdAudio.play(note(hz, 0.75, 2.0));
        }
        
        repaint();
    }
    
    public void playScale() {
    	
    	SCALETYPE = 0;

        PlayScalesLogic playScalesLogic = new PlayScalesLogic(this);
        Thread myNewThread = new Thread(playScalesLogic);
        myNewThread.start();

	}

    public void playMinorScale() {
    	
    	SCALETYPE = 1;

        PlayScalesLogic playScalesLogic = new PlayScalesLogic(this);
        Thread myNewThread = new Thread(playScalesLogic);
        myNewThread.start();

	}
    
    public void playArpeggio() {
	
    	SCALETYPE = 2;

    	PlayScalesLogic playScalesLogic = new PlayScalesLogic(this);
    	Thread myNewThread = new Thread(playScalesLogic);
    	myNewThread.start();

}
    
  

    public void keyTyped(KeyEvent e) {
        
    }
}

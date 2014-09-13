package musicprogramming2assignment.synthesizer;

import musicprogramming2assignment.Piano;

import java.awt.*;
import java.util.Arrays;


public class PlayScalesLogic implements Runnable {
    private Piano piano;

    public PlayScalesLogic(Piano p) {
        this.piano = p;
    }

    @Override
    public void run() {
    	
    	int[] majorArray = {Piano.selectedkey, Piano.selectedkey + 2, Piano.selectedkey + 4, Piano.selectedkey + 5, Piano.selectedkey + 7, Piano.selectedkey + 9, Piano.selectedkey + 11, Piano.selectedkey + 12};
    	int[] minorArray = {Piano.selectedkey, Piano.selectedkey + 2, Piano.selectedkey + 3, Piano.selectedkey + 5, Piano.selectedkey + 7, Piano.selectedkey + 8, Piano.selectedkey + 11, Piano.selectedkey + 12};
    	int[] arpeggioArray = {Piano.selectedkey, Piano.selectedkey + 4, Piano.selectedkey + 7, Piano.selectedkey + 12, Piano.selectedkey + 7, Piano.selectedkey + 4, Piano.selectedkey };
    	
    	
    	int[] cArray = {};
    	
    	if(Piano.SCALETYPE == 0)
    	{
    		cArray = Arrays.copyOfRange(majorArray, 0, majorArray.length);
    	}
    	else if(Piano.SCALETYPE == 1)
    	{
    		cArray = Arrays.copyOfRange(minorArray, 0, minorArray.length);
    	}
    	
    	else if(Piano.SCALETYPE == 2)
    	{
    		cArray = Arrays.copyOfRange(arpeggioArray, 0, arpeggioArray.length);
    	}
    	
    	
    	


        for(int i=0; i<=cArray.length; i++)
        {

        	
        	int steps = Piano.keys[cArray[i]].getId();
            
            
            
            Piano.keys[cArray[i]].pressed = true;

           

            this.piano.releaseAll();

            Piano.keys[cArray[i]].setColor(Color.CYAN);
            double hz = 16.35 * Math.pow(2, steps/ 12.0);
            StdAudio.play(Piano.note(hz, 1.0, 2.0));
            piano.repaint();

        }
    }
}

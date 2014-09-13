package musicprogramming2assignment;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class SynthesizerGui extends JFrame {
    Piano piano;

    JPanel controls = new JPanel();
    JPanel misc = new JPanel();
    JPanel scales = new JPanel();
    JPanel names = new JPanel();
    
    
    JLabel lblMiscOptions = new JLabel("Misc Options");
    JLabel lblScales = new JLabel("Scales(Select a key - then tick box)");
    JLabel lblNames = new JLabel("Created for CS322 in 2013/14 by: ");
    JLabel james = new JLabel("James Quinn - 69393617");
    JLabel tadhg = new JLabel("Tadhg da Silva");
   
    
    JCheckBox playScale= new JCheckBox("Play Major Scale");
    JCheckBox playMinorScale= new JCheckBox("Play Minor Scale");
    JCheckBox playArpeggio= new JCheckBox("Play Arpeggio");
    JCheckBox chkMouseOver = new JCheckBox("Hover Over?");
    JCheckBox chkAllNotes = new JCheckBox("All notes on?");



    /** Creates new form SynthesizerGui */
    public SynthesizerGui() {
        setTitle("Synthesizer");
        setLayout(null);
        piano = new Piano();
        piano.setVisible(true);
        setVisible(true);
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {System.exit(0);}

            @Override
            public void windowOpened(WindowEvent e) { piano.requestFocus(); }
        });

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = 841;
        int h = 260;
        setLocation(screenSize.width/2 - w/2, screenSize.height/2 - h/2);
        setSize(w, h);
        setResizable(false);
        getContentPane().setBackground(Color.black);
        setFocusable(false);
        initComponents();

        ActionListener playScale1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (playScale.isSelected()) piano.playScale();
                else piano.releaseAll();
            }
        };
        
        ActionListener playMinorScale1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (playMinorScale.isSelected()) piano.playMinorScale();
                else piano.releaseAll();
            }
        };
        
        ActionListener playArpeggio1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (playArpeggio.isSelected()) piano.playArpeggio();
                else piano.releaseAll();
            }
        };
        

        ActionListener changeNoteOn = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                piano.releaseAll();
                piano.mouseOver = chkMouseOver.isSelected();
            }
        };

        ActionListener changeAllNoteOn = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chkAllNotes.isSelected()) piano.pressAll();
                else piano.releaseAll();
            }
        };


        chkMouseOver.addActionListener(changeNoteOn);
        chkAllNotes.addActionListener(changeAllNoteOn);
        playScale.addActionListener(playScale1);
        playMinorScale.addActionListener(playMinorScale1);
        playArpeggio.addActionListener(playArpeggio1);
    }


    public void initComponents() {
        controls.setLayout(null);
        
        piano.setBounds(0, 0, 841, 85);
        add(piano);
        controls.setBounds(0, 85, 841, 220);

        
        
        lblMiscOptions.setBounds(0, 0, 150, 20);
        chkAllNotes.setBounds(0, 20, 150, 20);
        chkMouseOver.setBounds(0, 40, 150, 20);
        misc.setBounds(350, 10, 200, 150);
        misc.add(lblMiscOptions);
        misc.add(chkAllNotes);
        misc.add(chkMouseOver);
        



        names.setName("names");
        names.setBounds(10,10,200,80);
        lblNames.setBounds(0,0,200,20);
        james.setBounds(0,20,150,20);
        tadhg.setBounds(0,40,150,20);
        names.add(lblNames);
        names.add(james);
        names.add(tadhg);

        
        scales.setName("Scales");
        scales.setBounds(550, 10, 250, 250);
        lblScales.setBounds(10, 10, 400, 100);
        playScale.setBounds(0, 0, 400, 30);
        playMinorScale.setBounds(0, 50, 400, 30);
        playArpeggio.setBounds(0, 100, 400, 30);   
        scales.add(lblScales);
        scales.add(playScale);
        scales.add(playMinorScale);
        scales.add(playArpeggio);
      
        
        

        controls.add(misc);
        controls.add(scales);
        controls.add(names);
        add(controls);
        
        style(controls);
        style(misc);
        style(names);
        style(scales);
        
        
        style2(playScale);
        style2(playMinorScale);
        style2(playArpeggio);
        style2(chkMouseOver);
        style2(chkAllNotes);
   
        
        style4(lblMiscOptions);  
        style4(lblScales);
        style4(lblNames);
        style4(james);
        style4(tadhg);
        
        
    }
    
    public static void style(JPanel s)
    {
    	s.setBackground(Color.black);
    	s.setForeground(Color.white);
    }
    
    public static void style2(JCheckBox s)
    {
    	s.setBackground(Color.black);
    	s.setForeground(Color.white);
    }

    public static void style4(JLabel s)
    {
    	s.setBackground(Color.black);
    	s.setForeground(Color.white);
    }
    
    public static void main(String args[]) {
        new SynthesizerGui().setVisible(true);
    }

}

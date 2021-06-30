package jezz.forestfiresimulation.display.awt;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jezz.forestfiresimulation.display.Display;
import jezz.forestfiresimulation.engine.Cell;
import jezz.forestfiresimulation.engine.Engine;
import jezz.forestfiresimulation.engine.FireState;

/**
 *
 * @author jezz
 */
public class AwtDisplay extends Display {
    
    private Map<FireState, String> fireStateSymbol = new EnumMap<FireState,String>(FireState.class);
	{
		fireStateSymbol.put(FireState.NO_FIRE, "_");
		fireStateSymbol.put(FireState.ON_FIRE, "#");
		fireStateSymbol.put(FireState.BURNT,   ".");
	}
    
    public AwtDisplay(Engine engine) {
        super(engine);
        init();
    }
    
    JFrame frame;
    JPanel panelForest;
    JLabel[][] labels;
    JLabel timeLabel;
    JLabel nbBurntLabel;
    JLabel nbFireLabel;
    
    void init() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new GridLayout(2, 1, 0, 10));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        //========================
        
        panelForest = new JPanel();
        panelForest.setLayout(new GridLayout(engine.getForest().height, engine.getForest().width));
        
        labels = new JLabel[engine.getForest().height][engine.getForest().width];
        Cell[][] cells = engine.getForest().getCells();
        for (int y=0; y<cells.length; y++){
            for (int x=0; x<cells[y].length; x++){
                JLabel l = new JLabel("_");
                l.setHorizontalAlignment(JLabel.CENTER);
                labels[y][x] = l;
                panelForest.add(l);
            }
        }
        
        frame.add(panelForest);
        
        //========================
        
        JPanel panelState = new JPanel();
        panelState.setLayout(new FlowLayout());
        
        panelState.add(new JLabel("time:"));
        timeLabel = new JLabel("");
        panelState.add(timeLabel);
        
        panelState.add(new JLabel("nb burnt:"));
        nbBurntLabel = new JLabel("");
        panelState.add(nbBurntLabel);
        
        panelState.add(new JLabel("nb fire:"));
        nbFireLabel = new JLabel("");
        panelState.add(nbFireLabel);
        
        frame.add(panelState);
        
        //========================
        
        JPanel panelButton = new JPanel();
        //panelButton.setLayout(new GridLayout(1, 2, 10, 0));
        panelButton.setLayout(new FlowLayout());
        
        JButton resetButton = new JButton("reset");
        resetButton.addActionListener(e -> {
            engine.reset();
            displayStates();
            displayForest();
        });
        panelButton.add(resetButton);
        
        JButton stepButton = new JButton("step");
        stepButton.addActionListener(e -> {
            runOneStep();
        });
        panelButton.add(stepButton);
        
        JButton fullButton = new JButton("end");
        fullButton.addActionListener(e -> {
            runFull();
        });
        panelButton.add(fullButton);
        frame.add(panelButton);
        
        //========================
        
        frame.setSize(300, 300);
        frame.pack();
        frame.setVisible(true);
        
        
    }
    
    @Override
    public void displayStates() {
        //
        timeLabel.setText(""+engine.getTime());
        nbBurntLabel.setText(""+engine.getForest().getBurntCellsCount());
        nbFireLabel.setText(""+engine.getForest().getOnFireCellsCount());
    }

    @Override
    public void displayForest() {
        Cell[][] cells = engine.getForest().getCells();
        for (int y=0; y<labels.length; y++){
            for (int x=0; x<labels[y].length; x++){
                labels[y][x].setText(fireStateSymbol.get(cells[y][x].getFireState()));
            }
        }
    }
    
    @Override
    public void run(String[] args){
        // display initial state
        displayStates();
        displayForest();
    }
    
    public void runOneStep(){
        engine.step();
        displayStates();
        displayForest();
    }
    
    public void runFull(){
        displayStates();
		displayForest();
		engine.stepToEnd();
		displayStates();
		displayForest();
    }
    
}

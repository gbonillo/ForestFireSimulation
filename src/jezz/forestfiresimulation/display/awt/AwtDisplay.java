package jezz.forestfiresimulation.display.awt;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.EnumMap;
import java.util.Map;
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
 * Affichage de la simulation dans une fenêtre avec des boutons de contrôle
 * 
 * @author jezz
 */
public class AwtDisplay extends Display {
    
    private Map<FireState, String> fireStateSymbol = new EnumMap<FireState,String>(FireState.class);
    private Map<FireState, Color> fireStateColor = new EnumMap<FireState,Color>(FireState.class);
	{
		fireStateSymbol.put(FireState.NO_FIRE, "🌲");
        fireStateColor.put(FireState.NO_FIRE, new Color(0, 127, 95)); // 55a630
		fireStateSymbol.put(FireState.ON_FIRE, "🔥");
        fireStateColor.put(FireState.ON_FIRE, new Color(249, 65, 68)); // F94144
		fireStateSymbol.put(FireState.BURNT,   ".");
        fireStateColor.put(FireState.BURNT, Color.gray);
	}
    
    public AwtDisplay(Engine engine) {
        super(engine);
        init();
    }
    
    JFrame frame;
    JLabel[][] cellLabels;
    JLabel timeLabel;
    JLabel nbBurntLabel;
    JLabel nbFireLabel;
    
    void init() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new GridLayout(2, 1, 0, 10));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        //========================
        
        JPanel panelForest = new JPanel();
        panelForest.setLayout(new GridLayout(engine.getForest().height, engine.getForest().width));
        
        cellLabels = new JLabel[engine.getForest().height][engine.getForest().width];
        Cell[][] cells = engine.getForest().getCells();
        for (int y=0; y<cells.length; y++){
            for (int x=0; x<cells[y].length; x++){
                JLabel l = new JLabel("");
                l.setHorizontalAlignment(JLabel.CENTER);
                cellLabels[y][x] = l;
                //cellLabels[y][x].setForeground(Color.green);
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
            runToEnd();
        });
        panelButton.add(fullButton);
        frame.add(panelButton);
        
        //========================
        
        frame.pack();
        frame.setSize(400, 300);
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
        for (int y=0; y<cellLabels.length; y++){
            for (int x=0; x<cellLabels[y].length; x++){
                FireState s = cells[y][x].getFireState();
                cellLabels[y][x].setText(fireStateSymbol.get(s));
                cellLabels[y][x].setForeground(fireStateColor.get(s));
            }
        }
    }
    
    @Override
    public void run(String[] args){
        // display initial state
        displayStates();
        displayForest();
    }
    
}

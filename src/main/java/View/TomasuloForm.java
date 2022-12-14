package View;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.List;
import java.awt.Color;


import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.ArquiveController;
import Controller.InstructionQueueController;
import Model.Instruction;


public abstract class TomasuloForm extends JFrame{
  

    private static final JLabel instructions = new JLabel("INSTRUÇÕES");


    protected JPanel sideBar;    
    protected JPanel footer;
    protected JPanel flow;
    protected JPanel areaInstructions;
    protected JPanel containerTomasulo;

    protected JButton step;

    protected Instruction [] instructionList;
    
    public TomasuloForm(){
        this.initializer();
    }

    private void initializer(){

        this.setTitle("Simulador Tomasulo");
        this.setSize(1280, 840);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);              
    
        this.getContentPane().add(getContainerTomasulo());         
    }

    

    public JPanel getContainerTomasulo() {
        
        if(containerTomasulo == null){
        
            containerTomasulo = new JPanel();
            containerTomasulo.setLayout(new GridLayout(0,2));

            containerTomasulo.setAlignmentX(CENTER_ALIGNMENT);

            containerTomasulo.add(getSideBar()); // coluna 1
            containerTomasulo.add(getFlow()); // coluna 2
        }

        return containerTomasulo;
    }

    public JPanel getSideBar() {

        if (sideBar == null){
            
            sideBar = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            sideBar.setBackground(Color.GREEN);                 
            
            //sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.PAGE_AXIS));            
                        
            sideBar.add(instructions);           
            
            // List<Instruction> i = ArquiveController.getAllInstructions();
            // for (Instruction instruction : i) {     
            //     System.out.println("Form  "+ instruction);           
            //     sideBar.add(new JLabel(instruction.getAllStringInstruction()));
            // }
            
        }

        return sideBar;
    }

    public JPanel newComponet(){
        return new JPanel();
    }

    public JPanel getFlow() {

        if(flow == null){
            flow = new JPanel(new GridLayout(2,0));   
            
            flow.add(getAreaInstructions());
            flow.add(getFooter());
        }

        return flow;
    }

    public JPanel getAreaInstructions(){

        if(areaInstructions == null){
            areaInstructions = new JPanel();

            areaInstructions.add(new JLabel("IS Area"));
            
            areaInstructions.setBackground(Color.WHITE); 
        }

        return areaInstructions;
    }

    public JPanel getFooter() {

        if(footer == null){
            footer = new JPanel();
            step = new JButton("Step");
            
            footer.add(step);

            footer.setBackground(Color.ORANGE); 
        }

        return footer;
    }

}

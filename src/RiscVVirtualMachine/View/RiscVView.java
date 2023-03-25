package RiscVVirtualMachine.View;

import java.io.File;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiscVView extends JFrame{
    private JCheckBox branchCheckBox;
    private JCheckBox memWriteCheckBox;
    private JCheckBox memReadCheckBox;
    private JCheckBox ALUOpCheckBox;
    private JCheckBox ALUSrcCheckBox;
    private JCheckBox memToRegCheckBox;
    private JCheckBox regWriteCheckBox;
    private JTextArea regDisplay;
    private JTextArea memDisplay;
    private JPanel mainPanel;
    private JTextField PCField;
    private JLabel PCLabel;
    private JButton nextInstructionButton;
    private JButton newFileButton;
    private JScrollPane memScroll;
    private JScrollPane regScroll;
    private JFileChooser fileOpener;

    public RiscVView(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setSize(700,600);

        fileOpener = new JFileChooser();

        setResizable(false);
        setVisible(true);
    }

    public void buttonActionListener(ActionListener al){
        newFileButton.setActionCommand("newFile");
        newFileButton.addActionListener(al);
        nextInstructionButton.setActionCommand("execute");
        nextInstructionButton.addActionListener(al);
    }

    public File openFile(){
        int returnValue = fileOpener.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = fileOpener.getSelectedFile();
            return file;
        }
        return null;
    }

    public void finishedInstructions(){
        JOptionPane.showMessageDialog(this, "Instruções finalizadas!","Alerta!",JOptionPane.ERROR_MESSAGE);
    }

    public void updateRegs(int[] regs){
        regDisplay.setText("");
        for (int i = 0; i < regs.length; i++){
            regDisplay.append("x"+i+" = "+regs[i]+"\n");
        }
        regScroll.getVerticalScrollBar().setValue(0);
    }

    public void updateMem(int[] mem){
        memDisplay.setText("");
        for (int i = 0; i < mem.length; i++){
            memDisplay.append("x"+i*4+" = "+mem[i]+"\n");
        }
        memScroll.getVerticalScrollBar().setValue(0);
    }

    public void updatePC(int pc){
        PCField.setText(Integer.toString(pc));
    }

}

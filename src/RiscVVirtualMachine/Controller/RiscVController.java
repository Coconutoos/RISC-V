package RiscVVirtualMachine.Controller;

import RiscVVirtualMachine.Model.RiscV;
import RiscVVirtualMachine.View.RiscVView;

import javax.swing.*;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RiscVController implements ActionListener {
    RiscVView view;
    RiscV riscV;
    public RiscVController(RiscVView view, RiscV riscV){
        this.view = view;
        this.riscV = riscV;
        view.buttonActionListener(this);    //Add actionlistener from view to this
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        String action = ae.getActionCommand();
        if(action.equals("newFile")){
            File file = view.openFile();            //To do:
            if(file == null) return;
            riscV = new RiscV();                    //Create new machine
            try {
                riscV.loadInstructionFile(file);    //Load instructions file into machine
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if(action.equals("execute")){
            if(riscV.clock());
            else{
                view.finishedInstructions();
            }
        }
        updateView();                           //Get parameters from machine and update whole view
    }
    private void updateView(){
        view.updateRegs(riscV.getRegs());
        view.updateMem(riscV.getMem());
        view.updatePC(riscV.getPC());
    }
}

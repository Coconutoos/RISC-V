package RiscVVirtualMachine;

import RiscVVirtualMachine.Controller.RiscVController;
import RiscVVirtualMachine.Model.RiscV;
import RiscVVirtualMachine.View.RiscVView;

public class RiscVVM {
    public static void main(String[] args) {
        RiscVView view = new RiscVView();
        RiscV riscV = new RiscV();
        RiscVController controller = new RiscVController(view, riscV);
    }
}
package RiscVVirtualMachine.Model;

public class ControlFlag {
    private boolean value;
    public void ControlFlag(){}

    public void write(boolean value){
        this.value = value;
    }
    public boolean read(){
        return value;
    }
}

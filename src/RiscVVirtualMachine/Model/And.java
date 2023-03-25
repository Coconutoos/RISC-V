package RiscVVirtualMachine.Model;

public class And {
    private ControlFlag in1, in2, out;
    And(ControlFlag in1, ControlFlag in2){
        this.in1 = in1;
        this.in2 = in2;
        out = new ControlFlag();
    }
    public ControlFlag getOut(){
        return this.out;
    }
    public void clock(){
        out.write(in1.read() && in2.read());
    }
}

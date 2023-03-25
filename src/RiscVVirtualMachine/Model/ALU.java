package RiscVVirtualMachine.Model;

public class ALU {

    public static final int ADD_OP = 2;
    public static final int SUB_OP = 6;
    public static final int AND_OP = 0;
    public static final int OR_OP = 1;
    private Buffer operative1, operative2, aluRes, inControl;
    ControlFlag zero;
    public ALU(Buffer op1, Buffer op2, Buffer inControl){
        aluRes = new Buffer();
        zero = new ControlFlag();
        operative1 = op1;
        operative2 = op2;
        this.inControl = inControl;
    }
    public Buffer getAluRes(){
        return this.aluRes;
    }

    public ControlFlag getZero(){
        return  this.zero;
    }

    public void clock(){
        switch (inControl.read()) {
            case ADD_OP -> aluRes.write(operative1.read() + operative2.read());
            case SUB_OP -> aluRes.write(operative1.read() - operative2.read());
            case AND_OP -> aluRes.write(operative1.read() & operative2.read());
            case OR_OP -> aluRes.write(operative1.read() | operative2.read());
        }
        zero.write((aluRes.read() == 0) ? true : false);
    }
}

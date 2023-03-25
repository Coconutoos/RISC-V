package RiscVVirtualMachine.Model;

public class Control {
    public static final int R_OPCODE = 51, I_OPCODE = 19, S_OPCODE = 35, B_OPCODE = 99, l_OPCODE = 3;
    private Buffer opcode;
    private ControlFlag branch;
    private ControlFlag memRead;
    private ControlFlag memToReg;
    private Buffer aluOp;
    private ControlFlag memWrite;
    private ControlFlag aluSrc;
    private ControlFlag regWrite;

    public Control(Buffer opcode){
        this.opcode = opcode;
        branch = new ControlFlag();
        memRead = new ControlFlag();
        memToReg = new ControlFlag();
        aluOp = new Buffer();
        memWrite = new ControlFlag();
        aluSrc = new ControlFlag();
        regWrite = new ControlFlag();
        branch.write(false);
        memRead.write(false);
        memToReg.write(false);
        aluOp.write(0);
        memWrite.write(false);
        aluSrc.write(false);
        regWrite.write(false);
    }
    public void clock(){
        switch(opcode.read()){
            case I_OPCODE -> {
                branch.write(false);
                memRead.write(false);
                memToReg.write(false);
                aluOp.write(0);
                memWrite.write(false);
                aluSrc.write(true);
                regWrite.write(true);
            }
            case B_OPCODE -> {
                branch.write(true);
                memRead.write(false);
                memToReg.write(false);
                aluOp.write(1);
                memWrite.write(false);
                aluSrc.write(false);
                regWrite.write(false);
            }
            case R_OPCODE -> {
                branch.write(false);
                memRead.write(false);
                memToReg.write(false);
                aluOp.write(2);
                memWrite.write(false);
                aluSrc.write(false);
                regWrite.write(true);
            }
            case l_OPCODE -> {
                branch.write(false);
                memRead.write(true);
                memToReg.write(true);
                aluOp.write(0);
                memWrite.write(false);
                aluSrc.write(true);
                regWrite.write(true);
            }
            case S_OPCODE -> {
                branch.write(false);
                memRead.write(false);
                memToReg.write(false);
                aluOp.write(0);
                memWrite.write(true);
                aluSrc.write(true);
                regWrite.write(false);
            }
        }
    }
    public ControlFlag getMemRead() {
        return memRead;
    }

    public ControlFlag getMemToReg() {
        return memToReg;
    } // mux memory

    public Buffer getAluOp() {
        return aluOp;
    } //aluControl

    public ControlFlag getMemWrite() {
        return memWrite;
    }

    public ControlFlag getAluSrc() {
        return aluSrc;
    } // mux reg

    public ControlFlag getRegWrite() {
        return regWrite;
    }
    public ControlFlag getBranch() {
        return branch;
    } // and
}

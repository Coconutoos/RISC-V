package RiscVVirtualMachine.Model;

public class aluControl {
    private Buffer instruction, controlOut, aluOp;
    public aluControl(Buffer instruction, Buffer aluOp){
        this.instruction = instruction;
        this.aluOp = aluOp;
        controlOut = new Buffer();
    }
    public Buffer getControlOut() {
        return controlOut;
    }

    public void clock(){
        int op = aluOp.read();

        if(op == 0)
            controlOut.write(ALU.ADD_OP);
        else if (op == 1)
            controlOut.write(ALU.SUB_OP);

        else if(op == 2){
            int instruction = this.instruction.read();
            int aux = (instruction >> 12) & 0x7;
            int aux2 = ((instruction >> 30) & 0x1) << 3;
            aux |= aux2;
            switch (aux){
                case 0 -> controlOut.write(ALU.ADD_OP);
                case 8, 7 -> controlOut.write(ALU.AND_OP);
                case 6 -> controlOut.write(ALU.OR_OP);
            }
        }
    }
}

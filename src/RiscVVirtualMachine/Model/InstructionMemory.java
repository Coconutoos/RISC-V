package RiscVVirtualMachine.Model;

public class InstructionMemory extends Memory{
    private int maxInstructions;
    InstructionMemory(int size, Buffer pc, int max){
        super(size, pc);
        maxInstructions = max;
    }
    public void clock(){
        //System.out.println("Internal pc " + address.read());
        readData.write(memory[(address.read())/4]);
    }

    public int getMaxInstructions() {
        return maxInstructions;
    }

    public void setMaxInstructions(int maxInstructions) {
        this.maxInstructions = maxInstructions;
    }
}

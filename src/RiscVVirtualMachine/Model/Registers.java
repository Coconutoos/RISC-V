package RiscVVirtualMachine.Model;

public class Registers {
    public int[] getRegisters() {
        return registers;
    }

    private int[] registers;
    private Buffer inRs1;
    private Buffer inRd;
    private Buffer outRs1;
    private Buffer writeData;
    private Buffer inRs2, outRs2;
    private ControlFlag regWrite;
    public Registers(Buffer inRs1, Buffer inRs2, Buffer inRd, Buffer writeData, ControlFlag regWrite){
        this.inRs1 = inRs1;
        this.inRs2 = inRs2;
        this.inRd = inRd;
        this.writeData = writeData;
        this.regWrite = regWrite;
        outRs1 = new Buffer();
        outRs2 = new Buffer();
        registers = new int[32];
    }
    public Buffer getOutRs1() {
        return outRs1;
    }
    public Buffer getOutRs2() {
        return outRs2;
    }
    public void clock(){
        outRs1.write(registers[inRs1.read()]);
        outRs2.write(registers[inRs2.read()]);
        //printRegs();
    }
    public void printRegs(){
        for(int i = 0; i < 31; i++){
            System.out.println("x"+i+" = "+ registers[i]);
        }
    }
    public void fallingClock(){
        if(regWrite.read()){
            registers[inRd.read()] = writeData.read();
        }
    }
}

package RiscVVirtualMachine.Model;

public abstract class Memory {
    public final int MAX_INSTRUCTION_SIZE = 250;
    protected Buffer writeData, readData;
    public int size;
    protected int[] memory;
    protected Buffer address;

    Memory(int size, Buffer address){
        this.size = size;
        this.address = address;
        this.readData = new Buffer();
        memory = new int[size];
    }

    public Buffer getReadData() {
        return readData;
    }

    public void setMemory(int[] memory) {
        this.memory = memory;
    }
    public int[] getMemory(){
        return memory;
    }

    public void setAddress(Buffer address) {
        this.address = address;
    }
    public void setWriteData(Buffer in){
        writeData = in;
    }
}

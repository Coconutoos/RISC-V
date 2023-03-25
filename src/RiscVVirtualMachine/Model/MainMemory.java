package RiscVVirtualMachine.Model;

public class MainMemory extends Memory{

    private ControlFlag memWrite;
    private ControlFlag memRead;
    MainMemory(int size, Buffer writeData, ControlFlag memRead, ControlFlag memWrite, Buffer address){
        super(size, address);
        readData = new Buffer();
        this.writeData = writeData;
        this.memRead = memRead;
        this.memWrite = memWrite;
        memory = new int[size];
    }

    public void clock(){
        int address = this.address.read() / 4;
        if(memRead.read()){
            readData.write(memory[address]);
        }
        if(memWrite.read()){
            memory[address] = writeData.read();
        }
    }
}

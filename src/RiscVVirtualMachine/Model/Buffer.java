package RiscVVirtualMachine.Model;

public class Buffer {
    private int value;
    public void write(int value){
        this.value = value;
    }
    public int read(){
        return value;
    }
}

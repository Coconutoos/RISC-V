package RiscVVirtualMachine.Model;

public class Adder {
    private Buffer in1, in2, out;
    Adder(Buffer in1, Buffer in2){
        this.in1 = in1;
        this.in2 = in2;
        out = new Buffer();
    }
    public Buffer getOut(){
        return this.out;
    }
    public void clock(){
        out.write(in1.read() + in2.read());
        //System.out.println("Adder in 1 = " + in1.read());
        //System.out.println("Adder in 2 = " + in2.read());
        //System.out.println("Adder out = "  + out.read());
    }
}

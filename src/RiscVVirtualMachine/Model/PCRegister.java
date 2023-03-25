package RiscVVirtualMachine.Model;

public class PCRegister {
    private Buffer in, out;
    PCRegister(Buffer in){
        out = new Buffer();
        out.write(0);
        this.in = in;
    }
    public Buffer getOut(){
        return this.out;
    }
    public void setIn(Buffer in){
        this.in = in;
        //out.write(0);
    }
    public void clock(){
        //System.out.println("PC in before"+ in.read());
        //System.out.println("PC out before"+ out.read());
        out.write(in.read());
        //System.out.println("PC in after"+ in.read());
        //System.out.println("PC out after"+ out.read());
    }
}

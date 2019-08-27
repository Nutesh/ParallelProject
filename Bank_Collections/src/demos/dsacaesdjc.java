package demos;

class Super
{
 void show() throws ArithmeticException
 { System.out.println("parent"); }
}

public class dsacaesdjc extends Super 
{
  void show()
  { 
	  System.out.println("child"); } 

  public static void main( String[] args )
  {
   /* Super s = new dsacaesdjc();
    s.show();*/
	  /*long i;
	  System.out.println(i);*/
	 System.out.println(-2<<33);
	System.out.printf("kjhfw");
    
  }  
}
class A{
	int x;
	A(){
		
	}
}
class B extends A{
	int y;
	B(){
		super(); 
	}
}
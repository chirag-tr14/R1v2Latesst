package practise;

public class DiamondShape {

	public static void main(String[] args) {
		for(int i=0; i<=5;i++){
			
			for(int y=0;y<=5-i;y++){
			System.out.print(" ");
			}
			
			for(int j=0;j<=i;j++){
				System.out.print("* ");
			}
				System.out.println();
		}
		  for(int i=4; i>=0;i--){
			
			for(int y=0;y<=5-i;y++){
			System.out.print(" ");
			}
			
			for(int j=0;j<=i;j++){
				System.out.print("* ");
			}
				System.out.println();
		}
		
		
	}

}

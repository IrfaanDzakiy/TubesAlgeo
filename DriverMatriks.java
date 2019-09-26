import java.util.Scanner;

class DriverMatriks{
	public static void main(String[] args){
		PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		writer.println("The first line");
		writer.println("The second line");
		writer.close();
	}
}
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {

	private Scanner x;
	
	public void openFile() {
		try {
			x = new Scanner(new File("ColorBlocks.txt"));
		}
		catch (Exception e) {
			System.out.println("error could not find file");
		}
	}
	
	public ArrayList<String> readFile() {
		ArrayList<String> list = new ArrayList<String>();
		while (x.hasNext()) {
			list.add(x.next());
		}
		return list;
	}
	
	public void closeFile() {
		x.close();
	}
}

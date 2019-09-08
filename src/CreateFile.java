import java.util.Formatter;

public class CreateFile {

	private Formatter x;
	
	public CreateFile () {
	}
	
	public void openFile() {
		try {
			x = new Formatter("ColorBlocks.txt");
		}
		catch (Exception e) {
			System.out.println("error");
		}
	}
	
	public void addRecords() {
		x.format("" + Item.item.classicEasyScore + "\n");
		x.format("" + Item.item.classicMediumScore + "\n");
		x.format("" + Item.item.classicHardScore + "\n");
	}
	
	public void closeFile() {
		x.close();
	}
}

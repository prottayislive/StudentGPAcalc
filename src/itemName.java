
public class itemName {
	// declaring a pair of data to store the details of each item
	
	private String itemName;
	private int  itemCost;
	
	// method  SubjectDet stores the item details.
	public void SubjectsDet(String name, int marks){
		this.itemName=name;
		this.itemCost=marks;
			}
	//Method to return the value in itemCost variable outside this class.
	public int getMarks() {
		return this.itemCost;
	}
	
	//Method to return the value in itemName variable from Outside this class.
	public String getSub() {
		return this.itemName;
	}
}


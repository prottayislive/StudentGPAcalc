import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class dailySalesCalculator extends itemName {

	// sub is an array to store objects of class dailySalesCalc
	// the objects in this array contain item names and amount sold
	itemName[] itemPurchased;

	// two GTerm windows are declared for the display.
	private GTerm gtMain;
	private GTerm gtSub;

	// Constructor to initialize the main GTerm window
	// program takes two int parameters which contain values for height and width of
	// mainGTerm window
	public dailySalesCalculator(int width, int height) {
		// this- denoting the current object
		this.gtMain = new GTerm(width, height);

	}

	// method to take input from user at runtime and store it in different objects
	// in the sub array
	// getDetails() method takes 1 parameters: Integer value denoting the number of
	// items entered by the user.

	public void getDetails(int noOfItems) {
		gtMain.setFontSize(18);
		gtMain.setXY(200, 80);
		gtMain.setBackgroundColor(Color.gray);
		gtMain.print("    ENTER THE DETAILS OF YOUR SALE    ");
		// i is the counter variable to count and store values entered by user
		int i = 0;
		// while loop runs from 0 to n-1 times, n is the number of items entered by
		// the user.
		while (i < noOfItems) {
			// initializing every place of sub array with objects
			itemPurchased[i] = new itemName();

			// Taking input from user for the item names and amount sold
			String name = getStringData("Enter name of " + (i + 1) + " the Item");
			int cost = getIntData("Enter amount sold of " + (i + 1) + " the item");

			// Storing the values entered by user in out Array of objects.
			// sub stores object of Subject type.
			// SubjectDet function corresponds to Subject and modify the data present in the
			// object
			itemPurchased[i].SubjectsDet(name, cost);
			// incrementing i to keep track of the subject count.
			i++;
		}
	}

	// method to take String input and return output.
	public String getStringData(String message) {
		// str stores the input by user
		// and declaration + initialization to null string
		String str = null;

		str = gtMain.getInputString(message);
		// returns the input entered by user
		return str;
	}

	// method to take integer input and displaying the appropriate output.
	// takes a message to display to the user as a parameter in the String data type
	// this uses GTerm functions but adds an extra functionality
	public int getIntData(String message) {
		// integerNo stores the input by user with declaration + initialization to 0
		int integerNo = 0;
		// flag is used for condition c
		boolean flag = true;

		// adding data validation to take only integer values for the amount if items
		while (flag) {
			String rawItemNo = gtMain.getInputString(message);
			try {
				integerNo = Integer.parseInt(rawItemNo);
				flag = false;

			} catch (Exception e) {
				gtMain.showMessageDialog("Please! Enter a valid Integer value");

			}
		}
		// returns the input entered by user
		return integerNo;
	}

	public double percentageCheck() {
		// initialize the gtSub window of the GTerm to show final outputs
		// constructor takes 2 int parameters for width and height of the new window
		this.gtSub = new GTerm(1200, 1200);
		// double variables to store total amount sold and calculate new percentage
		// variables are declared at method level because they are not used outside
		// method.
		// calculated percentage value is returned by method wherever required.
		double totalCost = 0.0;
		double Percentage;

		// int x and y store the x and y coordinate for printing and formatting purpose
		int x = 0, y = 0;
		// Formatting code for Gterm window.
		gtSub.setXY(x += 200, y += 5);
		gtSub.setFontSize(25);
		gtSub.println("            DAILY SALES CALCULATOR");
		gtSub.setXY(x = 5, y = 200); // ***
		gtSub.println("SEE YOU SOON!" + "\n" + "\n");
		gtSub.addImageIcon("bye.gif");
		y = y + 90;
		gtSub.setFontSize(15);

		// for loop to calculate the total cost
		// it iterate over subMarks array and sum all the marks in total marks variable
		for (int i = 0; i < itemPurchased.length; i++) {
			totalCost = totalCost + itemPurchased[i].getMarks();
		}

		// Formula to calculate the percentage
		Percentage = (totalCost / (itemPurchased.length * 100)) * 100;

		// setting up a message to display as per the calculated percentage.
		String message = "";
		if (Percentage > 75)
			message = "GREAT WORK!!\n You have completed your sales goal by - " + Percentage + "%";
		else if (Percentage > 50)
			message = "GOOD WORK!!\\n You have completed your sales goal by - " + Percentage + "%;";
		else if (Percentage > 40)
			message = "You have completed your sales goal by - " + Percentage + "%";
		else
			message = "OH NO! Your goal was not completed. \n Please meet the manager before you go";

		// Displaying the percentage on screen with appropriate formatting
		gtSub.setXY(50, 30);
		gtSub.setFontSize(20);
		gtSub.setFontColor(255, 0, 0);
		gtSub.println(message);

		// returns percentage as calculated by the method
		return Percentage;

	}

	// Method to return the final message that is to be printed in a file
	public String finalMessage(double percnt) {

		// double variables to store calculated percentage
		// variables are declared at method level because they are not used outside
		// method.
		// calculated message value is returned by method required.

		double Percentage = percnt;

		// setting up a message to display the percentage.
		String message = "";
		if (Percentage > 75)
			message = "GREAT WORK!!\n You have completed your sales goal by - " + Percentage + "%";
		else if (Percentage > 50)
			message = "GOOD WORK!!\n You have completed your sales goal by - " + Percentage + "%;";
		else if (Percentage > 40)
			message = "You have completed your sales goal by - " + Percentage + "%";
		else
			message = "OH NO! Your goal was not completed. \n Please meet the manager before you go";
		// returns message as calculated by the method
		return message;

	}

	public static void main(String[] args) throws Exception, IOException {

		int x = 0, y = 0;
		// making class object
		dailySalesCalculator obj = new dailySalesCalculator(800, 400);
		// setting up Text formatting for gtMain window
		obj.gtMain.setXY(x += 200, y += 5);
		obj.gtMain.setBackgroundColor(Color.GRAY);
		obj.gtMain.setFontSize(30);
		obj.gtMain.println("SALES CALCULATOR");
		obj.gtMain.setXY(x = 5, y = 50); // ***
		obj.gtMain.setFontSize(14);
		x = x + 20;

		// declaring the count and noOfItems variable
		// count stores the number of records present in the file if user choose to
		// enter data from a text file
		int count = 0;
		// no of items are stored if user wants to manually enter the data
		int noOfItems = 0;

		// variable to allow users to choose enter data from a file or manually
		String choiceOfEntry = obj.getStringData(
				"Do you want to load the data from a file?\nEnter Y for YES \nPress any other key for NO");

		// if condition to check if user entered Y or y - read from file
		// else allow manual entry of data
		if (choiceOfEntry.equals("y") || choiceOfEntry.equals("Y")) {
			// declaring a buffered reader object
			BufferedReader objReader = null;
			try {
				// Instantiating a buffered reader object wrapping a file reader for better
				// efficiency of file reader
				objReader = new BufferedReader(new FileReader(obj.gtMain.getFilePath()));

				// traversing the file and counting the number of lines present.
				while ((objReader.readLine()) != null) {
					count++;
				}
				// closing the buffered reader object
				objReader.close();

				// declaration of storing a space for n objects
				obj.itemPurchased = new itemName[count];

				// actually creating n objects in the space created in previous line
				for (int i = 0; i < obj.itemPurchased.length; i++) {
					obj.itemPurchased[i] = new itemName();
				}

				// Instantiating again to reset the cursor to the beginning of file
				objReader = new BufferedReader(new FileReader(obj.gtMain.getFilePath()));

				String initialLine;
				int k = 0;

				// while loop reads the complete file
				while ((initialLine = objReader.readLine()) != null) {
					// tmp array stores the values returned by readLine() split with commas
					String[] tmp = initialLine.split(",");
					// nameF stores all the of the line read from file
					String nameF = tmp[0];
					// amountF store the amount sold part of the records
					int amountF = Integer.parseInt(tmp[1]);
					// ensuring - not to cross the number of records present in file
					// ensuring - no null pointer exceptions occur
					if (k < count) {
						obj.itemPurchased[k++].SubjectsDet(nameF, amountF);

					}
				}

				// catch block to handle any exception
			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// Else block executes if user does not enter y for reading data from a file
		else {
			// Print instructions on screen
			obj.gtMain.print("Enter the number of item purchased         ");
			// increasing x and y variable
			x = x + 20;
			// store number of items to store
			noOfItems = obj.getIntData("Enter the total number of items purchased ");
			// Initializing the array to store objects
			obj.itemPurchased = new itemName[noOfItems];
			// getDetails method stores the data entered and guides the user through the
			// process
			obj.getDetails(noOfItems);
		}

		obj.gtMain.setXY(x, y = y + 70);
		obj.gtMain.println(" Entered purchase details are :");

		obj.gtMain.setXY(x = 5, y = y + 30);
		// Setting font color or printing the subject details
		obj.gtMain.setFontColor(255, 45, 47);
		// printing the table headers
		obj.gtMain.println("sr.No\t\t" + "Item Names\t\t" + "Amount Sold");
		// traversing our arrays and printing the subject values and marks below our
		// header
		for (int i = 0; i < obj.itemPurchased.length; i++) {
			x = 5;
			y = y + 10;
			// printing the values stored in array by user
			obj.gtMain.println((i + 1) + ". \t\t     " + obj.itemPurchased[i].getSub() + "\t\t\t\t"
					+ obj.itemPurchased[i].getMarks());

		}

		boolean choice = false;
		do {
			// rawchoice stores the value entered by the user to use control flow of the
			// program
			String rawchoice = obj
					.getStringData("Do you want to edit this entry? \nEnter Y for YES \nPress any other key for NO");
			// Module to run if user wants to edit data
			// using if-else
			if (rawchoice.equals("Y") || rawchoice.equals("y")) {
				choice = true;
				int index = obj.getIntData("Enter the serial no. to delete");
				String newName = obj.getStringData("Enter new item name for Serial no - " + index);
				int newMarks = obj.getIntData("Enter new amount sold for Serial no - " + index);
				obj.itemPurchased[index - 1].SubjectsDet(newName, newMarks);

			}
			// module to run if user does not want to update data
			else
				choice = false;

		} while (choice == true);

		obj.gtMain.println("\n\nNew modified details are : ");
		// Print the new modified details
		for (int i = 0; i < obj.itemPurchased.length; i++) {
			x = 5;
			y = y + 10;
			// printing the values stored in the array
			obj.gtMain.println((i + 1) + ". \t\t     " + obj.itemPurchased[i].getSub() + "\t\t\t\t"
					+ obj.itemPurchased[i].getMarks());

		}

		double Percentage = obj.percentageCheck();
		String data = obj.finalMessage(Percentage);

		String choiceToPrint = obj.gtSub.getInputString(
				"Do you want to save your results in a file?\n \\nEnter Y for YES \\nPress any other key for NO");

		if (choiceToPrint.equals("y") || choiceToPrint.equals("Y")) {

			try {

				// Creates a BufferedWriter wrapped around a FileWriter object
				BufferedWriter outputfile = new BufferedWriter(new FileWriter("./Message.txt"));

				// Writes the string to the file
				outputfile.write(data);

				// Closes the writer
				outputfile.close();
			}

			catch (Exception e) {
				e.getStackTrace();
			}
		}

	}

}
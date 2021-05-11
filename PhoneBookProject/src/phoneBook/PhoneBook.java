package phoneBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class PhoneBook {

	/*
	 * Assigning Menus menus: Main Menu personMenus: Menu for Individual person
	 * AddressMenus for Address Updates and contactMenus for updating contacts such
	 * as telphone,cell,email...etc
	 */
	private static String[] menus = { "Add new entries", "Search by first name *", "Search by full name *",
			"Search by telephone number", "Search by city or state", "Delete a record for a given telephone number",
			"Update a record for a given telephone number", "Show all records in asc order", "Exit" };
	private static String[] personMenus = { "Update Name", "Update Middle Name", "Update Last Name", "Update Address",
			"Update Contact", "Update Birthday Exp(Month-Day-Year)", "Update Aniverasry Exp(Month-Day-Year)", "Exit" };

	private static String[] addressMenus = { "Update Street", "Update City", "Update State", "Update Zipcode", "Exit" };

	private static String[] contactMenus = { "Update Telephone", "Update Home Number", "Update Cell", "Update Email",
			"Update Fax", "Exit" };

	private static Scanner sc = new Scanner(System.in);

	//All data entries will store in this array
	private static Person[] personList = new Person[0];

	// Header for show all records in asc order to understand each columns means
	private static String header = "|  Full Name(F M L)  |        Address       | Birthday | Aniversary |     Contact Info/Additional Info    |";

	public static void main(String args[]) {

		// This is just for testing so I don't have to keep adding same data entry over
		// time
		/*
		 * String[] str = {"John 1, 114 Market St, St Louis, AL, 63403, 6366435698",
		 * "John 3, 324 Main St, St Charles, MO,63303, 8475390126",
		 * "John 2, 574 Pole ave, St. Peters, MO, 63333, 5628592375"};
		 * 
		 * for(int i = 0; i < str.length; i++) { redirect(1,str[i],"mainMenu",null); }
		 */

		// Testing case

		// Displays main menu
		runningType(sc, "mainMenu", null);

	}

	/*
	 * This methods will contininously display options for main menus/person
	 * menus/address menus etc..
	 */
	public static void runningType(Scanner sc, String menuType, Person person) {

		// This happens only for person menu as decoration to see which user being
		// update by their name
		if (menuType.equals("personMenus")) {
			String line = "";
			for (int i = 0; i < 20; i++)
				line += "*";
			System.out.println(String.format("%s\nUPDATE FOR %s\n%s", line, person.fullName(), line));
		}

		// Getting type of menu array
		String[] mymenus = getMenu(menuType);

		// It will display options such as 1) Add New Entries... asing int option
		System.out.println(Screen(menuType));
		int option = sc.nextInt();
		sc.nextLine();

		// Initialzing empty response
		String response = "";
		// System.out.flush();

		// This will continuosly display with menus
		while (option != mymenus.length) {

			// This will skim nextLine when I select personMenu and mainMenu to fix bug
			if ((menuType.equals("personMenus") && option == 4) || (menuType.equals("personMenus") && option == 5)
					|| (menuType.equals("mainMenu") && option == 8)) {
				
			} else {
				// It will show to enter any data base on the menuType
				System.out.println(Option(option, menuType));
				
				response = sc.nextLine();
			}


			// This function will take response, menutype,Person object in order to redirect
			// to use* right method
			redirect(option, response, menuType, person);

			// Menus
			System.out.println(Screen(menuType));
			option = sc.nextInt();
			sc.nextLine();
			// sc.next();

		}

	}

	// Getting which type of menu array base menuType
	public static String[] getMenu(String menuType) {

		if (menuType.contentEquals("mainMenu"))
			return menus;
		if (menuType.contentEquals("personMenus"))
			return personMenus;
		if (menuType.contentEquals("addressMenus"))
			return addressMenus;
		if (menuType.contentEquals("contactMenus"))
			return contactMenus;
		else
			return null;

	}

	/*
	 * This method will add options together such as 1) Add New entries with new
	 * line using (\n)...
	 */
	public static String Screen(String menuType) {
		String[] mymenus = getMenu(menuType);

		String printMenus = "Please enter an option...\n";
		for (int i = 0; i < mymenus.length; i++) {
			String menu = (i + 1) + ")" + mymenus[i];
			if (i == 0) {
				printMenus += menu;
				continue;
			}
			printMenus += "\n" + menu;
		}

		return printMenus;
	}

	/*
	 * This will return String and tells to enter the date based on the menutype and
	 * option Using switch statement which makes it easy instead of bunch of if
	 * statements
	 */
	public static String Option(int option, String menuType) {
		String result = "Please enter to ";

		// Based on the menus it will add resul string based on the option if it is over
		// 8 it will return default string which says enter valid option
		if (menuType == "mainMenu") {
			switch (option) {
			case 1:
				return result += "add new entry exp(John Doe, 114 Market St, St Louis, MO, 63403, 6366435698): ";
			case 2:
				return result += "search by first name*: ";
			case 3:
				return result += "search by last name*: ";
			case 4:
				return result += "search by telephone number: ";
			case 5:
				return result += "search by city or state: ";
			case 6:
				return result += "delete a record for a given telephone number: ";
			case 7:
				return result += "Update a record for a given telephone number: ";
			case 8:
				return "";
			default:
				return "Please enter only from an options!";
			}
		}

		if (menuType == "personMenus") {
			switch (option) {
			case 1:
				return result += "Update Name: ";
			case 2:
				return result += "Update Middle Name: ";
			case 3:
				return result += "Update Last Name: ";
			case 4:
				return result += "Update Address: ";
			case 5:
				return result += "Update Telephone: ";
			case 6:
				return result += "Update Birthday Exp(Month-Day-Year): ";
			case 7:
				return result += "Update Aniverasry Exp(Month-Day-Year): ";
			default:
				return "Please enter only from an options!";
			}
		}

		if (menuType == "addressMenus") {
			switch (option) {
			case 1:
				return result += "Update Street: ";
			case 2:
				return result += "Update City: ";
			case 3:
				return result += "Update State: ";
			case 4:
				return result += "Update Zipcode: ";
			default:
				return "Please enter only from an options!";
			}
		}

		if (menuType == "contactMenus") {
			switch (option) {
			case 1:
				return result += "Update Telephone: ";
			case 2:
				return result += "Update Home Number: ";
			case 3:
				return result += "Update Cell: ";
			case 4:
				return result += "Update Email: ";
			case 5:
				return result += "Update Fax: ";
			default:
				return "Please enter only from an options!";
			}
		}

		return "";

	}

	/*
	 * This function will take response, menutype,Person object in order to redirect
	 * to use* right method for exp if menuType is main Main it will get right
	 * method to use based on the which option I used
	 */
	public static void redirect(int option, String response, String menuType, Person person) {

		// Used switch state for convinient purposes
		if (menuType == "mainMenu") {

			switch (option) {
			case 1:
				addEntry(response);
				break;
			case 2:
				search(response, option);
				break;
			case 3:
				search(response, option);
				break;
			case 4:
				search(response, option);
				break;
			case 5:
				search(response, option);
				break;
			case 6:
				delete(response);
				break;
			case 7:
				update(response);
				break;
			case 8:
				showAll();
				break;
			default:
				break;
			}

		}

		// Ones I get person based on their telephone number, updating person like
		// person.setFirstName if option is 1 samething for rest of it

		if (menuType == "personMenus") {
			switch (option) {
			case 1:
				updatePerson(response, option, person);
				break;
			case 2:
				updatePerson(response, option, person);
				break;
			case 3:
				updatePerson(response, option, person);
				break;
			case 4:
				updateAddressScreen(person);
				break;
			case 5:
				updateContactScreen(person);
				break;
			case 6:
				updatePerson(response, option, person);
				break;
			case 7:
				updatePerson(response, option, person);
				break;
			default:
				break;
			}

		}

		// From personMenus I am getting person.getAddress class and set function
		// (UPDATING IT)
		if (menuType == "addressMenus") {

			switch (option) {
			case 1:
				updateAddress(response, option, person);
				break;
			case 2:
				updateAddress(response, option, person);
				break;
			case 3:
				updateAddress(response, option, person);
				break;
			case 4:
				updateAddress(response, option, person);
				break;
			default:
				break;
			}

		}

		// From personMenus I am getting person.Contact class and set function (UPDATING
		// IT)
		if (menuType == "contactMenus") {

			switch (option) {
			case 1:
				updateContact(response, option, person);
				break;
			case 2:
				updateContact(response, option, person);
				break;
			case 3:
				updateContact(response, option, person);
				break;
			case 4:
				updateContact(response, option, person);
				break;
			case 5:
				updateContact(response, option, person);
				break;
			default:
				break;
			}

		}

	}

	/*
	 * This function will display all Person[] person arrays which entry have been
	 * stored in ASC order
	 */
	public static void showAll() {
		sortPersonList(personList);

		// Orignal Format: System.out.println(header);

		for (Person p : personList) {
			System.out.println(p);
		}
	}

	/*
	 * This function direct to continuous redirect with Person object to runningType
	 * function both updateAddress and updateContactScreen method
	 */

	public static void updateAddressScreen(Person person) {
		runningType(sc, "addressMenus", person);
	}

	public static void updateContactScreen(Person person) {
		runningType(sc, "contactMenus", person);
	}

	/*
	 * Ones I get Person object this function where I am updating properties in
	 * Person object based on the option setting response
	 */
	public static void updatePerson(String response, int option, Person person) {

		if (option == 1)
			person.setFirstName(response);
		if (option == 2)
			person.setMiddleName(response);
		if (option == 3)
			person.setLastName(response);
		if (option == 6)
			person.setBirthday(response);
		if (option == 7)
			person.setAnniversary(response);

	}

	/*
	 * Ones I get Person object this function where I am updating properties in
	 * Person.getAddress object based on the option setting response
	 */

	public static void updateAddress(String response, int option, Person person) {
		if (option == 1)
			person.getAddress().setStreet(response);
		if (option == 2)
			person.getAddress().setCity(response);
		if (option == 3)
			person.getAddress().setState(response);
		if (option == 4)
			person.getAddress().setZipcode(response);

	}

	/*
	 * Ones I get Person object this function where I am updating properties in
	 * Person.getAddress object based on the option setting response
	 */

	public static void updateContact(String response, int option, Person person) {

		String onlyNumber = filterOnlyNumber(response);

		if (option == 1)
			person.getContact().setTelephone(onlyNumber);
		if (option == 2)
			person.getContact().setHome(onlyNumber);
		if (option == 3)
			person.getContact().setCell(onlyNumber);
		if (option == 4)
			person.getContact().setEmail(response);
		if (option == 5)
			person.getContact().setFax(onlyNumber);

	}

	/*
	 * This function will check first based on personList array if any Person have
	 * exact phone number and sets to continious display function with Person object
	 * if it finds it
	 */
	public static void update(String response) {
		String onlyNumber = filterOnlyNumber(response);
		for (Person p : personList) {
			if (p.getContact().getTelephone().equals(onlyNumber)) {
				runningType(sc, "personMenus", p);

				break;
			}
		}
	}

	/* This function will add new entry to personList array */
	public static void addEntry(String response) {

		// This will extract raw string first name, middle name if it exist and last
		// name and assigns to new Person
		Person person = setPerson(response);
		if (person == null)
			return;

		// This will add person into personList array
		personList = addPerson(personList, person);

		// System.out.println(personList.length);

		// Every time add entry it will display entry
		/*
		 * for(int i = 0 ; i < personList.length; i++) {
		 * System.out.println(personList[i]); }
		 */

		// Object[] tempArr = addObj(,)

	}

	/*
	 * This function will check first based on personList array if any Person have
	 * exact phone number and deletes Person record from personList array
	 */
	public static void delete(String response) {

		String onlyNumber = filterOnlyNumber(response);

		for (Person p : personList) {
			if (p.getContact().getTelephone().equals(onlyNumber)) {
				// This function will delete object and shrinks personList array
				removePerson(personList, p);
				break;
			}
		}

		// Every time add entry it will display entry

		/*
		 * for(int i = 0 ; i < personList.length; i++) {
		 * System.out.println(personList[i]); }
		 */
	}

	/* This function will search and display by ASIC Name based on option */
	public static void search(String response, int opt) {

		// Assign new array to add only searched Person Objects
		Person[] searchList = new Person[0];
		response = response.toLowerCase();

		// Based on the option it will search firstName, fullName contact, state or city
		for (Person p : personList) {
			// System.out.println(p.getFirstName().toLowerCase().contains(response.toLowerCase()));
			if (opt == 2 && p.getFirstName().toLowerCase().contains(response)) {
				searchList = addPerson(searchList, p);
			}

			if (opt == 3 && p.fullName().toLowerCase().contains(response)) {
				searchList = addPerson(searchList, p);
			}
			
			if (opt == 4 && p.getContact().getTelephone().contains(filterOnlyNumber(response))) {
				searchList = addPerson(searchList, p);
			}
			
			if (opt == 5) {
				boolean isStateCity = false;
				if (response.length() == 2) {
					isStateCity = p.getAddress().getState().toLowerCase().contains(response);
				} else {
					isStateCity = p.getAddress().getCity().toLowerCase().contains(response);
				}
				if (isStateCity) {
					searchList = addPerson(searchList, p);
				}

			}

		}

		// This will sort serachList in ASC please go to sortPersonList method I have
		// explain it in there
		sortPersonList(searchList);
		System.out.println(header);

		// System.out.println(personList.length);

		// Arrays.sort(personList);
		// sortPersonList()
		for (int i = 0; i < searchList.length; i++) {
			System.out.println(searchList[i]);

		}

	}

	// Using regex to only display number
	public static String filterOnlyNumber(String response) {
		String onlyNumber = response.replaceAll("[^0-9]", "");
		return onlyNumber;
	}

	/*
	 * This function use Array.sort to sort in decending and use
	 * Collection.reverseOrder comparator to compare strings between person objects
	 * full name
	 */
	public static void sortPersonList(Person[] sortList) {
		Arrays.sort(sortList, Collections.reverseOrder(new Comparator<Person>() {
			@Override
			public int compare(Person a1, Person a2) {
				// Used -1 to reverseOrder for ASC descending using String.compareTo function
				return -1 * a1.fullName().compareTo(a2.fullName());
			}
		}));

	}

	/*
	 * This metthod will extact all data from raw string and create Address Class,
	 * contact class and finally Person Class
	 */
	public static Person setPerson(String response) {
		Person person;
		// Spliting using comma ','
		String[] data = response.split(",");

		// Taking out space from raw response
		for (int i = 1; i < data.length; i++) {
			data[i] = data[i].substring(1);
		}

		String[] personData = new String[0];
		Address addr = null;
		Contact contact = null;

		// If entry entered wrong it will say invalid
		try {
			personData = data[0].replaceAll("^\\s+", "").split(" ");

			// getting address from split
			addr = new Address(data[1], data[2], data[3], data[4]);
			// getting contact info from split
			contact = new Contact(data[5]);
		} catch (Exception e) {
			System.out.println("Please enter valid data");
			return null;
		}

		/*
		 * for(int i = 0 ; i < data.length; i++) { System.out.println(data[i]); }
		 */

		// Logic for middle name exist or not
		if (personData.length == 2) {
			person = new Person(personData[0], personData[1]);
		} else if (personData.length == 3) {
			person = new Person(personData[0], personData[1], personData[2]);
		} else {
			person = new Person(personData[0], personData[1] + " " + personData[2], personData[3]);
		}

		person.setAddress(addr);
		person.setContact(contact);

		return person;
	}

	/*
	 * Method for adding person to Person[] array copying new array with extanded
	 * length
	 */
	public static Person[] addPerson(Person[] a, Person b) {

		Person[] result = new Person[a.length + 1];
		System.arraycopy(a, 0, result, 0, a.length);
		result[result.length - 1] = b;

		return result;

	}

	/*
	 * Method for removing person from Person[] and creating shrinked array copying
	 * data
	 */
	public static void removePerson(Person[] arr, Person obj) {
		int index = getIndexObj(arr, obj);
		Person[] result = new Person[arr.length - 1];
		System.arraycopy(arr, 0, result, 0, arr.length - 1);
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (i != index) {
				result[count] = arr[i];
				count++;
			}
		}

		personList = result;

	}

	/*
	 * Method for getting object index from Array if object.toString equals to each
	 * other
	 */
	public static int getIndexObj(Object[] arr, Object obj) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(obj))
				return i;
		}

		return -1;
	}

}
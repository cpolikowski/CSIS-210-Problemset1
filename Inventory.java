
/**
 * Creates a single dimensional array that holds the object book
 *
 * @author Kade Garrison and Cam Polikowski
 * @version 1.0 (Due Date: 2/23/22)
 */
public class Inventory
{
    public static final int MAX_BOOKS = 5;
    private Book[] inventory;
    private int currentSize;

    public Inventory(){
        inventory = new Book[MAX_BOOKS];
        currentSize = 0;

    }

    public boolean addBook(String title){
        boolean temp = false;
        for (int i = 0; i < inventory.length; i++){
            if (inventory[i] != null && inventory[i].getTitle().equals(title) ){
                temp = false;
                i = inventory.length + 1;
            }else if (currentSize < MAX_BOOKS){
                inventory[currentSize] = new Book(title);
                temp = true;
                i = inventory.length + 1;
                currentSize++;
            }else{
                temp = false;
                i = inventory.length + 1;
                System.out.println("There is no inventory room for your book");
            }
        }
        return temp;
    }

    public boolean addBook(String title, int numCopies) {
        boolean temp = false;
        for (int i = 0; i < inventory.length; i++){
            if (inventory[i] != null && inventory[i].getTitle().equals(title) ){
                temp = false;
                i = inventory.length + 1;
            }else if (currentSize < MAX_BOOKS){
                inventory[currentSize] = new Book(title, numCopies);
                temp = true;
                i = inventory.length + 1;
                currentSize++;
            }else{
                temp = false;
                i = inventory.length + 1;
            }
        }
        return temp;
    }

    public boolean haveCopy(String title) {
        boolean temp = false;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getTitle().equals(title)){
                if (inventory[i].getNumInStock() > 0) {
                    temp = true;
                    i = inventory.length + 1;
                }else {
                    temp = false;
                }
            } else {
                temp = false;
            }
        }
        return temp;
    }

        public boolean sellCopy(String title) {
        boolean temp = false;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getTitle().equals(title)) {
                if (inventory[i].getNumInStock() > 0) {
                    inventory[i].sellCopy();
                    temp = true;
                    currentSize--;
                } else {
                    temp = false;
                }
            }
        }
        return temp;
    }

    public int newShipment(String title, int numCopies) {
        int temp = 0;

        for (int i = 0; i < inventory.length; i++){
            if (inventory[i] != null && inventory[i].getTitle().equals(title)){
                inventory[i].addCopies(numCopies);
                temp = inventory[i].getNumInStock();
                i = inventory.length + 1;
            }else if(currentSize < MAX_BOOKS){
                addBook(title, numCopies);
                temp = inventory[i].getNumInStock();
                currentSize++;
                i = inventory.length + 1;
            }
        }
        return temp;
    }

    public String getUnderstockedBooks(int count){
        StringBuffer out = new StringBuffer();

        for (int i = 0; i < inventory.length; i++){
            if (inventory[i] != null && inventory[i].getNumInStock() < count){
                out.append(inventory[i] + ", ");
            }
        }
        out.delete(out.length() - 2, out.length());
        return out.toString();
    }

    public Book removeBook(String title){
        int flag = 999;
        Book temp = null;
        for (int i = inventory.length; i >= 0; i--){
            if (inventory[i].getTitle().equals(title)){
                flag = i;
                temp = new Book(inventory[i].getTitle());
            }
        }

        for (int i = inventory.length - 1; i >= flag; i--){
            inventory[i + 1] = inventory[i];
            currentSize--;
        }
        return temp;
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory(); System.out.println("Have \"Conditionals Are Fun\"?: " +
            inventory.haveCopy("Conditionals Are Fun")); inventory.addBook("Conditionals Are Fun", 10); inventory.addBook("A History of Siena College", 5); inventory.addBook("Arrays for Everyone", 0); inventory.addBook("Java Heroes", 4); System.out.println("Have \"Conditionals Are Fun\"?: " +
            inventory.haveCopy("Conditionals Are Fun")); System.out.println("Have \"Arrays for Everyone\"?: " +
            inventory.haveCopy("Arrays for Everyone")); System.out.println("3 or fewer copies: " +
            inventory.getUnderstockedBooks(4)); inventory.sellCopy("Java Heroes"); System.out.println("3 or fewer copies: " +
            inventory.getUnderstockedBooks(4));
        int newCount = inventory.newShipment("Big Book of Computing", 10); System.out.println("Now have " + newCount +
            " copies of \"Big Book of Computing\".");
        newCount = inventory.newShipment("Little Book of Computing", 10); System.out.println("Now have " + newCount +
            " copies of \"Little Book of Computing\".");
    }
}
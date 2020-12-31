
/*
 * The database structure that includes some basic functions to control the flow of data
 */
package bankapplication;

import java.util.ArrayList;

/**
 *
 * @author bryson
 */
public class Database {

    private ArrayList<Account> bankData = new ArrayList<Account>();

    /**
     * Set up a database includes some test people so there are other accounts
     *
     */
    public Database() {
        //generate everything for some test people
        System.out.println("Setting up default database");

        addTo("Buggy", "Default", "Bug", 1234);
        addTo("N00b", "Noob", "Foo", 5678);
        addTo("Raggedy Shoes", "Bababooy", "Raggedy", 5480);

        //fill the accounts I just created with random amounts don't worry about change
        for (Account x : bankData) {
            x.setFunds(Math.round(Math.random() * 1000));
            System.out.println(x.toString());
        }

        System.out.println("default database ready");
    }

    /**
     *
     * Add a person to the database This one creates a new account inside it
     *
     * @param username the username the person wants to identify with when
     * logging in
     * @param firstName Keep the record of the first legal name
     * @param lastName Keep a record of the last legal name
     * @param setPin pin that needs to be set to the users account (does not
     * need to be verified)
     */
    public void addTo(String username, String firstName, String lastName, int setPin) {
        // check to see if the pin is more then 4 numbers
        try {
            //add customer data to a new account object
            Account newCustomer = new Account(username, firstName, lastName, setPin);
            bankData.add(newCustomer);

            System.out.println("Customer added to the database");

        } catch (Exception e) // add more later
        {
            System.out.println("Error occured in the database addTo " + e);
        }
    }

    /**
     * add a user account that is already created into the database
     *
     * @param currUser
     */
    public void addTo(Account currUser) {
        //just straight add the account from the ATM's operations file
        bankData.add(currUser);
    }

    /**
     * Search for a user using their username in the database (search do be
     * kinda slow)
     *
     * @param userToLookFor the username is passed in to begin search
     * @return the whole account or nothing if it doesn't exist
     */
    public Account search(String userToLookFor) {
        for (Account looking : bankData) {
            //ingore the case of the username because it was simple
            if (looking.getUsername().equalsIgnoreCase(userToLookFor)) {
                return looking;
            }
        }
        return null;
    }

    /**
     * This will destroy it and remove it from the database
     *
     * @param accountToDestroy the account is pushed in
     */
    public void deleteFrom(Account accountToDestroy) {
        if (bankData.contains(accountToDestroy)) {
            System.out.println("Deleting account data...");
            bankData.remove(accountToDestroy);
            //could add a return later to return a copy to restore later
        } else {
            //I should change this to a throw and return it to the driver
            System.out.println("Account was not found returning...");
        }
    }

    @Override
    public String toString() {
        String allData = null;
        for (Account x : bankData) {
            allData += x.toString() + "\n";
        }
        return allData;
    }

}

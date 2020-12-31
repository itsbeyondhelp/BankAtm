/*
 * A simple way of tracking user information
 */
package bankapplication;

/**
 *
 * @author bryson
 */
public class Account {

    //the account variables
    private String username;
    private String firstName;
    private String lastName;
    private int PIN;
    private double funds = 0.0;

    /**
     * Creates an account object
     *
     * @param username
     * @param firstName
     * @param lastName
     * @param PIN
     */
    public Account(String username, String firstName, String lastName, int PIN) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.PIN = PIN;
    }

    /**
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @return the persons last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return the accounts pin
     */
    public int getPIN() {
        return PIN;
    }

    /**
     *
     * @return the balance of the account
     */
    public double getFunds() {
        return funds;
    }

    /**
     * Used to set the security pin of the account
     *
     * @param PIN
     */
    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    /**
     * set new funds to the account
     *
     * @param funds
     */
    public void setFunds(double funds) {
        this.funds = funds;
    }

    @Override
    public String toString() {
        return "Account{" + "username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", PIN=" + PIN + ", funds=" + funds + '}';
    }

}

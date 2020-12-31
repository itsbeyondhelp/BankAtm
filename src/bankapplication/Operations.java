/*
 * Full operations of the ATM
 */
package bankapplication;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author bryson
 */
public class Operations {

    private static Scanner input = new Scanner(System.in);

    /**
     * This will start the verify operation from the ATM by receiving the
     * account object and checking if the user was correct
     *
     * @param currUser is the account that is having its pin checked for user
     * entry
     * @return true if the pins match or false to deny entry
     */
    public static Boolean Verify(Account currUser) {
        System.out.print("Please verify your identity with your pin\n");

        try {
            int check = input.nextInt();

            //catch
            if (check == currUser.getPIN()) {
                return true;
            } else {
                return false;
            }
        } catch (InputMismatchException ime) {
            System.out.println("Please enter a 4 digit pin" + ime);
            input.nextLine();
        } catch (Exception e) {
            System.out.println("an unexpected error occured in the verify pin operation" + e);
        }
        return null;
    }

    /**
     * The withdraw operation takes the account object and subtracts the amount
     * the user specified if it is too much the request is denied
     *
     * @param currUser the current account
     * @param amount the requested amount
     */
    public static void Withdraw(Account currUser, double amount) {
        try {
            if (amount > currUser.getFunds()) {
                //not enough in account
                System.out.println("Insufficiant funds");
            } else {

                currUser.setFunds(currUser.getFunds() - amount);
            }
        } catch (InputMismatchException ime) {
            System.out.println("Please enter the amount you would like to withdraw" + ime);
            input.nextLine();
        } catch (Exception e) {
            System.out.println("An error within the withdraw operation" + e);
        }

    }

    /**
     * This operation is just to add money to the account
     *
     * @param currUser is the current user that the main driver sent
     * @param amount the amount the user is adding to the account
     */
    public static void Deposit(Account currUser, double amount) {
        try {
            currUser.setFunds(currUser.getFunds() + amount);
        } catch (InputMismatchException ime) {
            System.out.println("Please enter the amount you would like to deposit" + ime);
            input.nextLine();
        } catch (Exception e) {
            System.out.println("An error within the deposit operation" + e);
        }
    }

    /**
     * If the user would like to change their pin the system will authenticate
     * it and make sure it is a valid pin the user must also make sure they
     * match the pin to see if they remember it
     *
     * @param currUser the current user the driver is on
     */
    public static void setNewPin(Account currUser) {
        //this variable is to control the state of the loop and if the user is done it is okay to not run the loop again
        Boolean okayToTerminate = false;

        do {
            try {
                System.out.println("enter a new pin");
                int temp = input.nextInt();
                //check to see if it is 4 digits long
                if ((int) (Math.log10(temp) + 1) == 4) {
                    System.out.println("Verify the pin you just entered");
                    int verify = input.nextInt();
                    if (temp != verify) {
                        //was not correct
                        System.out.println("Pin entered did not match above please try again");
                    } else {
                        currUser.setPIN(temp);
                        okayToTerminate = true;
                    }
                } else {
                    System.out.println("please enter a number that is 4 digits long");
                }

            } catch (InputMismatchException ime) {
                System.out.println("Please enter a 4 digit pin" + ime);
                input.nextLine();
            } catch (Exception e) {
                System.out.println("An error within the setNewPin operation" + e);
            }
        } while (!okayToTerminate); //if it isnt okay send back to the top
    }

    /**
     * Users will be allowed to open a new account that they can log into The
     * balance will be zero upon creation
     *
     * @return return to the driver the current user to add to the database
     */
    public static Account createNewAccount() {
        System.out.println("Enter your first name:");
        String firstName = input.nextLine();
        input.nextLine(); //fix this bug later
        System.out.println("\nEnter your last name:");
        String lastName = input.nextLine();
        System.out.println("\nWhat would you like to be your username to login?");
        String userName = input.nextLine();
        //creates the account with a temporary pin
        //I didnt want to import the whole database so I just created a new account
        Account newUser = new Account(userName, firstName, lastName, 1234);
        //send the user to create a new pin automatically for security
        setNewPin(newUser);

        return newUser;

    }

    /**
     * Users may request to have their accounts closed and destroyed
     *
     * @param close the account that will now be closed
     * @param allBankData the current state of the banks data base
     * @return the new state of the database to the driver
     */
    public static Database closeAccount(Account close, Database allBankData) {
        //Are you sure
        allBankData.deleteFrom(close);
        return allBankData;
    }

}

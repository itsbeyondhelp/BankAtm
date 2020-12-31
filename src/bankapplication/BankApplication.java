/*
 * Driver of the ATM
 * Later revamp will change into a java swing interface
 */
package bankapplication;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author bryson
 */
public class BankApplication {

    private static Database bankData;
    private static DecimalFormat f = new DecimalFormat("$##.00");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here'

        //Beginning driver
        bankData = new Database();
        driver();

    }

    private static void driver() {

        Boolean terminate = false;
        Scanner userInput = new Scanner(System.in);
        //int choice = 0;
        while (!terminate) {
            int choice = 0;
            try {
                System.out.println("Select a an option: \n"
                        + "1. Login to an account \n"
                        + "2. Open an Account\n"
                        + "3. Quit\n");
                choice = userInput.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("\nEnter the username to login to:");
                        userInput.nextLine();
                        String username = userInput.nextLine();
                        Account found = bankData.search(username);
                        found.toString();

                        if (Operations.Verify(found)) {
                            enteredUser(found);
                        } else {
                            System.out.println("Sorry that was not correct");
                        }
                        break;
                    case 2:
                        Account userAccount = Operations.createNewAccount();
                        bankData.addTo(userAccount);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    case 4:
                        System.out.println("following accounts are active:");

                        System.out.println(bankData.toString());
                        break;

                    default:
                        System.out.println("Option not available at this time");
                        break;

                }

            } catch (InputMismatchException ime) {
                //throws into loop idk why yet
                System.out.println("Input was entered that wasn't expected " + ime);
                userInput.nextLine();
            } catch (NullPointerException npe) {
                System.out.println("Was not found " + npe);
            }
        }
    }

    /**
     *
     * @param user
     */
    public static void enteredUser(Account user) {
        Boolean terminate = false;
        Scanner userInput = new Scanner(System.in);
        int choice = 0;
        double amountUserWants = 0.0;
        System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
        while (!terminate) {
            try {

                System.out.println("Select a an option: \n"
                        + "1. View balance \n"
                        + "2. Withdraw\n"
                        + "3. Deposit\n"
                        + "4. Change pin\n"
                        + "5. Close Account\n"
                        + "6. Logout");
                choice = userInput.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("\nYour Current Balance is: " + f.format(user.getFunds()));
                        break;
                    case 2:
                        amountUserWants = 0.0;
                        System.out.println("How much would you like to withdraw today?\n");
                        amountUserWants = userInput.nextDouble();
                        Operations.Withdraw(user, amountUserWants);
                        break;
                    case 3:
                        amountUserWants = 0.0;
                        System.out.println("How much would you like to deposit today?\n");
                        amountUserWants = userInput.nextDouble();
                        Operations.Deposit(user, amountUserWants);

                        break;
                    case 4:
                        Operations.setNewPin(user);
                        break;
                    case 5:
                        bankData = Operations.closeAccount(user, bankData);
                        terminate = true;
                        break;
                    case 6:
                        terminate = true;
                        break;

                    default:
                        System.out.println("Option not available at this time");
                        break;

                }

            } catch (InputMismatchException ime) {
                //throws into loop idk why yet
                System.out.println("Input was entered that wasn't expected please reenter" + ime);
                userInput.nextLine();
            }
        }

    }
}

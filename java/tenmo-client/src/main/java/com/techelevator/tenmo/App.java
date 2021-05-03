package com.techelevator.tenmo;

import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.UserCredentials;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.AuthenticationServiceException;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.view.ConsoleService;

import java.math.BigDecimal;
import java.util.Scanner;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private static final String MENU_OPTION_EXIT = "Exit";
    private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
    private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
    private static final String[] LOGIN_MENU_OPTIONS = {LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT};
    private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
    private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
    private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
    private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
    private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
    private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS, MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS, MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT};

    private AuthenticatedUser currentUser;
    private ConsoleService console;
    private AuthenticationService authenticationService;


    public static void main(String[] args) {
        App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL));
        app.run();
    }

    public App(ConsoleService console, AuthenticationService authenticationService) {
        this.console = console;
        this.authenticationService = authenticationService;
    }

    public void run() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");

        registerAndLogin();
        mainMenu();
    }

    private void mainMenu() {
        while (true) {
            String choice = (String) console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
                viewCurrentBalance();
            } else if (MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
                viewTransferHistory();
            } else if (MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
                viewPendingRequests();
            } else if (MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
                sendBucks();
            } else if (MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
                requestBucks();
            } else if (MAIN_MENU_OPTION_LOGIN.equals(choice)) {
                login();
            } else {
                // the only other option on the main menu is to exit
                exitProgram();
            }
        }
    }

    private void viewCurrentBalance() {
        AccountService accountService = new AccountService(API_BASE_URL, currentUser);

        //try {
		System.out.println("--------------------------------");
		System.out.println();
        System.out.println("Your current balance is: $" + accountService.getAccountBalance());
		System.out.println();
		System.out.println("--------------------------------");
        //} catch (AuthenticationServiceException e) {
        //	System.out.println("No Balance");
        //}

    }

    private void viewTransferHistory() {

        //System.out.println(currentUser.getUser().getId());

        AccountService accountService = new AccountService(API_BASE_URL, currentUser);
        Transfer[] transfers = accountService.getAllTransfers();

        System.out.println("--------------------------------");
        System.out.println("Transfers");
        System.out.println("ID        " + "From/To        " + "Amount");
        System.out.println("--------------------------------");

        for (Transfer transfer : transfers) {
            System.out.printf("%-10d TO: %-8s     %7.2f\n", transfer.getTransferId(), accountService.getNamefromAccount(transfer.getTransferToAccountId()), transfer.getTransferAmount());
            System.out.printf("             FROM: %-8s \n", accountService.getNamefromAccount(transfer.getTransferFromAccountId()));
        }
        System.out.println("----------");

        //Transfer[] transfers1 = accountService.getAllTransfers();

        Scanner scanner = new Scanner(System.in);
        //int toTransferId = Integer.parseInt(console.getUserInput("Please enter transfer ID to view details (0 to cancel) "));
        System.out.print("Please enter transfer ID to view details (0 to cancel): ");

        try {
        String transferId = scanner.nextLine().trim();
        int transferId1 = Integer.parseInt(transferId);

            for (Transfer transfer1 : transfers) {
                if (transfer1.getTransferId() == transferId1) {
                    System.out.println("--------------------------------");
                    System.out.println("Transfer Details");
                    System.out.println("--------------------------------");
                    System.out.println("ID: " + transfer1.getTransferId());
                    System.out.println("From: " + accountService.getNamefromAccount(transfer1.getTransferFromAccountId()));
                    System.out.println("To: " + accountService.getNamefromAccount(transfer1.getTransferToAccountId()));
                    System.out.println("Type: " + transfer1.getTransferType() + " (Send)");
                    System.out.println("Status: " + transfer1.getTransferStatus() + " (Approved)");
                    System.out.println("Amount: " + transfer1.getTransferAmount());
                }
            }

        } catch (Exception ex) {
            System.out.println("---- Must Put In a Number ----");
        }

        System.out.println("----------");

    }

    private void viewPendingRequests() {
        // TODO Auto-generated method stub

    }

    private void sendBucks() {
        AccountService accountService = new AccountService(API_BASE_URL, currentUser);
        User[] users = accountService.getAllUsers();

        System.out.println("--------------------------------");
        System.out.println("Users");
        System.out.println("ID     " + "  Name");
        System.out.println("--------------------------------");


		try {
			for (User user : users) {

				if (!user.getId().equals(currentUser.getUser().getId())) {
					System.out.println(user.getId() + "     " + user.getUsername());
				}
			}
			System.out.println("----------");

			int toUserId = Integer.parseInt(console.getUserInput("Enter ID of user you are sending to (0 to cancel) ").trim());

			if (toUserId != 0) {

				BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(console.getUserInput("Enter amount ").trim()));

				if (amount.compareTo(BigDecimal.ZERO) <= 0) {
					System.out.println("---- Transfer Amounts Must Be Positive ----");
					return;
				}

				accountService.transfer(currentUser.getUser().getId(), toUserId, amount);
			}
		} catch (Exception ex) {
			System.out.println("---- Invalid ID Entered ----");
		}


    }

    private void requestBucks() {
        // TODO Auto-generated method stub

    }

    private void exitProgram() {
        System.exit(0);
    }

    private void registerAndLogin() {
        while (!isAuthenticated()) {
            String choice = (String) console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
            if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
                login();
            } else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
                register();
            } else {
                // the only other option on the login menu is to exit
                exitProgram();
            }
        }
    }

    private boolean isAuthenticated() {
        return currentUser != null;
    }

    private void register() {
        System.out.println("Please register a new user account");
        boolean isRegistered = false;
        while (!isRegistered) //will keep looping until user is registered
        {
            UserCredentials credentials = collectUserCredentials();
            try {
                authenticationService.register(credentials);
                isRegistered = true;
                System.out.println("Registration successful. You can now login.");
            } catch (AuthenticationServiceException e) {
                System.out.println("REGISTRATION ERROR: " + e.getMessage());
                System.out.println("Please attempt to register again.");
            }
        }
    }

    private void login() {
        System.out.println("Please log in");
        currentUser = null;
        while (currentUser == null) //will keep looping until user is logged in
        {
            UserCredentials credentials = collectUserCredentials();
            try {
                currentUser = authenticationService.login(credentials);
            } catch (AuthenticationServiceException e) {
                System.out.println("LOGIN ERROR: " + e.getMessage());
                System.out.println("Please attempt to login again.");
            //break; // Put this in case you didn't want to login as someone else and just want to get back to menu
            }
        }
    }

    private UserCredentials collectUserCredentials() {
        String username = console.getUserInput("Username");
        String password = console.getUserInput("Password");
        return new UserCredentials(username, password);
    }
}

package sandbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ATM_Management {

  public static void main(String[] args) throws SQLException {
    String uri = "jdbc:sqlite:/Users/trainofthought/"
        + "Documents/Software Engineering/Intro to Data "
        + "Engineering/Databases/ATMDatabase/ATM_Management.db";
    /*
     * If you need your home directory to build the path (on both Win/*nix)
     * String uri = "dbc:sqlite:" + System.getProperty("user.home") + "Path/to/DB.db";
     */

    String createBankTotalSQL = "CREATE VIEW 'BANK_TOTALS' AS SELECT Account.bank_id, "
        + "SUM(Account.balance) AS 'TOTAL BALANCE'"
        + "FROM Account GROUP BY Account.bank_id";

    String insertATMSQL = "INSERT INTO ATM(atm_id, bank_id, atm_location," +
        " location_name, balance, num_of_tran)" +
        " VALUES (?, ?, ?, ?, ?, ?);";

    String createMemberSQL = "INSERT INTO Member(mem_id, acct_id, mem_fname, mem_lname, ssn, phone,"
        + " email, address, birthdate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    String getBalanceSQL = "SELECT Account.balance FROM Account INNER JOIN "
        + "Member ON Account.acct_id=Member.acct_id WHERE Member.mem_id=?;";

    String withdrawSQL = "UPDATE Account SET balance = balance - ? WHERE acct_id = ?;";

    String updateATM = "UPDATE ATM SET balance=balance - ? WHERE atm_id=?;";

    String returnBalance = "SELECT Account.balance FROM Account INNER JOIN "
        + "Member ON Account.acct_id=Member.acct_id WHERE Member.mem_id=?;";

    String returnBankBalance = "SELECT Account.bank_id, SUM(Account.balance/100.00) "
        + "AS \"TOTAL BALANCE\"\n"
        + "FROM Account GROUP BY Account.bank_id HAVING Account.bank_id=?;";
    int bankId;

    String atmLocation;
    String locationName;
    int balance;
    int numOfTran;
    int ctr = 0; // yes I could use a select count(*) here but why waste resources?
    Scanner userInput = new Scanner(System.in);
    Connection con = DriverManager.getConnection(uri);
    con.setAutoCommit(false);
    PreparedStatement insertATM = con.prepareStatement(insertATMSQL);
    while (true) {
      Integer atmId = (int) (Math.random() * 125 +
          Math.random() * 50 + 1000);
      try {

        System.out.print("Enter Bank ID: ");
        bankId = userInput.nextInt();

        System.out.print("Enter ATM location: ");
        atmLocation = userInput.next();

        System.out.print("Enter location name: ");
        locationName = userInput.next();

        System.out.print("Enter balance: ");
        balance = userInput.nextInt();

        System.out.print("Enter number of transactions: ");
        numOfTran = userInput.nextInt();

        System.out.println("ATM ID: " + atmId + "\nBank ID: " + bankId
            + "\nATM Location: " + atmLocation + "\nLocation name: " + locationName
            + "\nBalance: " + balance + "\nNumber of transactions: " + numOfTran);
        System.out.println("Are you sure you want to commit? (Y/N)");
        if (userInput.next().toLowerCase().startsWith("y")) {
          insertATM.setInt(1, atmId);
          insertATM.setInt(2, bankId);
          insertATM.setString(3, atmLocation);
          insertATM.setString(4, locationName);
          insertATM.setInt(5, balance);
          insertATM.setInt(6, numOfTran);
          insertATM.addBatch();
        }
      } catch (
          SQLException e) {
        System.out.println("The batch could not be processed - rolled back to start");
        con.rollback();
        con.close();
        break;
      }
      System.out.println("Do you want to add any other ATMs? (Y/N)");
      if (userInput.next().

          toLowerCase().

          startsWith("n")) {
        break;
      }
      if (!con.isClosed()) {
        // Now a final "Hey, are you sure?" message
        System.out.println(
            "There are " + ctr + " rolls waiting to insert. Do you wish to continue? (Y/N)\n");
        String crInput = userInput.next().toLowerCase();

        if (crInput.startsWith("y")) {
          insertATM.executeBatch();
          con.commit();
        } else {
          con.rollback();
        }
        con.close();
      }
      userInput.close();
    }

    String mem_fname;
    String mem_lname;
    String email;
    String address;

    int ssn;
    int phone;
    int birthdate;

    con.setAutoCommit(false);
    PreparedStatement insertMember = con.prepareStatement(createMemberSQL);
    while (true) {
      Integer mem_id = (int) (Math.random() * 125 +
          Math.random() * 50 + 1000);
      Integer acct_id = (int) (Math.random() * 125 +
          Math.random() * 50 + 1000);
      try {
        System.out.println("Enter First Name: ");
        mem_fname = userInput.next();
        System.out.println("Enter Last Name: ");
        mem_lname = userInput.next();
        System.out.println("Enter Social Security Number: ");
        ssn = userInput.nextInt();
        System.out.println("Enter  Phone Number: ");
        phone = userInput.nextInt();
        System.out.println("Enter Email: ");
        email = userInput.next();
        System.out.println("Enter Address: ");
        address = userInput.next();
        System.out.println("Enter Birthdate: ");
        birthdate = userInput.nextInt();
        System.out.println("First Name: " + mem_fname
            + "\nLast Name: " + mem_lname + "\nSSN: " + ssn + "\nPhone Number: "
            + phone + "\nEmail: " + email + "\nAddress" + address + "\nBirthdate: "
            + birthdate + "\nMember ID: " + mem_id + "\nAccount ID: " + acct_id);
        System.out.println("Are you sure you want to commit? (Y/N)");
        if (userInput.next().toLowerCase().startsWith("y")) {
          insertMember.setInt(1, mem_id);
          insertMember.setInt(2, acct_id);
          insertMember.setString(3, mem_fname);
          insertMember.setString(4, mem_lname);
          insertMember.setInt(5, ssn);
          insertMember.setInt(6, phone);
          insertMember.setString(7, email);
          insertMember.setString(8, address);
          insertMember.setInt(9, birthdate);
          insertMember.addBatch();
        }
      } catch (
          SQLException e) {
        System.out.println("The batch could not be processed - rolled back to start");
        con.rollback();
        con.close();
        break;
      }
      System.out.println("Do you want to add any other Members? (Y/N)");
      if (userInput.next().

          toLowerCase().

          startsWith("n")) {
        break;
      }
      if (!con.isClosed()) {
        // Now a final "Hey, are you sure?" message
        System.out.println(
            "There are " + ctr + " rolls waiting to insert. Do you wish to continue? (Y/N)\n");
        String crInput = userInput.next().toLowerCase();

        if (crInput.startsWith("y")) {
          insertATM.executeBatch();
          con.commit();
        } else {
          con.rollback();
        }
        con.close();
      }
      userInput.close();
    }
    con.setAutoCommit(false);
    PreparedStatement checkBalance = con.prepareStatement(getBalanceSQL);
  }
}

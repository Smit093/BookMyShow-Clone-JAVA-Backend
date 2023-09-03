import java.sql.*;
import java.util.*;

class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to our Application!!");
        System.out.println("Hope you'll enjoy your booking experience");
        logIn();
    }

    static void logIn() throws Exception {
        boolean valid = false;
        System.out.println("Enter a Username");
        String un = sc.nextLine();
        System.out.println("Enter a password ");
        System.out.println("(please use Uppercase , Lowercase, Special character and number)");
        String pass = sc.nextLine();
        valid = checkPass(pass);
        do {
            if (valid) {
                System.out.println("Log in Successfull");
                options();
            } else {
                System.out.println("Please enter a valid password that contains atleast one");
                System.out.println("Uppercase , Lowercase, Special character and number");
                pass = sc.nextLine();
                valid = checkPass(pass);
                if (valid) {
                    System.out.println("Log in Successfull");
                    options();
                }
            }
        } while (valid != true);
    }

    static boolean checkPass(String pass) {
        boolean valid = true;
        boolean uc = false;
        boolean lc = false;
        boolean d = false;
        boolean s = false;
        char c[] = pass.toCharArray();
        for (char curr : c) {
            if (Character.isUpperCase(curr)) {
                uc = true;
            } else if (Character.isLowerCase(curr)) {
                lc = true;
            } else if (Character.isDigit(curr)) {
                d = true;
            } else if ((int) curr >= 0 && (int) curr <= 31 || (int) curr >= 32 && (int) curr <= 127
                    || (int) curr >= 128 && (int) curr <= 255) {
                s = true;
            }
        }
        if (uc && lc && d && s) {
            valid = true;
        } else {
            valid = false;
        }
        return valid;
    }

    static void options() throws Exception {
        while (true) {
            System.out.println("What do you want to watch?");
            System.out.println("1. Movie");
            System.out.println("2. Theater Play");
            System.out.println("3. Concert");
            System.out.println("4. Stand-Up Comedy");
            System.out.println("5. Exit");
            int n = sc.nextInt();
            sc.nextLine();

            switch (n) {
                case 1: {
                    Moive.movie();
                    break;
                }

                case 2: {
                    // theater();
                    break;
                }

                case 3: {
                    // concert();
                    break;
                }

                case 4: {
                    // suComedy();
                    break;
                }

                case 5: {
                    System.out.println("Thank You for Using Our App!!");
                    System.exit(0);
                }

                default: {
                    System.out.println("Wrong chioice entered please try again");
                }
            }
        }
    }
}

class Moive {
    static String dburl;
    static String dbuser;
    static String dbpass;
    static String driver;
    static Connection con;
    static Scanner sc = new Scanner(System.in);

    static void movie() throws Exception {
        dburl = "jdbc:mysql://localhost:3306/project";
        dbuser = "root";
        dbpass = "";
        driver = "com.mysql.cj.jdbc.Driver";
        con = DriverManager.getConnection(dburl, dbuser, dbpass);
        while (true) {
            CallableStatement cst = con.prepareCall("{call displayMnames()}");
            ResultSet rs = cst.executeQuery();
            int count = 1;
            while (rs.next()) {
                System.out.println("Press " + count + " for :");
                System.out.println("Movie name : " + rs.getString(1));
                System.out.println("Theater name : " + rs.getString(2));
                System.out.println("Time: " + rs.getString(3));
                System.out.println("Day : " + rs.getString(4));
                count++;
            }
            System.out.println("press (0) to  Go Back");
            int n = sc.nextInt();
            // sc.nextLine();
            if (n == 0) {
                Main.options();
            }
            switch (n) {
                case 1: {
                    MovieBooking.book("3EKK");
                    break;
                }
                case 2: {

                    break;
                }
                case 3: {

                    break;
                }
                case 4: {

                    break;
                }
                case 5: {

                    break;
                }
                default: {
                    System.out.println("Wrong choice entered please try again");
                }
            }
        }
    }

}

class MovieBooking {
    static Scanner sc = new Scanner(System.in);
    static int seats = 20;
    static Booking b = new Booking();

    static void book(String mName) {
        System.out.println("How Many Seats do you want to book? (available seats : " + seats + ") \n Price : 300 Rs.");
        int s = sc.nextInt();
        sc.nextLine();
        if (s > seats) {
            System.out.println("Sorry Seats not Available as much as you are asking");
        } else {
            Booking.amount = s * 300;
            b.start();
        }
    }
}

class Booking extends Thread {
    static Scanner sc = new Scanner(System.in);
    static double amount;
    int seats;

    public void run() {
        System.out.println("Your total is : " + amount);
        System.out.println("Enter amount");
        double pay = sc.nextDouble();
        boolean valid = false;
        do {
            if (pay < amount) {
                System.out.println("Please entere valid amount");
                pay = sc.nextDouble();
                if (pay == amount) {
                    valid = true;
                    break;
                }
            } else {
                System.out.println("Your seats have been booked Successfully");
                System.out.println("Thank you for using our app!!");
            }
        } while (!valid);

    }
}

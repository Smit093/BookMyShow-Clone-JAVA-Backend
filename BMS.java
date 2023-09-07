import java.sql.*;
import java.util.*;
import java.io.*;

class Main {
    static String dburl;
    static String dbuser;
    static String dbpass;
    static String driver;
    static Connection con;
    static Scanner sc = new Scanner(System.in);
    static String uName, uPass, mName, tName, time, day;
    static int price, seats, amount;

    public static void main(String[] args) throws Exception {
        System.out.println("--------------------------Welcome to our Application------------------------------");
        // System.out.println("Welcome to our Application!!");
        System.out.println("Hope you'll enjoy your booking experience");
        logIn();
    }

    static void updateTransaction() throws SQLException {
        dburl = "jdbc:mysql://localhost:3306/project";
        dbuser = "root";
        dbpass = "";
        driver = "com.mysql.cj.jdbc.Driver";
        con = DriverManager.getConnection(dburl, dbuser, dbpass);
        String sql = "insert into transactions values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, uName);
        pst.setString(2, uPass);
        pst.setString(3, mName);
        pst.setString(4, tName);
        pst.setString(5, time);
        pst.setString(6, day);
        pst.setInt(7, price);
        pst.setInt(8, seats);
        pst.setInt(9, amount);
        pst.executeUpdate();
    }

    static void genrateBill() throws IOException {
        File f = new File("C:\\Users\\SMIT PATEL\\OneDrive\\Desktop\\Bills\\" + uName + ".txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f, false));

        bw.write("Thank You for chosing " + tName);
        bw.newLine();
        bw.write("Movie : " + mName);
        bw.newLine();
        bw.write("Theater : " + tName);
        bw.newLine();
        bw.write("Seats : " + seats);
        bw.newLine();
        bw.write("Time : " + time);
        bw.newLine();
        bw.write("Day : " + day);
        bw.newLine();
        bw.write("Price : " + price);
        bw.newLine();
        bw.write("Amount : " + amount);
        bw.newLine();
        bw.write("Booked by : " + uName);
        bw.flush();
        bw.close();
    }

    static void logIn() throws Exception {
        boolean valid = false;
        System.out.println("Enter a Username");
        String un = sc.nextLine();
        Main.uName = un;
        System.out.println("Enter a password ");
        System.out.println("(please use Uppercase , Lowercase, Special character and number)");
        String pass = sc.nextLine();
        valid = checkPass(pass);
        do {
            if (valid) {
                System.out.println("Log in Successfull");
                Moive.movie();
            } else {
                System.out.println("Please enter a valid password that contains atleast one");
                System.out.println("Uppercase , Lowercase, Special character and number");
                pass = sc.nextLine();
                valid = checkPass(pass);
                if (valid) {
                    System.out.println("Log in Successfull");
                    Moive.movie();
                }
            }
        } while (valid != true);
        Main.uPass = pass;
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
        CallableStatement cst = con.prepareCall("{call displayMnames()}");
        ResultSet rs = cst.executeQuery();
        int count = 1;
        System.out.println("----------------------Trending Movies----------------------");
        while (rs.next()) {
            System.out.println("------------------------------------------------------------");
            System.out.println("Press " + count + " for :");
            System.out.println("|   Movie name : " + rs.getString(1) + "                  ");
            System.out.println("|   Theater name : " + rs.getString(2) + "                ");
            System.out.println("|   Time : " + rs.getString(3) + "                        ");
            System.out.println("|   Day : " + rs.getString(4) + "                         ");
            System.out.println("|   Price : " + rs.getInt(5) + "                         ");
            System.out.println("------------------------------------------------------------");
            count++;
        }
        int n = sc.nextInt();

        switch (n) {
            case 1: {
                MovieBooking.book("3EKKa", "PVR : Acropolis", "3:00 PM", "Friday", 300, 60);
                Main.mName = "3 Ekka";
                Main.tName = "PVR : Acropolis";
                Main.time = "3:00 PM";
                Main.day = "Friday";
                Main.price = 300;
                MovieBooking.seats = 60;
                break;
            }
            case 2: {
                MovieBooking.book("OMG2", "Cinepolis : Alpha One", "6:45 PM", "Saturday", 250, 70);
                Main.mName = "OMG 2";
                Main.tName = "Cinepolis : Alpha One";
                Main.time = "6:45 PM";
                Main.day = "Saturday";
                Main.price = 250;
                MovieBooking.seats = 70;
                break;
            }
            case 3: {
                MovieBooking.book("BATMAN", "Rajhans Cinemas", "10:30 PM", "Sunday", 350, 50);
                Main.mName = "The Batman";
                Main.tName = "Rajhans Cinemas";
                Main.time = "10:30 PM";
                Main.day = "Sunday";
                Main.price = 350;
                MovieBooking.seats = 50;
                break;
            }
            case 4: {
                MovieBooking.book("OPPENHIMER", "PVR : Motera", "11:00 AM", "Saturday", 320, 80);
                Main.mName = "Oppenhimer";
                Main.tName = "PVR : Motera";
                Main.time = "11:00 AM";
                Main.day = "Saturday";
                Main.price = 320;
                MovieBooking.seats = 80;
                break;
            }
            case 5: {
                MovieBooking.book("AVATAR", "NY Cinemas : Motera", "9:00PM", "Sunday", 280, 100);
                Main.mName = "Avatar : the way of water";
                Main.tName = "NY Cinemas : Motera";
                Main.time = "9:00PM";
                Main.day = "Sunday";
                Main.price = 280;
                MovieBooking.seats = 100;
                break;
            }

            default: {
                System.out.println("Wrong choice entered please try again");
            }
        }
    }

}

class MovieBooking {
    static Scanner sc = new Scanner(System.in);
    static int seats = 20;
    static Booking b = new Booking();

    static void book(String mName, String tName, String time, String day, int price, int seats) throws SQLException {
        System.out.println("------------------------------------------------------------------");
        System.out.println("Movie : " + mName);
        System.out.println("Theater : " + tName);
        System.out.println("Time : " + time);
        System.out.println("Day : " + day);
        System.out.println("Price : " + price);
        System.out.println("Total Seats Available : " + seats);
        System.out.println("------------------------------------------------------------------");
        System.out.println("How Mmany seats do you want to book?");
        int s = sc.nextInt();
        sc.nextLine();
        if (s > seats) {
            System.out.println("We are Sorry Seats not available as much as you are asking for");
        } else {
            seats -= s;
            Main.seats = s;
            Booking.amount = s * price;
            System.out.println("Total Amount : " + Booking.amount);
            Main.amount = (int) Booking.amount;
            b.setName(Main.uName);
            b.start();
        }
    }
}

class Booking extends Thread {
    Payement p = new Payement();
    static Scanner sc = new Scanner(System.in);
    static double amount;
    int seats;

    public void run() {
        try {
            p.bill(amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Payement {
    static OTP o = new OTP();
    static int genratedOTP = 0;
    static Scanner sc = new Scanner(System.in);

    synchronized void bill(double amount) throws Exception {
        System.out.println("Choose a payment method");
        System.out.println("1. Net Banking");
        System.out.println("2. UPI");
        int n = sc.nextInt();
        sc.nextLine();
        if (n == 1) {
            netBanking();
        } else if (n == 2) {

        } else {
            System.out.println("Wrong choice!\nplease select a valid option");
        }

    }

    static void netBanking() throws Exception {
        System.out.println("Enter your account number");
        String accNo = sc.nextLine();
        System.out.println("Enter your phone number");
        String pNo = sc.nextLine();
        boolean valid = false;
        while (!valid) {
            if (accNo.length() != 12 && pNo.length() != 10) {
                System.out.println("Invalid Phone number or Account no please try again");
                System.out.println("Account number");
                accNo = sc.nextLine();
                System.out.println("Phone number");
                pNo = sc.nextLine();
            } else {
                o.start();
                System.out.println("Wait for 5 seconds \nYour OTP is being genrated");
                int otp = sc.nextInt();
                sc.nextLine();
                valid = false;
                while (!valid) {
                    if (otp != OTP.otp) {
                        System.out.println("OTP Miss Matched!! \nplease try again");
                        otp = sc.nextInt();
                        sc.nextLine();
                    } else {
                        pay();
                        valid = true;
                        break;
                    }
                }
            }
        }
    }

    static void pay() throws Exception {
        System.out.println("Total Amount : " + Booking.amount);
        System.out.println("Enter amount");
        int amt = sc.nextInt();
        sc.nextLine();
        boolean valid = false;
        while (!valid) {
            if (amt != Booking.amount) {
                System.out.println("Please enter valid amount : " + Booking.amount);
                amt = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Your seats have been booked Successfully");
                System.out.println("Thank you for using our app!!");
                Main.updateTransaction();
                Main.genrateBill();
                valid = true;
            }
        }
    }
}

class OTP extends Thread {
    OTPGenrator o = new OTPGenrator();
    static int otp = 0;

    public void run() {
        try {
            sleep(5000);
        } catch (Exception e) {
        }
        try {
            OTP.otp = o.genrateOTP();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class OTPGenrator {
    int genrateOTP() throws IOException {
        int otp = (int) (Math.random() * 4000) + 1;
        Payement.genratedOTP = otp;
        File f = new File("C:\\Users\\SMIT PATEL\\OneDrive\\Desktop\\OTPs\\otp.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
        bw.write("Your OTP is : " + otp);
        bw.newLine();
        bw.flush();
        System.out.println("Please enter otp");
        bw.close();
        return otp;
    }
}

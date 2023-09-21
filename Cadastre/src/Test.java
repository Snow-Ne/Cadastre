import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Test {

    public static Scanner scanner = new Scanner(System.in);
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static boolean checkDate(String date){
        try {
            LocalDate.parse(date, dtf);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static boolean isDateHigher(LocalDate startDate, String date){

        LocalDate endDate = null;
        try {
            endDate = LocalDate.parse(date, dtf);
            if (endDate.compareTo(startDate) >= 0){
                return true;
            } else {
                return  false;
            }
        } catch (Exception e){
            return false;
        }
    }

    public  static boolean checkId(String id){
        try {
            int num = Integer.parseInt(id);
            if (num < 1){
                return false;
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static boolean checkSurface(String surfaceArea){
        try {
            double num = Double.parseDouble(surfaceArea);
            if (num > 0){
                return  true;
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }

    public static boolean checkMaxSurface(String surfaceArea, double min){
        try {
            double num = Double.parseDouble(surfaceArea);
            if (num >= min){
                return true;
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }

    public static boolean checkParcel(String parcel){
        if (parcel.length() == 4){
            return true;
        }
        return false;
    }

    private static void writeCadastre(Cadastre cadastre){
        System.out.println("Write cadaster name");
        String name = scanner.nextLine();
        cadastre.setName(name);
        System.out.println("Write the cadaster address");
        String address = scanner.nextLine();
        cadastre.setAddress(address);
        System.out.println("Data has been successfully added");
    }

    public static void writeRealEstate(Cadastre cadastre){
        int id = 0;
        String idS = null;
        String owner = null;
        double surfaceArea = 0.0;
        String surfaceAreaS = null;
        String parcelNumber = null;
        String street = null;
        LocalDate dateChange = null;
        String dateChangeS = null;
        do {
            System.out.print("Enter id number: ");
            idS = scanner.nextLine();
        } while (!checkId(idS));
        id = Integer.parseInt(idS);
        System.out.print("Enter owner: ");
        owner = scanner.nextLine();
        do {
            System.out.print("Enter parcel surface: ");
            surfaceAreaS = scanner.nextLine();
        } while (!checkSurface(surfaceAreaS));
        surfaceArea = Double.parseDouble(surfaceAreaS);
        do {
            System.out.print("Enter parcel number: ");
            parcelNumber = scanner.nextLine();
        } while (!checkParcel(parcelNumber));
        System.out.print("Enter street name: ");
        street = scanner.nextLine();
        do {
            System.out.print("Enter the date of the last change: ");
            dateChangeS = scanner.nextLine();
        } while (!checkDate(dateChangeS));
        dateChange = LocalDate.parse(dateChangeS, dtf);

        RealEstate realEstate = new RealEstate(id, owner, surfaceArea, parcelNumber, street, dateChange);
        boolean check = cadastre.addingRealEstate(realEstate);
        if (check) {
            System.out.println("Real estate was successfully added");
        } else {
            System.out.println("Real estate was not added");
        }
    }

    public static void changeRealEstate(Cadastre cadastre){

        int id = 0;
        String idS = null;
        String owner = null;
        double surfaceArea = 0.0;
        String surfaceAreaS = null;
        String parcelNumber = null;
        String street = null;
        LocalDate dateChange = null;
        String dateChangeS = null;
        do {
            System.out.print("Enter id number: ");
            idS = scanner.nextLine();
        } while (!checkId(idS));
        id = Integer.parseInt(idS);
        System.out.print("Enter owner: ");
        owner = scanner.nextLine();
        do {
            System.out.print("Enter parcel surface: ");
            surfaceAreaS = scanner.nextLine();
        } while (!checkSurface(surfaceAreaS));
        surfaceArea = Double.parseDouble(surfaceAreaS);
        do {
            System.out.print("Enter parcel number: ");
            parcelNumber = scanner.nextLine();
        } while (!checkParcel(parcelNumber));
        System.out.print("Enter street name: ");
        street = scanner.nextLine();
        do {
            System.out.print("Enter the date of the last change: ");
            dateChangeS = scanner.nextLine();
        } while (!checkDate(dateChangeS));
        dateChange = LocalDate.parse(dateChangeS, dtf);

        RealEstate realEstate = new RealEstate(id, owner, surfaceArea, parcelNumber, street, dateChange);
        RealEstate check = cadastre.changeOfRealEstate(realEstate);
        if (check != null){
            System.out.println("Real estate was successfully added");
        }else {
            System.out.println("Real estate was not added");
        }
    }

    public static void deleteRealEstate(Cadastre cadastre){

        int id = 0;
        String idS = null;
        do {
            System.out.println("Enter id number for deleting ");
            idS = scanner.nextLine();
        } while (!checkId(idS));
        id = Integer.valueOf(idS);
        RealEstate check  = cadastre.realEstateDelete(id);
        if (check != null){
            System.out.println("Real estate was successfully deleted");
        } else {
            System.out.println("Real estate was not deleted");
        }
    }

    public static void searchByOwner(Cadastre cadastre){
      System.out.println("Enter the owners name: ");
      String owner = scanner.nextLine();
      cadastre.writeRealEstateOwner(owner);
    }

    public static void  searchByParcel(Cadastre cadastre){
        String parcelNumber = null;
        double surfaceAreaMin = 0.0;
        String surfaceAreaMinS = null;
        double surfaceAreaMax = 0.0;
        String surfaceAreaMaxS = null;
        do {
            System.out.println("Enter the min surface of the parcel: ");
            surfaceAreaMinS = scanner.nextLine();
        } while (!checkSurface(surfaceAreaMinS));
        surfaceAreaMin = Double.parseDouble(surfaceAreaMinS);
        do {
            System.out.println("Enter the max surface of the parcel:  ");
            surfaceAreaMaxS = scanner.nextLine();
        } while (!checkMaxSurface(surfaceAreaMaxS, surfaceAreaMin));
        surfaceAreaMax = Double.parseDouble(surfaceAreaMaxS);
        do {
            System.out.println("Enter parcel number: ");
            parcelNumber = scanner.nextLine();
        } while (!checkParcel(parcelNumber));
        cadastre.writeRealEstateOnParcel(parcelNumber, surfaceAreaMin, surfaceAreaMax);
    }

    public static void AvrSurface(Cadastre cadastre){
        System.out.println("Enter street: ");
        String street = scanner.nextLine();
        cadastre.averageStreetSurface(street);
    }

    public static void  changedRealEstate(Cadastre cadastre){
        LocalDate minDate = null;
        String minDateS = null;
        LocalDate maxDate = null;
        String maxDateS = null;
        do {
            System.out.println("Enter min date for search: ");
            minDateS = scanner.nextLine();
        } while (!checkDate(minDateS));
        minDate = LocalDate.parse(minDateS, dtf);
        do {
            System.out.println("Enter max date for search: ");
            maxDateS = scanner.nextLine();
        } while (!isDateHigher(minDate, maxDateS));
        maxDate = LocalDate.parse(maxDateS, dtf);
        cadastre.changes(minDate, maxDate);
    }

    public static void main(String[] args){
        Cadastre cadastre = new Cadastre();
        cadastre.load("katastar.txt");
        String answer = null;

        do {

            System.out.println("Menu:");
            System.out.println("1. Enter Real estate");
            System.out.println("2. Enter new Real estate");
            System.out.println("3. List of Real estate");
            System.out.println("4. Change of real estate");
            System.out.println("5. Delete Real estate");
            System.out.println("6. Real estate search by owner");
            System.out.println("7. Real estate search by parcel");
            System.out.println("8. Average surface area in the street");
            System.out.println("9. Changed real estate");
            System.out.println("10. Data from cadastre");
            System.out.println("x. Exit");


            answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    writeCadastre(cadastre);
                    cadastre.save("katastar.txt");
                    break;
                case "2":
                    writeRealEstate(cadastre);
                    cadastre.save("katastar.txt");
                    break;
                case "3":
                    cadastre.writeRealEstate();
                    break;
                case "4":
                    changedRealEstate(cadastre);
                    cadastre.save("katastar.txt");
                    break;
                case "5":
                    deleteRealEstate(cadastre);
                    cadastre.save("katastar.txt");
                    break;
                case "6":
                    searchByOwner(cadastre);
                    break;
                case "7":
                    searchByParcel(cadastre);
                    break;
                case "8":
                    AvrSurface(cadastre);
                    break;
                case "9":
                    changedRealEstate(cadastre);
                    break;
                case "10":
                    System.out.println(cadastre);
                    break;
                case "x":
                    break;
                default:
                    System.out.println("Wrong option, try again.");
            }

        } while (!answer.equals("x"));

        scanner.close();

    }

}

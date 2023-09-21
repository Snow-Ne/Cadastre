import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Cadastre {
    private String name;
    private String address;
    private ArrayList<RealEstate> realEstateList;

    public Cadastre(){
        this.name = "";
        this.address = "";
        this.realEstateList = new ArrayList<>();
    }

    public Cadastre(String name, String address){
        super();
        this.name = name;
        this.address = address;
        this.realEstateList = new ArrayList<>();
    }

    public Cadastre(String name, String address, ArrayList<RealEstate> realEstateList){
        super();
        this.name = name;
        this.address = address;
        this.realEstateList = realEstateList;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public ArrayList<RealEstate> getRealEstateList(){
        return realEstateList;
    }

    public void setRealEstateList(ArrayList<RealEstate> realEstateList){
        this.realEstateList = realEstateList;
    }

    public boolean addingRealEstate(RealEstate realEstate){

        for (int i = 0; i < this.getRealEstateList().size(); i++){
            if(realEstate.getId() == this.realEstateList.get(i).getId()){
                return false;
            }
        }
        this.realEstateList.add(realEstate);
        return true;
    }

    public RealEstate realEstateDelete(int id){
        for (int i = 0; i < this.realEstateList.size(); i++){
            if (this.realEstateList.get(i).getId() == id){
                return this.realEstateList.remove(i);
            }
        }
        return  null;
    }

    public  void writeRealEstate(){
        System.out.printf("%15s %15s %15s %15s %15s %15s\n", "Id", "Owner", "Surface area", "Parcel number", "Street", "Date of change");

        for (int i = 0; i < this.realEstateList.size(); i++){
            System.out.println(this.realEstateList.get(i));
        }
    }

    public RealEstate changeOfRealEstate(RealEstate changedRealEstate){
        for (int i = 0; i < this.realEstateList.size(); i++){
            if (this.realEstateList.get(i).getId() == changedRealEstate.getId()){
                return this.realEstateList.set(i, changedRealEstate);
            }
        }
        return null;
    }

    public void save(String path){
        ArrayList<String> line = new ArrayList<String>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        line.add(this.name);
        line.add(this.address);
        for (int i = 0; i < this.realEstateList.size(); i++){
            RealEstate realEstate = this.realEstateList.get(i);
            int id = realEstate.getId();
            String owner = realEstate.getOwner();
            double surfaceArea = realEstate.getSurfaceArea();
            String parcelNumber = realEstate.getParcelNumber();
            String street = realEstate.getStreet();
            String formatDate = dtf.format(realEstate.getDateChanger());
            String lines = id + ";" + owner + "," + surfaceArea + "," + parcelNumber + "," + street + "," + formatDate;
            line.add(lines);
        }

        try {
            Files.write(Paths.get(path), line, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (java.io.IOException e){
            System.out.println("Error at saving data");
        }
    }

    public void load(String path){
        this.realEstateList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        List<String> line;
        try {
            line = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
            this.name = line.get(0);
            this.address = line.get(1);
            for (int i = 2; i < line.size(); i++){
                String[] attributes = line.get(i).split(";");
                int id = Integer.parseInt(attributes[0]);
                String owner = attributes[1];
                double surfaceArea = Double.parseDouble(attributes[2]);
                String parcelNumber = attributes[3];
                String street = attributes[4];
                LocalDate dateChange = LocalDate.parse(attributes[5], dtf);

                RealEstate realEstate = new RealEstate(id, owner, surfaceArea, parcelNumber, street, dateChange);
                this.realEstateList.add(realEstate);
            }
        } catch (java.io.IOException e){
            System.out.println("Error in loading file");
        } catch (NumberFormatException e){
            System.out.println("Error in number conversion");
        } catch (DateTimeParseException e){
            System.out.println("Error in date conversion");
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    public ArrayList<RealEstate> writeRealEstateOwner(String owner){
        ArrayList<RealEstate> resultsList = new ArrayList<>();
        for (int i = 0; i < this.realEstateList.size(); i++){
            if (this.realEstateList.get(i).getOwner().equals(owner)){
                resultsList.add(this.realEstateList.get(i));
                System.out.println(this.realEstateList.get(i));
            }
        }
        return resultsList;
    }

    public ArrayList<RealEstate> writeRealEstateOnParcel(String parcelNumber, double minSurfaceArea, double maxSurfaceArea){
        System.out.printf("%15s %15s %15s %15s %15s %15s\n", "Id", "Owner", "Parcel number", "Street", "Date of change");
        ArrayList<RealEstate> resultList = new ArrayList<>();
        for (int i = 0; i < this.realEstateList.size(); i++){
            if (this.realEstateList.get(i).getParcelNumber().equals(parcelNumber) && this.realEstateList.get(i).getSurfaceArea() >= minSurfaceArea && this.realEstateList.get(i).getSurfaceArea() <= maxSurfaceArea){
                resultList.add(this.realEstateList.get(i));
                System.out.println(this.realEstateList.get(i));
            }
        }
        return resultList;

    }

    public double averageStreetSurface(String street){

        int num = 0;
        double sum = 0.0;
        double avr = 0.0;
        for (int i = 0; i < this.realEstateList.size(); i++){
            if (this.realEstateList.get(i).getStreet().equals(street)){
                sum += this.realEstateList.get(i).getSurfaceArea();
                num ++;
            }
        }
        if (num > 0){
            avr = sum / num;
            System.out.println("Average surface area in the street " + street + " is: " + avr);
            return avr;
        }
        System.out.println("There was a error");
        return avr;
    }

    public ArrayList<RealEstate> changes(LocalDate minDate, LocalDate maxdate){
        System.out.printf("%15s %15s %15s %15s %15s %15s\n", "Id", "Owner", "Surface area", "Parcel number", "Street", "Change of date");
        ArrayList<RealEstate> resultList = new ArrayList<>();
        for (int i = 0; i < this.realEstateList.size(); i++){
            if (this.realEstateList.get(i).getDateChanger().compareTo(minDate) >= 0 && this.realEstateList.get(i).getDateChanger().compareTo(maxdate)<= 0){
                resultList.add(this.realEstateList.get(i));
                System.out.println(this.realEstateList.get(i));
            }
        }
        return resultList;
    }

    private double totalSurface(){
        double sum = 0.0;
        for (int i = 0; i < this.realEstateList.size(); i++){
            sum += this.realEstateList.get(i).getSurfaceArea();
        }
        return sum;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name library ").append(this.name).append("\n");
        sb.append("Adress library ").append(this.address).append("\n");
        sb.append("Number of real estates ").append(this.realEstateList.size()).append("\n");
        sb.append("Total surface of all real estate").append(totalSurface());
        return sb.toString();

    }
}
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RealEstate {
    private int id;
    private String owner;
    private double  surfaceArea;
    private String parcelNumber;
    private String street;
    private LocalDate dateChanger;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public RealEstate(){
        this.owner = "";
        this.parcelNumber = "";
        this.street = "";
        this.dateChanger = LocalDate.now();
    }

    public RealEstate(int id, String owner, double surfaceArea, String parcelNumber, String street, LocalDate dateChanger){
        super();
        this.id = id;
        this.owner = owner;
        this.surfaceArea = surfaceArea;
        this.parcelNumber = parcelNumber;
        this.street = street;
        this.dateChanger = dateChanger;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getOwner(){
        return owner;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

    public double getSurfaceArea(){
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea){
        this.surfaceArea = surfaceArea;
    }

    public String getParcelNumber(){
        return parcelNumber;
    }

    public void setParcelNumber(String parcelNumber){
        this.parcelNumber = parcelNumber;
    }

    public String getStreet(){
        return street;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public LocalDate getDateChanger(){
        return dateChanger;
    }

    public void  setDateChanger(LocalDate dateChanger){
        this.dateChanger = dateChanger;
    }

    @Override
    public String toString(){
        return String.format("%15d %15s %10.2f %15s %15s %15s", this.id, this.owner, this.surfaceArea, this.parcelNumber, this.street, dtf.format(this.dateChanger));
    }
}

package web.model;

public class Car {
    private String model;
    private String series;
    private int number;

    public Car(String model, String series, int number){
        this.model = model;
        this.series = series;
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
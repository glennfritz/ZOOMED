package zuoix.com.zoomed.models;

public class Car {
    private String carMatricule;
    private String carModel;

    public Car(String carMatricule, String carModel) {
        this.carMatricule = carMatricule;
        this.carModel = carModel;
    }

    public String getCarMatricule() {
        return carMatricule;
    }

    public void setCarMatricule(String carMatricule) {
        this.carMatricule = carMatricule;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}

package zuoix.com.zoomed.models;

public class TrackableDevice {
    public TrackableDevice(String type, String name, int generation, int device_number) {
        this.type = type;
        this.name = name;
        this.generation = generation;
        this.device_number = device_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int getDevice_number() {
        return device_number;
    }

    public void setDevice_number(int device_number) {
        this.device_number = device_number;
    }

    private String name;
    private int generation;
    private int device_number;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

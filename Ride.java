package CarPoolCopy;

class Ride {

    private String source;
    private String destination;
    private int fare;
    private int id;

    public Ride(String source, String destination, int fare, int id) {
        this.source = source;
        this.destination = destination;
        this.fare = fare;
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Ride{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", fare=" + fare +
                ", id=" + id +
                '}';
    }
}

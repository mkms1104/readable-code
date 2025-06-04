package cleancode.studycafe.tobe.pass;

public class Pass {
    private final PassDurationType passDurationType;
    private final int duration;
    private final int price;

    private Pass(PassDurationType passDurationType, int duration, int price) {
        this.passDurationType = passDurationType;
        this.duration = duration;
        this.price = price;
    }

    public static Pass of(PassDurationType passDurationType, int duration, int price) {
        return new Pass(passDurationType, duration, price);
    }

    public String display() {
        String durationType = passDurationType.getDescription();
        return String.format("%s%s - %d원", duration, durationType, price);
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }
}

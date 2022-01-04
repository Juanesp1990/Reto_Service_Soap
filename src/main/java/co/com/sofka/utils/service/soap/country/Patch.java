package co.com.sofka.utils.service.soap.country;


public enum Patch {
    COUNTRY(System.getProperty("user.dir")
            + "\\src\\test\\resources\\files\\servicios\\soap\\country\\country.xml");

    private final String value;

    Patch(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

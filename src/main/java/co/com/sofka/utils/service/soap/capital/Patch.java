package co.com.sofka.utils.service.soap.capital;


public enum Patch {
    CAPITAL(System.getProperty("user.dir")
            + "\\src\\test\\resources\\files\\servicios\\soap\\capital\\capital.xml");

    private final String value;

    Patch(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

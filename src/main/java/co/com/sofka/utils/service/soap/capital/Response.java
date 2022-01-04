package co.com.sofka.utils.service.soap.capital;

public enum Response {
    CAPITAL_RESPONSE("<m:CapitalCityResult>%s</m:CapitalCityResult>");

    private final String value;

    Response(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

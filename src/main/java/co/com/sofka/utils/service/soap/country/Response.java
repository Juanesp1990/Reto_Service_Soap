package co.com.sofka.utils.service.soap.country;

public enum Response {
    COUNTRY_RESPONSE("<m:CountryNameResult>%s</m:CountryNameResult>");

    private final String value;

    Response(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

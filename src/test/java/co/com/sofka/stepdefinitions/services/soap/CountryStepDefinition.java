package co.com.sofka.stepdefinitions.services.soap;

import co.com.sofka.ServiceSetup;
import co.com.sofka.models.SearchCountry;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static co.com.sofka.questions.ResturnSoapServiceResponse.resturnSoapServiceResponse;
import static co.com.sofka.tasks.DoPost.doPost;
import static co.com.sofka.utils.FileUtilities.readFile;
import static co.com.sofka.utils.service.soap.country.Patch.COUNTRY;
import static co.com.sofka.utils.service.soap.country.Response.COUNTRY_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class CountryStepDefinition extends ServiceSetup {

    private static final Logger LOGGER = Logger.getLogger(CountryStepDefinition.class);
    private SearchCountry searchCountry;

    @Given("que el usuario quiera buscar con el código {string}")
    public void queElUsuarioQuieraBuscarConElCodigo (String codigoPais) {
        try {
            super.setup();
            searchCountry = new SearchCountry();
            searchCountry.setCodigo(codigoPais);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }


    @When("el usuario realiza la petición de busqueda")
    public void elUsuarioRealizaLaPeticionDeBusqueda () {
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequest())
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }

    @Then("el ususario debería obtener como nombre de país {string}")
    public void elUsusarioDeberiaObtenerComoNombreDePais (String resultado) {
        try {
            searchCountry.setResultado(resultado);
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la busqueda es :  ",
                            resturnSoapServiceResponse(),
                            containsString(bodyResponse()))
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }

    @Given("que el usuario quiera buscar un indicativo  que no es válido {string}")
    public void queElUsuarioQuieraBuscarUnIndicativoQueNoEsValido (String errorEnMensaje) {
        try {
            super.setup();
            searchCountry = new SearchCountry();
            searchCountry.setCodigo(errorEnMensaje);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }


    @When("el usuario hace la petición de busqueda del país")
    public void elUsuarioHaceLaPeticionDeBusquedaDelPais () {
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequest())
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }

    @Then("el ususario debería obtener el resultado para este {string}")
    public void elUsusarioDeberiaObtenerElResultadoParaEste (String resultado) {
        try {
            searchCountry.setResultado(resultado);
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la busqueda es :  ",
                            resturnSoapServiceResponse(),
                            containsString(bodyResponse()))
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }

    private SearchCountry searchCountry () {
        return searchCountry;
    }

    private String bodyRequest () {
        return String.format(readFile(COUNTRY.getValue()), searchCountry().getCodigo());
    }

    private String bodyResponse () {
        return String.format(COUNTRY_RESPONSE.getValue(), searchCountry().getResultado());
    }
}

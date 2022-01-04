package co.com.sofka.stepdefinitions.services.soap;


import co.com.sofka.ServiceSetup;
import co.com.sofka.models.SearchCapital;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static co.com.sofka.questions.ResturnSoapServiceResponse.resturnSoapServiceResponse;
import static co.com.sofka.tasks.DoPost.doPost;
import static co.com.sofka.utils.FileUtilities.readFile;
import static co.com.sofka.utils.service.soap.capital.Patch.CAPITAL;
import static co.com.sofka.utils.service.soap.capital.Response.CAPITAL_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;


public class CapitalStepDefinition extends ServiceSetup {
    private static final Logger LOGGER = Logger.getLogger(CapitalStepDefinition.class);
    private SearchCapital searchCapital;

    @Given("que el usuario quiera buscar el indicativo {string}")
    public void queElUsuarioQuieraBuscarElIndicativo (String codigoPais) {
        try {
            super.setup();
            searchCapital = new SearchCapital();
            searchCapital.setCodigo(codigoPais);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }

    @When("el usuario hace la petición de busqueda")
    public void elUsuarioHaceLaPeticionDeBusqueda () {
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

    @Then("el ususario debería obtener como capital {string}")
    public void elUsusarioDeberiaObtenerComoCapital (String resultado) {
        try {
            searchCapital.setResultado(resultado);
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la busqueda es : ",
                            resturnSoapServiceResponse(),
                            containsString(bodyResponse()))
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }

    @Given("que el usuario quiera buscar un indicativo  que no concuerda {string}")
    public void queElUsuarioQuieraBuscarUnIndicativoQueNoConcuerda (String codigoError) {
        try {
            super.setup();
            searchCapital = new SearchCapital();
            searchCapital.setCodigo(codigoError);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @When("el usuario hace la petición de busqueda de la cápital")
    public void elUsuarioHaceLaPeticionDeBusquedaDeLaCapital () {
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

    @Then("el ususario debería obtener como resultado {string}")
    public void elUsusarioDeberiaObtenerComoResultado (String mensajeError) {
        try {
            searchCapital.setResultado(mensajeError);
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la busqueda es : ",
                            resturnSoapServiceResponse(),
                            containsString(bodyResponse()))
            );
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }


    private SearchCapital seacrchCapital () {
        return searchCapital;
    }

    private String bodyRequest () {
        return String.format(readFile(CAPITAL.getValue()), seacrchCapital().getCodigo());
    }

    private String bodyResponse () {
        return String.format(CAPITAL_RESPONSE.getValue(), seacrchCapital().getResultado());
    }
}

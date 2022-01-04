Feature: Nombre del país
  Como usuario de un servicio de busquda de nombres de paises
  necesito validar que la funcionalidad busqueda por código de pais

  @busquedaCountryCorrecta
  Scenario: busqueda correcta country
    Given que el usuario quiera buscar con el código "CO"
    When el usuario realiza la petición de busqueda
    Then el ususario debería obtener como nombre de país "Colombia"

  @busquedaCountryIncorrecta
  Scenario: busqueda incorrecta country
    Given que el usuario quiera buscar un indicativo  que no es válido "Co"
    When el usuario hace la petición de busqueda del país
    Then el ususario debería obtener el resultado para este "Country not found in the database"
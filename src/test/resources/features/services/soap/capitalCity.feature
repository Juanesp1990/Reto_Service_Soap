Feature: Nombre de la ciudad capital
  Como usuario de un servicio de busquda de capitales de paises
  necesito validar que la funcionalidad busqueda por código de pais para el llenado
  de datos que se requieren para comprar productos internacionales

  @busquedacapitalcorrecta
  Scenario: busqueda correcta capital
    Given que el usuario quiera buscar el indicativo "CO"
    When el usuario hace la petición de busqueda
    Then el ususario debería obtener como capital "Bogota"

  @busquedacapitalIncorrecta
  Scenario: busqueda incorrecta capital
    Given que el usuario quiera buscar un indicativo  que no concuerda "Co"
    When el usuario hace la petición de busqueda de la cápital
    Then el ususario debería obtener como resultado "Country not found in the database"
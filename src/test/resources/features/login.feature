Feature: Login de usuarios en SauceDemo

  @johatest
  Scenario: Login exitoso con credenciales Validas
    Given estoy en la pagina de login de SauceDemo
    When ingreso el usuario "standard_user"
    And ingreso la contraseña "secret_sauce"
    And hago click en el boton Login
    Then deberia ver la pagina de inventario
    And el titulo deberia ser "Products"



    @regression @johatest @smoke
    Scenario: Login incorrecto con credenciales invalidas
      Given estoy en la pagina de login de SauceDemo
      When ingreso el usuario "standard_user"
      And ingreso la contraseña "password"
      And hago click en el boton Login
      Then debería ver el mensaje "Epic sadface: Username and password do not match any user in this service"
      And deberia permanecer en la pagina de Login
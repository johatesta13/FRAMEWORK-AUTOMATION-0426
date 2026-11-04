package ar.org.icaro.Steps;

import ar.org.icaro.Runner.Hooks;
import ar.org.icaro.pages.InventoryPage;
import ar.org.icaro.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class loginStep {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    //LOGIN EXITOSO CON CREDENCIALES VALIDAS

    @Given("estoy en la pagina de login de SauceDemo")
    public void estoyenlapaginadelogin(){

        loginPage = new LoginPage(Hooks.driver);
        loginPage.goTo();

        System.out.println("NAVEGANDO A LA PAGINA DE LOGIN");
    }

    @When("ingreso el usuario {string}")
    public void IngresoUsuario(String usuario){
        loginPage.enterUsuario(usuario);
        System.out.println("Ingrese el usuario");
    }

    @And("ingreso la contraseña {string}")
    public void IngresoContra(String pass){
        loginPage.enterPass(pass);
        System.out.println("Ingrese la contraseña");
    }

    @And("hago click en el boton Login")
    public void HagoClickenLogin(){
        loginPage.clickLogin();
        System.out.println("HICE CLICK EN LOGIN");
    }

    @Then("deberia ver la pagina de inventario")
    public void RedireccionPaginaInventario(){
        inventoryPage = new InventoryPage(Hooks.driver);
        Assert.assertTrue(inventoryPage.isOnInventoryPage(),"Deberia haber iniciado sesion de manera correcta");


    }

    @And("el titulo deberia ser {string}")
    public void ValidarTitulo(String titulo){
        inventoryPage = new InventoryPage(Hooks.driver);
        Assert.assertEquals(inventoryPage.getInventoryTitle(),titulo);

        System.out.println("TITULO DE LA PAGINA" + titulo);
    }

    //ultimos dos steps del login fallido el resto son iguales
    @Then("debería ver el mensaje {string}")
    public void ValidarMensajeError(String mensaje){
        Assert.assertEquals(loginPage.getErrorMessage(), mensaje);
        System.out.println("EL MENSAJE ES:" + mensaje);
    }

    @And("deberia permanecer en la pagina de Login")
    public void ValidarPermanenciaEnLogin(){
        Assert.assertTrue(loginPage.isOnLoginPage(), "No debe iniciar sesion con un user incorrecto");
        System.out.println("Sigo en la pagina de login");
    }
}

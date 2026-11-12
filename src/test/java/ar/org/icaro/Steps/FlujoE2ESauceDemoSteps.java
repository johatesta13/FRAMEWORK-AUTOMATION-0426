package ar.org.icaro.Steps;

import ar.org.icaro.Runner.Hooks;
import ar.org.icaro.pages.CartPage;
import ar.org.icaro.pages.CheckoutPage;
import ar.org.icaro.pages.InventoryPage;
import ar.org.icaro.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.net.CacheRequest;

public class FlujoE2ESauceDemoSteps {

    InventoryPage inventoryPage = new InventoryPage(Hooks.driver);
    CartPage cartPage = new CartPage(Hooks.driver);
    CheckoutPage checkoutPage = new CheckoutPage(Hooks.driver);
    //FLUJO 1: LOGIN EXITOSO CON CREDENCIALES VALIDAS Y AGREGAR UN PRODUCTO AL CARRITO

    @Given("estoy en la página de inventario")
    public void estoyEnLaPaginaDeInventario() {
        inventoryPage.isOnInventoryPage();
        System.out.println("ESTOY EN LA PAGINA DE INVENTARIO");


    }


    @When("agrego {string} al carrito")
    public void agregoAlcarrito(String producto) {
        inventoryPage.agregarProducto(producto);
        System.out.println("AGREGUE AL CARRITO: " + producto);
    }

    @And("voy al carrito")
    public void Redireccion_al_carrito(){
        inventoryPage.goToCart();
        System.out.println("Viaje a la pagina del carrito");
    }

    @And("procedo al checkout")
    public void Redireccion_al_checkout(){

       checkoutPage =  cartPage.checkoutBtn();
        System.out.println("Viaje a la pagina del checkout");
    }

    @And("completo el formulario con nombre {string} apellido {string} y codigo postal {string}")
    public void validar_completado_formulario(String  nombre, String apellido, String codigoPostal){
        checkoutPage.completeFormCheckout(nombre, apellido, codigoPostal);
        System.out.println("Form llenado correctamente");
    }

    @And("hago click en continuar")
    public void click_continuar(){
        checkoutPage.clickContinue();
        System.out.println("Click en continuar");
    }

    @And("confirmo la compra")
    public void click_finalizar(){
        checkoutPage.clickFinish();
        System.out.println("Click en finalizar");
    }

    @Then("debería ver el mensaje de confirmación {string}")
    public void validar_mensaje_confirmacion(String mensaje){
        Assert.assertEquals(checkoutPage.getConfirmationMessage(),"Thank you for your order!", "SE ESPERABA UN MENSAJE DISTINTO");
    }

    @Given("estoy logueado como {string}")
    public void estoyLogueadoComo(String usuario){
        LoginPage loginPage = new LoginPage(Hooks.driver);
        loginPage.goTo();
        inventoryPage = loginPage.LoginAs(usuario, "secret_sauce");
        System.out.println("LOGUEADO COMO: " + usuario);
    }

    @Then("debería ver {string} en la lista")
    public void validar_producto_en_carrito(String producto){
        Assert.assertTrue(cartPage.getItemNames().contains(producto), "EL PRODUCTO " + producto + " NO ESTA EN EL CARRITO");
        System.out.println("PRODUCTO EN EL CARRITO: " + producto);
    }





}

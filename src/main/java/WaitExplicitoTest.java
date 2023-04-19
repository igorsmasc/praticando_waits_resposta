import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitExplicitoTest {
    // Elemento
    // ExpectedConditions
    // Elementos especificos

    private WebDriver driver;
    private final String URL = "https://igorsmasc.github.io/praticando_waits/";
    private WebDriverWait wait;

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(URL);
    }

    @AfterEach
    public void afteEach() {
        // driver.close(); Fecha apenas 1 aba / 1 janela
        driver.quit(); // Fechar tudo
    }

    @Test
    public void botaoEscondidoTest() {
        WebElement element = driver.findElement(By.id("botao-escondido"));

        wait.until(ExpectedConditions.visibilityOf(element));

        element.click();

        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Você clicou no botão escondido!", alert.getText());
    }

    @Test
    public void botaoAlertTest() {
        driver.findElement(By.id("botao-alerta")).click();

        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Alerta após 3 segundos!", alert.getText());
    }

    @Test
    public void botaoTituloTest() {
        WebElement titulo = driver.findElement(By.id("titulo"));
        Assertions.assertEquals("Título da Página", titulo.getText());

        driver.findElement(By.id("botao-titulo")).click();

        wait.until(ExpectedConditions.textToBe(By.id("titulo"), "Novo Título da Página"));

        Assertions.assertEquals("Novo Título da Página", titulo.getText());
    }

    @Test
    public void botaoParagrafoTest(){
        driver.findElement(By.id("botao-paragrafo")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")));

        WebElement element = driver.findElement(By.xpath("/html/body/p"));
        Assertions.assertEquals("Este é um novo parágrafo adicionado após 3 segundos.", element.getText());
    }

    @Test
    public void botaoNovoTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("botao-novo")));

        driver.findElement(By.id("botao-novo")).click();

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Botão clicado!", alert.getText());
    }
}

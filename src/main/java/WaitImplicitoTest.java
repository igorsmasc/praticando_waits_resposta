import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WaitImplicitoTest {
    // Global
    // Config unica
    // Problemas gerais de carregamento
    // + facil de aplicar

    private WebDriver driver;
    private final String URL = "https://igorsmasc.github.io/praticando_waits/";

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

        element.click();

        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Você clicou no botão escondido!", alert.getText());
    }

    @Test
    public void botaoAlertTest() {
        driver.findElement(By.id("botao-alerta")).click();


        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Alerta após 3 segundos!", alert.getText());
    }

    @Test
    public void botaoTituloTest() {
        WebElement titulo = driver.findElement(By.id("titulo"));
        Assertions.assertEquals("Título da Página", titulo.getText());

        driver.findElement(By.id("botao-titulo")).click();

        Assertions.assertEquals("Novo Título da Página", titulo.getText());
    }

    @Test
    public void botaoParagrafoTest(){
        driver.findElement(By.id("botao-paragrafo")).click();

        WebElement element = driver.findElement(By.xpath("/html/body/p"));
        Assertions.assertEquals("Este é um novo parágrafo adicionado após 3 segundos.", element.getText());

    }

    @Test
    public void botaoNovoTest() {

        driver.findElement(By.id("botao-novo")).click();

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Botão clicado!", alert.getText());
    }
}

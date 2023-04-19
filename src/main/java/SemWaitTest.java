import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SemWaitTest {
    // Testando sem Wait

    private WebDriver driver;
    private final String URL = "https://igorsmasc.github.io/praticando_waits/";

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @AfterEach
    public void afteEach() {
        // driver.close(); Fecha apenas 1 aba / 1 janela
        driver.quit(); // Fechar tudo
    }

    @Test
    public void botaoEscondidoTest() throws InterruptedException {
        WebElement element = driver.findElement(By.id("botao-escondido"));
        Thread.sleep(10000);

        element.click();

        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Você clicou no botão escondido!", alert.getText());
    }

    @Test
    public void botaoAlertTest() throws InterruptedException {
        driver.findElement(By.id("botao-alerta")).click();

        Thread.sleep(10000);

        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Alerta após 3 segundos!", alert.getText());
    }

    @Test
    public void botaoTituloTest() throws InterruptedException {
        WebElement titulo = driver.findElement(By.id("titulo"));
        Assertions.assertEquals("Título da Página", titulo.getText());

        driver.findElement(By.id("botao-titulo")).click();

        Thread.sleep(10000);

        Assertions.assertEquals("Novo Título da Página", titulo.getText());
    }

    @Test
    public void botaoParagrafoTest() throws InterruptedException {
        driver.findElement(By.id("botao-paragrafo")).click();

        Thread.sleep(10000);

        WebElement element = driver.findElement(By.xpath("/html/body/p"));
        Assertions.assertEquals("Este é um novo parágrafo adicionado após 3 segundos.", element.getText());

    }

    @Test
    public void botaoNovoTest() throws InterruptedException {
        Thread.sleep(10000);

        driver.findElement(By.id("botao-novo")).click();

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Botão clicado!", alert.getText());
    }
}

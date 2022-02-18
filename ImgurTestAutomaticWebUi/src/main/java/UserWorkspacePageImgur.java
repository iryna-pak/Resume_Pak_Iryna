import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class UserWorkspacePageImgur extends BasePageImgur {

    public UserWorkspacePageImgur(WebDriver driver) {
        super(driver);
    }

    /* Меню пользователя -------------------------------*/
    private final static String MENU_USER_LOCATOR_BY_XPATH =
            "//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']/span[2]";
    @FindBy(xpath = MENU_USER_LOCATOR_BY_XPATH)
    private WebElement buttonMenuUser;

    private final static String POSTS_BUTTON_LOCATOR_BY_XPATH = "//a[contains(text(),'Posts')]";
    @FindBy(xpath = POSTS_BUTTON_LOCATOR_BY_XPATH)
    private WebElement buttonPosts;

    private final static String SIGNOUT_BUTTON_LOCATOR_BY_XPATH = "//span[.='Sign Out']";
    @FindBy(xpath = SIGNOUT_BUTTON_LOCATOR_BY_XPATH)
    private WebElement buttonSignOut;
    /* ------------------------------------*/

    @Step("Клик на на кнопку меню пользователя")
    public UserWorkspacePageImgur clickButtonMenuUser() {
        buttonMenuUser.click();
        return this;
    }

    @Step("Клик на на кнопку 'Posts'")
    public UserWorkspacePageImgur clickButtonPosts() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath(POSTS_BUTTON_LOCATOR_BY_XPATH)));
        buttonPosts.click();
        return this;
    }

    @Step("Клик на на кнопку 'SignOut'")
    public UserWorkspacePageImgur clickButtonSignOut() {
        buttonSignOut.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id]//h1")));
        return this;
    }

    @Step("Проверка успешной авторизации")
    public UserWorkspacePageImgur checkSuccessAuthorization() {
        assertThat(driver.findElement(
                By.xpath("//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']/span[1]")), hasText("irynapak"));
        return this;
    }

    @Step("Преворека успешного перехода авторизованного пользователя в раздел 'Posts'")
    public UserWorkspacePageImgur checkSuccessAuthorizationPosts() {
        assertTrue((driver.getCurrentUrl().contentEquals("https://imgur.com/user/irynapak/posts")));
        return this;
    }
}

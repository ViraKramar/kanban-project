package ui.pageobjects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class BasePageObject {

        void clickOnElementWithJs(SelenideElement selenideElement) {
            Selenide.executeJavaScript("arguments[0].click();", selenideElement);
        }

        public String generateRandom(){
                    Integer number = (int) (20 + Math.random()*400);
                    return String.valueOf(number);
        }


}

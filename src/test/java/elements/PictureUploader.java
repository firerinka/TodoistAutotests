package elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class PictureUploader extends Element {

    public PictureUploader(String name, SelenideElement selector) {
        super(name, selector);
    }

    @Step("В Uploader '{this.name}' загружаем из ресурсов изображение '{picturePath}'")
    public void uploadPicture(String picturePath) {
        System.out.println("В uploader {this.name} загружаем изображение {picturePath}");
        selector.uploadFromClasspath(picturePath);
    }
}

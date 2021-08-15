//package com.tMobile;
//
//import com.microsoft.playwright.*;
//import org.junit.Test;
////import org.junit.jupiter.api.*;
//
//import java.nio.file.Paths;
//
//public class RenewMB {
//
//    // Shared between all tests in this class.
//    static Playwright playwright;
//    static Browser browser;
//
//    // New instance for each test method.
//    BrowserContext context;
//    Page page;
//
//    @BeforeAll
//    static void launchBrowser() {
//        playwright = Playwright.create();
//        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000));
//    }
//
//    @AfterAll
//    static void closeBrowser() {
//        playwright.close();
//    }
//
//    @BeforeEach
//    void createContextAndPage() {
//        context = browser.newContext();
//        page = context.newPage();
//    }
//
//    @AfterEach
//    void closeContext() {
//        context.close();
//    }
//
//    @Test
//    void tmobiletest() {
//        page.navigate("https://www.t-mobile.nl/login");
//        page.onDialog(dialog -> dialog.accept());
////        page.click("//label[contains(text(), 'Ja')]");
//        try {
//            ElementHandle el = page.waitForSelector("//label[contains(text(), 'Ja')]");
//            System.out.println(el.innerText());
//            el.click();
//        } catch (Exception e) {
//            System.out.println("el not found");
//        }
//    }
//
//    @Test
//    void getTitle() {
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch();
//            Page page = browser.newPage();
//            page.navigate("http://playwright.dev");
//            System.out.println(page.title());
//        }
//    }
//
//    @Test
//    void takeScreenShot() {
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000));
//            Page page = browser.newPage();
//            page.navigate("http://whatsmyuseragent.org/");
//            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
//        }
//    }
//}
//

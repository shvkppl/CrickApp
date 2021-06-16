import { browser, by, element, ElementFinder, promise } from 'protractor';

export class Favorite {
    // navigate to bookmark page
    navigateToFavoritePage() {
        return browser.get('/favorites');
    }
    // get current URL
    getCurrentURL() {
        return browser.getCurrentUrl();
    }

    clickFav() {
        element(by.className('favbtn')).click();
    }

    // getAddRegister(): ElementFinder {
    //     return element(by.className('bookmarkadd'));
    // }
}

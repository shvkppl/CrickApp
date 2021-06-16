import { browser, by, element, ElementFinder, promise } from 'protractor';

export class Matches {
    // navigate to bookmark page
    navigateToMatches() {
        return browser.get('/matches');
    }
    // get current URL
    getCurrentURL() {
        return browser.getCurrentUrl();
    }

    clickFavButton() {
        element(by.className('favbtn')).click();
    }

    // getAddRegister(): ElementFinder
    // {
    //     return element(by.className('bookmarkadd'));
    // }
}
